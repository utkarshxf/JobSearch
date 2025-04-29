package com.orion.templete.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.orion.templete.R

sealed class Screen(val route: String, val title: String, val icon: @Composable () -> Unit) {
    object Jobs : Screen(
        route = "jobs",
        title = "Jobs",
        icon = { Icon(Icons.Filled.Menu, contentDescription = "Jobs") }
    )
    object Bookmarks : Screen(
        route = "bookmarks",
        title = "Bookmarks",
        icon = { Icon(painter = painterResource(id = R.drawable.ic_bookmark), contentDescription = "Bookmarks") }
    )
}