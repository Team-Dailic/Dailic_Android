package com.syaainn.dailic.di

import com.syaainn.dailic.data.service.LicenseService
import com.syaainn.dailic.data.service.ProblemService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideLicenseService(retrofit: Retrofit): LicenseService =
        retrofit.create(LicenseService::class.java)

    @Provides
    @Singleton
    fun provideProblemService(retrofit: Retrofit): ProblemService =
        retrofit.create(ProblemService::class.java)
}
