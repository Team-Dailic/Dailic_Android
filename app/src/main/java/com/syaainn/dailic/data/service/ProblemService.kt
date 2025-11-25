package com.syaainn.dailic.data.service

import com.syaainn.dailic.data.dto.base.ApiResponse
import com.syaainn.dailic.data.dto.request.PostAiQuestionRequestDto
import com.syaainn.dailic.data.dto.request.PostProblemAnswerRequestDto
import com.syaainn.dailic.data.dto.request.PutScrapProblemRequestDto
import com.syaainn.dailic.data.dto.response.GetAiSolutionResponseDto
import com.syaainn.dailic.data.dto.response.GetDailyProblemResponseDto
import com.syaainn.dailic.data.dto.response.GetScrapProblemResponseDto
import com.syaainn.dailic.data.dto.response.GetWrongProblemResponseDto
import com.syaainn.dailic.data.dto.response.PostAiQuestionResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProblemService {
    @POST("/daily-problems")
    suspend fun postDailyProblem(
        @Query("userId") userId: Long = 1
    )

    @GET("/daily-problems")
    suspend fun getDailyProblem(
        @Query("userId") userId: Long = 1
    ): ApiResponse<List<GetDailyProblemResponseDto>>

    @POST("/user-problem-status/submit")
    suspend fun postProblemAnswer(
        @Body requestBody: PostProblemAnswerRequestDto
    )

    @PUT("/user-problem-status/scrap")
    suspend fun putScrapProblem(
        @Body requestBody: PutScrapProblemRequestDto
    )

    @GET("/user-problem-status/wrong/{userId}")
    suspend fun getWrongProblem(
        @Path("userId") userId: Long = 1
    ): ApiResponse<List<GetWrongProblemResponseDto>>

    @GET("/user-problem-status/scrap/{userId}")
    suspend fun getScrapProblem(
        @Path("userId") userId: Long = 1
    ): ApiResponse<List<GetScrapProblemResponseDto>>

    @POST("/ai/explain/{problemId}")
    suspend fun getAiSolution(
        @Path("problemId") problemId: Long,
        @Query("userId") userId: Long = 1,
        @Query("includeSolution") includeSolution: Boolean
    ): ApiResponse<GetAiSolutionResponseDto>

    @POST("/ai/ask/{problemId}")
    suspend fun postAiQuestion(
        @Path("problemId") problemId: Long,
        @Query("userId") userId: Long = 1,
        @Body requestBody: PostAiQuestionRequestDto
    ): ApiResponse<PostAiQuestionResponseDto>
}
