package com.syaainn.dailic.data.service

import com.syaainn.dailic.data.dto.base.ApiResponse
import com.syaainn.dailic.data.dto.request.SelectLicenseRequestDto
import com.syaainn.dailic.data.dto.response.GetCurrentLicenseResponseDto
import com.syaainn.dailic.data.dto.response.SelectLicenseResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface LicenseService {
    @POST("/licenses")
    suspend fun selectLicense(
        @Query("userId") userId: Long = 1,
        @Body requestBody: SelectLicenseRequestDto
    ): ApiResponse<SelectLicenseResponseDto>

    @GET("/licenses/current")
    suspend fun getCurrentLicense(
        @Query("userId") userId: Long = 1
    ): ApiResponse<GetCurrentLicenseResponseDto>

    @PUT("/licenses")
    suspend fun changeLicense(
        @Query("userId") userId: Long = 1,
        @Body requestBody: SelectLicenseRequestDto
    ): ApiResponse<SelectLicenseResponseDto>
}