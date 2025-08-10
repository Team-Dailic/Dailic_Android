package com.syaainn.dailic.data.service

import com.syaainn.dailic.data.dto.request.SelectLicenseRequestDto
import com.syaainn.dailic.data.dto.response.SelectLicenseResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("/licenses")
    suspend fun selectLicense(
        @Query("userId") userId: Long = 1,
        @Body requestBody: SelectLicenseRequestDto
    ): Response<SelectLicenseResponseDto>
}