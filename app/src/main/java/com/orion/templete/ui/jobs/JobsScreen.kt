package com.orion.templete.ui.jobs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.orion.templete.R
import com.orion.templete.data.model.Job
import com.orion.templete.data.model.PrimaryDetails
import com.orion.templete.ui.theme.TempleteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobsScreen(
    viewModel: JobsViewModel = hiltViewModel()
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = jobs.itemCount,
                ) { index ->
                    val job = jobs[index]
                    if (job != null) {
                        JobCard(
                            job = job
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobCard(
    job: Job,
    isBookmarked: Boolean = false,
    onBookmarkClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            job.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            job.company_name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "₹${job.salary_min ?: 0} - ₹${job.salary_max ?: 0}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (job.primary_details?.Place != null) {
                Text(
                    text = job.primary_details.Place,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))
            }

            // Job Type - only show if primary_details and Job_Type are not null
            if (job.primary_details?.Job_Type != null) {
                Text(
                    text = job.primary_details.Job_Type,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Button(
                    onClick = { /* Apply action */ },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .height(36.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = job.button_text?.takeIf { it.isNotEmpty() } ?: "Apply")
                }

                IconButton(
                    onClick = onBookmarkClick,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
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
        JobsScreen()
    }
}