package com.orion.templete.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orion.templete.data.model.Job
import com.orion.templete.data.model.PrimaryDetails
import com.orion.templete.ui.bookmark.BookmarksScreen
import com.orion.templete.ui.jobs.JobsScreen
import com.orion.templete.ui.common.BottomNav
import com.orion.templete.ui.jobs.JobDetailsScreen
import com.orion.templete.util.Screen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNav(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Jobs.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Jobs.route) {
                JobsScreen() { job ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("job", job)
                    navController.navigate(Screen.JobDetails.route)
                }
            }
            composable(Screen.JobDetails.route) {
                val job = navController.previousBackStackEntry?.savedStateHandle?.get<Job>("job")
                if(job != null) JobDetailsScreen(job){ navController.popBackStack() }
            }
            composable(Screen.Bookmarks.route) {
                BookmarksScreen()
            }
        }
    }
}
