package com.example.jetshopping.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onNavigateToItem: (Int) -> Unit,
) {
    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java))
}