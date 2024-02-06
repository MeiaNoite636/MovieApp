package com.example.themovie.di

import com.example.themovie.data.api.ServiceAPI
import com.example.themovie.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesServiceProvider() = ServiceProvider()

    @Provides
    fun providerServiceAPI(
        serviceProvider: ServiceProvider
    ): ServiceAPI{
        return serviceProvider.createService(ServiceAPI::class.java)
    }
}