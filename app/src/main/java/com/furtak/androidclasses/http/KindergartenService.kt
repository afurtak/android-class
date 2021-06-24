package com.furtak.androidclasses.http

import com.furtak.androidclasses.http.model.ChildResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface KindergartenService {
    @GET("/children")
    suspend fun getChildrenList(): List<ChildResponse>

    @GET("/image/{name}")
    suspend fun getChildPhoto(@Path("name") name: String): ResponseBody
}
