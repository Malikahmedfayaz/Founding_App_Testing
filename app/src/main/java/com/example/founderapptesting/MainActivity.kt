package com.example.founderapptesting

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.*
import com.example.founderapptesting.dataclasses.Product
import com.example.founderapptesting.ui.screens.DiscoveryScreen
import com.example.founderapptesting.ui.screens.HomeScreen
import com.example.founderapptesting.ui.theme.FounderAppTestingTheme
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Data Loading Logic
        try {
            val jsonString = assets.open("items.json").bufferedReader().use { it.readText() }
            val products = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.decodeFromString<List<Product>>(jsonString)

            viewModel.setProducts(products)
        } catch (e: Exception) {
            Log.e("MainActivity", "Error loading JSON: ${e.message}")
        }

        enableEdgeToEdge()
        setContent {
            FounderAppTestingTheme {
                // 2. Navigation State
                var currentScreen by remember { mutableStateOf("Home") }

                // Collect states from ViewModel
                val searchQuery by viewModel.searchQuery.collectAsState()
                val selectedCategory by viewModel.selectedCategory.collectAsState()

                // 3. Screen Switcher
                if (currentScreen == "Home") {
                    HomeScreen(
                        products = viewModel.filteredProducts,
                        onNavigateToSearch = { currentScreen = "Search" }
                    )
                } else {
                    DiscoveryScreen(
                        products = viewModel.filteredProducts,
                        searchQuery = searchQuery,
                        onSearchChange = { viewModel.onSearchChange(it) },
                        selectedCategory = selectedCategory,
                        onCategorySelect = { viewModel.onCategorySelect(it) },
                        onNavigateBack = { currentScreen = "Home" }
                    )
                }
            }
        }
    }
}