package com.example.founderapptesting.dataclasses

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val brand: String,
    val category: String,
    // Using JsonElement handles the inconsistent number/string price format
    val price: JsonElement? = null,
    val rating: Double? = 0.0,
    val reviews: Int = 0,
    val inStock: Boolean = true,
    val image: String? = null,
    val description: String? = null
) {
    // Computed property to format price safely
    val formattedPrice: String
        get() {
            val rawPrice = price?.jsonPrimitive?.content ?: "0.0"
            return "$${String.format("%.2f", rawPrice.toDoubleOrNull() ?: 0.0)}"
        }
}