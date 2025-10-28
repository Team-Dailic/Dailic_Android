package com.syaainn.dailic.di

import com.syaainn.dailic.data.service.LicenseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideLicenseService(retrofit: Retrofit): LicenseService =
        retrofit.create(LicenseService::class.java)
}