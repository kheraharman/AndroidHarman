package com.android.data.di

import com.android.data.remote.FruitsApi
import com.android.data.repository.FruitsRepositoryImpl
import com.android.domain.repository.FruitsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FruitsModule {

    /**
     * Provides a singleton instance of [FruitsApi] using the provided [Retrofit] instance.
     *
     * @param retrofit The Retrofit instance used to create the [FruitsApi].
     * @return An instance of [FruitsApi].
     */

    @Singleton
    @Provides
    fun provideFruitsApi(retrofit: Retrofit): FruitsApi {
        return retrofit.create(FruitsApi::class.java)
    }

    /**
     * Provides a singleton instance of [FruitsRepository] using the provided [FruitsApi].
     *
     * @param fruitsApi The [FruitsApi] instance used to create the [FruitsRepository].
     * @return An instance of [FruitsRepository].
     */

    @Singleton
    @Provides
    fun provideFruitsRepository(fruitsApi: FruitsApi): FruitsRepository {
        return FruitsRepositoryImpl(fruitsApi)
    }
}