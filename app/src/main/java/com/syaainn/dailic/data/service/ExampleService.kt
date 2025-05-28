package com.syaainn.dailic.data.service

import com.syaainn.dailic.data.dto.base.ApiResponse
import com.syaainn.dailic.data.dto.request.ExampleRequestDto
import com.syaainn.dailic.data.dto.response.ExampleResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ExampleService {
    @GET("api/v1/data")
    suspend fun getExampleData(): ApiResponse<ExampleResponseDto>

    @POST("api/v1/data")
    suspend fun postExampleData(
        @Body exampleRequestDto: ExampleRequestDto,
    ): ApiResponse<Unit>
}