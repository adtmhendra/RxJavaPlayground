package com.example.rxjavaplayground.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().apply {
    baseUrl(ApiService.BASE_URL)
    addConverterFactory(MoshiConverterFactory.create(moshi))
    addCallAdapterFactory(RxJava3CallAdapterFactory.create())
}.build()

object Api {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}