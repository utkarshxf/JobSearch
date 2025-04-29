package com.orion.templete.ui.common
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import com.orion.templete.R
import com.orion.templete.data.model.Job
import com.orion.templete.ui.bookmark.BookmarkViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobCard(
    job: Job,
    bookmarkViewModel: BookmarkViewModel,
    onJobClick: (Job) -> Unit = {}
) {
    val isBookmarked by bookmarkViewModel.isJobBookmarked(job.id)
        .collectAsState(initial = false)
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = { onJobClick(job) }
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

            // Company name check
            job.company_name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Salary information
            Text(
                text = "₹${job.salary_min ?: 0} - ₹${job.salary_max ?: 0}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Place - only show if primary_details and Place are not null
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
                // Button text check
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .height(36.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = job.button_text?.takeIf { it.isNotEmpty() } ?: "Apply")
                }

                IconButton(
                    onClick = {
                        bookmarkViewModel.toggleBookmark(job, isBookmarked)
                    },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        painter = painterResource(id = if (isBookmarked) R.drawable.ic_filled_bookmark else R.drawable.ic_bookmark),
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
