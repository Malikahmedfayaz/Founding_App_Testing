package com.example.founderapptesting.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.founderapptesting.dataclasses.Product
import androidx.compose.foundation.layout.Box
import com.example.founderapptesting.ui.components.ProductCard
import com.example.founderapptesting.ui.components.ProductDetailSheet
import androidx.activity.compose.BackHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryScreen(
    products: List<Product>,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedCategory: String,
    onCategorySelect: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    val categories = listOf("All", "Storage", "Outdoor", "Bath", "Kitchen", "Decor")

    // Check if no products match the search/filter
    val isEmpty = products.isEmpty()

    // Handle system back press
    BackHandler {
        onNavigateBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Search Feed",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color(0xFF1A1A1A)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF1A1A1A)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFAFAFA)
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        },
        containerColor = Color(0xFFFAFAFA)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFFAFAFA))
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = {
                    Text(
                        "Search products...",
                        color = Color(0xFF9E9E9E)
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = Color(0xFF9E9E9E)
                    )
                },
                shape = RoundedCornerShape(50),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFE0E0E0),
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    color = Color(0xFF1A1A1A)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Category Chips
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    val isSelected = category == selectedCategory
                    FilterChip(
                        selected = isSelected,
                        onClick = { onCategorySelect(category) },
                        label = {
                            Text(
                                category,
                                color = if (isSelected) Color.White else Color(0xFF1A1A1A),
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF1A1A1A),
                            selectedLabelColor = Color.White,
                            containerColor = Color(0xFFF0F0F0),
                            labelColor = Color(0xFF1A1A1A)
                        ),
                        shape = RoundedCornerShape(50),
                        border = null,
                        modifier = Modifier.height(36.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Product Grid or Empty State
            if (isEmpty) {
                // Empty State
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Empty state icon
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color(0xFFBDBDBD),
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // "No items found" text
                    Text(
                        text = "No items found",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1A1A1A),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Helper text
                    Text(
                        text = "Try searching for something else or clear your filters.",
                        fontSize = 14.sp,
                        color = Color(0xFF757575),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Clear Search button
                    OutlinedButton(
                        onClick = {
                            onSearchChange("")
                            onCategorySelect("All")
                        },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFF1A1A1A)
                        ),
                        border = ButtonDefaults.outlinedButtonBorder,
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = "Clear Search",
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                }
            } else {
                // Product Grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(products) { product ->
                        Box(
                            modifier = Modifier
                                .clickable { selectedProduct = product }
                        ) {
                            ProductCard(product)
                        }
                    }
                }
            }
        }
    }

    // Detail View
    selectedProduct?.let { product ->
        ProductDetailSheet(product) { selectedProduct = null }
    }
}