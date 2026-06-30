package com.example.founderapptesting

import androidx.lifecycle.ViewModel
import com.example.founderapptesting.dataclasses.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel : ViewModel() {
    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory = _selectedCategory.asStateFlow()

    fun setProducts(products: List<Product>) {
        _allProducts.value = products
    }

    fun onSearchChange(newQuery: String) { _searchQuery.value = newQuery }
    fun onCategorySelect(category: String) { _selectedCategory.value = category }

    val filteredProducts: List<Product>
        get() {
            val query = _searchQuery.value.lowercase()
            return _allProducts.value.filter {
                (it.title.lowercase().contains(query) || it.brand.lowercase().contains(query)) &&
                        (_selectedCategory.value == "All" || it.category == _selectedCategory.value)
            }
        }
}