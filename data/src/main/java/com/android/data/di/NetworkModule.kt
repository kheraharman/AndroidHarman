package com.android.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * A Dagger module that provides network-related dependencies such as Retrofit and OkHttpClient.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides a singleton Retrofit instance configured with the base URL and a Gson converter.
     *
     * @param okHttp The OkHttpClient instance to be used by Retrofit for HTTP requests.
     * @return A configured Retrofit instance.
     */
    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(okHttp)
            baseUrl("https://fruityvice.com/api/fruit/")
        }.build()
    }

    /**
     * Provides a singleton OkHttpClient instance configured with a logging interceptor.
     *
     * @param httpLoggingInterceptor The HttpLoggingInterceptor instance to log HTTP request and response.
     * @return A configured OkHttpClient instance.
     */
    @Singleton
    @Provides
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            this.addInterceptor(httpLoggingInterceptor)
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }.build()
    }

    /**
     * Provides a singleton HttpLoggingInterceptor instance set to log the body of HTTP requests and responses.
     *
     * @return A configured HttpLoggingInterceptor instance.
     */
    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}