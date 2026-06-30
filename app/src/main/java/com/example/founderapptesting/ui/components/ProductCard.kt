package com.example.founderapptesting.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.founderapptesting.dataclasses.Product

@Composable
fun ProductCard(product: Product) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Image Container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFEEEEEE))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                contentDescription = product.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = android.R.drawable.ic_menu_gallery) // Fallback image when not available
            )

            // "Sold Out" Badge if not in stock
            if (!product.inStock) {
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(4.dp),
                    color = Color.Black.copy(alpha = 0.7f)
                ) {
                    Text(
                        text = "SOLD OUT",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Title
        Text(
            text = product.title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xFF111111),
            maxLines = 1
        )

        // Price
        Text(
            text = product.formattedPrice,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}

//@Composable
//fun DiscoveryBottomNavigation(
//    currentScreen: String,
//    onTabSelected: (String) -> Unit
//) {
//    NavigationBar(
//        containerColor = Color.White,
//        tonalElevation = 0.dp
//    ) {
//        val items = listOf("Home", "Search") // Matches your screen names
//        val icons = listOf(Icons.Default.Home, Icons.Default.Search)
//
//        items.forEachIndexed { index, item ->
//            val isSelected = currentScreen == item
//
//            NavigationBarItem(
//                icon = { Icon(icons[index], contentDescription = item) },
//                label = { Text(item) },
//                selected = isSelected,
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = Color.White,
//                    selectedTextColor = Color.Black,
//                    indicatorColor = Color(0xFF222222)
//                ),
//                onClick = { onTabSelected(item) } // Triggers the navigation in MainActivity
//            )
//        }
//    }
//}