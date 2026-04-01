package org.lmi.android.interview.hackernews

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import org.lmi.android.interview.hackernews.network.Item
import org.lmi.android.interview.hackernews.viewmodels.MainViewModel

/**
 * This Activity shows the Hacker News home screen
 *
 * The Challenge:
 *
 * Recreate the Hacker News home screen in the app. The UI can be simple.
 *
 * For reference: https://news.ycombinator.com/
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackerNewsScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HackerNewsScreen(viewModel: MainViewModel = viewModel()) {
    val uiState by viewModel.uiStateFlow.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Hacker News") }) },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            when (val state = uiState) {
                is MainViewModel.UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is MainViewModel.UiState.ShowUi -> {
                    StoryList(stories = state.stories)
                }
                is MainViewModel.UiState.Error -> {
                    Text(
                        text = state.message,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}

@Composable
private fun StoryList(stories: List<Item>) {
    LazyColumn {
        itemsIndexed(stories) { index, item ->
            StoryRow(rank = index + 1, item = item)
        }
    }
}

@Composable
private fun StoryRow(rank: Int, item: Item) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(
            text = "$rank. ${item.title}",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = "${item.score ?: 0} points by ${item.by ?: "unknown"}",
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
