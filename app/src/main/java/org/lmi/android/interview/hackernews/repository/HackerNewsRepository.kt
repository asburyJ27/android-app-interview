package org.lmi.android.interview.hackernews.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.lmi.android.interview.hackernews.network.HackerNewsService
import org.lmi.android.interview.hackernews.network.Item
import javax.inject.Inject

/**
 *  Repository for fetching hacker news data
 */
class HackerNewsRepository @Inject constructor(private val service: HackerNewsService) {

    suspend fun getTopStories(): List<Item> = coroutineScope {
        val ids = service.getTopStories().take(30)
        ids.map { id -> async { service.getItem(id) } }.awaitAll()
    }
}
