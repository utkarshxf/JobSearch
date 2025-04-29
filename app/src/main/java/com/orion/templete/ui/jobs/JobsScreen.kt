package com.orion.templete.ui.jobs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.orion.templete.R
import com.orion.templete.data.model.Job
import com.orion.templete.data.model.PrimaryDetails
import com.orion.templete.ui.bookmark.BookmarkViewModel
import com.orion.templete.ui.common.JobCard
import com.orion.templete.ui.theme.TempleteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsScreen(
    viewModel: JobsViewModel = hiltViewModel(),
    bookmarkViewModel: BookmarkViewModel = hiltViewModel(),
    goToDetailedScreen:(Job)-> Unit,
) {
    val jobs = viewModel.jobsFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jobs") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                jobs.loadState.refresh is LoadState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                jobs.loadState.refresh is LoadState.Error -> {
                    val error = (jobs.loadState.refresh as LoadState.Error).error
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorView(
                            message = error.localizedMessage ?: "An unknown error occurred",
                            onRetryClick = { jobs.retry() }
                        )
                    }
                }
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            count = jobs.itemCount
                        ) { index ->
                            val job = jobs[index]
                            if (job != null) {
                                JobCard(
                                    job = job,
                                    bookmarkViewModel
                                ){ goToDetailedScreen(it) }
                            } else {
                                // Placeholder for null items (can happen during loading)
                                JobCardPlaceholder()
                            }
                        }

                        // Add loading footer when appending more items
                        if (jobs.loadState.append is LoadState.Loading) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        // Add error footer when append fails
                        if (jobs.loadState.append is LoadState.Error) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                val error = (jobs.loadState.append as LoadState.Error).error
                                ErrorView(
                                    message = error.localizedMessage ?: "Failed to load more items",
                                    onRetryClick = { jobs.retry() },
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }

                        // Show empty state
                        if (jobs.itemCount == 0 && jobs.loadState.refresh !is LoadState.Loading) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No jobs found",
                                        style = MaterialTheme.typography.bodyLarge,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorView(
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = onRetryClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Retry")
        }
    }
}

@Composable
fun JobCardPlaceholder() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = 0.3f))
        )
    }
}

// Preview function for testing
@Preview
@Composable
fun JobsScreenPreview() {
    val sampleJobs = listOf(
        Job(
            company_name = "Tech Solutions Ltd",
            salary_max = 50000,
            salary_min = 30000,
            title = "Android Developer",
            city_location = 1,
            amount = "40000",
            button_text = "Apply Now",
            content = "We are looking for an experienced Android developer to join our team.",
            job_type = 1,
            primary_details = PrimaryDetails(
                Experience = "2-3 years",
                Fees_Charged = "None",
                Job_Type = "Full-time",
                Place = "Bangalore",
                Qualification = "B.Tech/MCA",
                Salary = "30000-50000"
            ),
            qualification = 1,
            whatsapp_no = "9876543210",
            id = 1
        ),
        Job(
            company_name = "Digital Innovators",
            salary_max = 70000,
            salary_min = 45000,
            title = "Senior UI/UX Designer",
            city_location = 2,
            amount = "60000",
            button_text = "Apply",
            content = "Looking for a creative UI/UX designer with experience in mobile apps.",
            job_type = 1,
            primary_details = PrimaryDetails(
                Experience = "4+ years",
                Fees_Charged = "None",
                Job_Type = "Remote",
                Place = "Delhi",
                Qualification = "Any Design Degree",
                Salary = "45000-70000"
            ),
            qualification = 2,
            whatsapp_no = "9876543211",
            id = 2
        )
        // Add more sample jobs if needed
    )
    TempleteTheme {
        JobsScreen(){}
    }
}