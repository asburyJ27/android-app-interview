package org.lmi.android.interview.hackernews.repository

import org.lmi.android.interview.hackernews.network.HackerNewsService
import org.lmi.android.interview.hackernews.network.Item
import javax.inject.Inject

/**
 *  Repository for fetching hacker news data
 */
class HackerNewsRepository @Inject constructor(private val service: HackerNewsService) {

    suspend fun getTopStories(): List<Item> {
        val ids = service.getTopStories()
        return ids.map { id ->  service.getItem(id) }
    }
}
