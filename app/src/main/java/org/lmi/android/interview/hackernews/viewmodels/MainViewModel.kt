package org.lmi.android.interview.hackernews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.lmi.android.interview.hackernews.network.Item
import org.lmi.android.interview.hackernews.repository.HackerNewsRepository
import javax.inject.Inject

/**
 * ViewModel for [org.lmi.android.interview.hackernews.MainActivity]
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: HackerNewsRepository,
) : ViewModel() {

    private var _uiStateFlow = MutableStateFlow<UiState>(UiState.Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        loadTopStories()
    }

    private fun loadTopStories() {
        viewModelScope.launch {
            _uiStateFlow.value = runCatching {
                UiState.ShowUi(repository.getTopStories())
            }.getOrElse {
                UiState.Error(it.message ?: "Unknown error")
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class ShowUi(val stories: List<Item>) : UiState()
        data class Error(val message: String) : UiState()
    }
}
