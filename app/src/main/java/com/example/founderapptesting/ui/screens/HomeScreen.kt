package com.example.founderapptesting.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.founderapptesting.dataclasses.Product
import com.example.founderapptesting.ui.components.PhilosophyBanner
import com.example.founderapptesting.ui.components.ProductCard
import com.example.founderapptesting.ui.components.ProductDetailSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    products: List<Product>,
    onNavigateToSearch: () -> Unit
) {
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    // Get first 4 products for New Arrivals
    val newArrivals = products.take(4)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Discovery",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF1A1A1A)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { /* Handle menu */ },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color(0xFF1A1A1A)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = onNavigateToSearch,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color(0xFF1A1A1A)
                        )
                    }
                    IconButton(
                        onClick = { /* Handle cart */ },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = "Cart",
                            tint = Color(0xFF1A1A1A)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .background(Color.White)
                    .height(64.dp)
            )
        },
        containerColor = Color.White
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(padding)
                .background(Color.White)
        ) {
            // Header Section - New Arrivals
            item(span = { GridItemSpan(2) }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "New Arrivals",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A1A),
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "Curated essentials for the modern minimalist.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF757575),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // New Arrivals Products (First 4)
            items(newArrivals) { product ->
                Box(
                    modifier = Modifier
                        .clickable { selectedProduct = product }
                ) {
                    ProductCard(product)
                }
            }

            // Philosophy Banner - Full Width
            item(span = { GridItemSpan(2) }) {
                PhilosophyBanner()
            }

            // Additional Products (if any)
            items(products.drop(4)) { product ->
                Box(
                    modifier = Modifier
                        .clickable { selectedProduct = product }
                ) {
                    ProductCard(product)
                }
            }
        }
    }

    // Product Detail Sheet
    selectedProduct?.let { product ->
        ProductDetailSheet(product) { selectedProduct = null }
    }
}