package com.example.rxjavaplayground.network

import com.example.rxjavaplayground.model.TopRatingResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val API_KEY = "4d601705c8324648b378d5e18e98d78e"
        const val BASE_URL = "https://api.rawg.io/api/"
    }

    @GET("games?key=${API_KEY}&page_size=10&ordering=-rating&platforms=4&page=1")
    fun getListTopRating(): Observable<TopRatingResponse>
}