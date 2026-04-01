package org.lmi.android.interview.hackernews.network

import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.Path

val BASE_URL = "https://hacker-news.firebaseio.com/"

/**
 * Retrofit service for the Hacker News API
 *
 * https://github.com/HackerNews/API
 */

// https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty
// https://hacker-news.firebaseio.com/v0/item/26068032.json?print=pretty
interface HackerNewsService {

    @GET("v0/topstories.json")
    suspend fun getTopStories(): List<Int>

    @GET("v0/item/{id}.json")
    suspend fun getItem(@Path("id") id: Int): Item
}

@JsonClass(generateAdapter = true)
data class Item(
    val id: Int,
    val title: String,
    val text: String?,
    val time: Int,
    val type: String,
    val by: String?,
    val descendants: Int?,
    val kids: List<Int>?,
    val score: Int?,
    val url: String?,
)
