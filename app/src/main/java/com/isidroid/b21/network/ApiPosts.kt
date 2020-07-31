package com.isidroid.b21.network

import com.isidroid.b21.network.responses.PostsResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiPosts {
    enum class SortOptions(val api: String) {
        POPULAR("mostPopular"),
        COMMENTED("mostCommented"),
        CREATED_AT("createdAt")
    }


    @GET("posts")
    fun posts(
        @Query("first") first: Int = 20,
        @Query("after") after: String? = null,
        @Query("orderBy") orderBy: String = SortOptions.CREATED_AT.api
    ): Single<PostsResponse>
}