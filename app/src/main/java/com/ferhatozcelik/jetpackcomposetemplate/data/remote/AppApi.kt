package com.ferhatozcelik.jetpackcomposetemplate.data.remote

import com.ferhatozcelik.jetpackcomposetemplate.data.model.ExampleModel
import retrofit2.Response
import retrofit2.http.*

interface AppApi {

    @GET("/api/v1/example")
    suspend fun getExampleResult(): Response<List<ExampleModel>>

}