package com.tech.drivychallenge.common.module

import com.squareup.moshi.Moshi
import com.tech.drivychallenge.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val CONVERTER_MOSHI = "MOSCHI"
    }

    @Provides
    @Named(CONVERTER_MOSHI)
    fun provideRetrofitMoschi(
        moshiConverterFactory: MoshiConverterFactory,
        client: OkHttpClient.Builder
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client.build())
        .addConverterFactory(moshiConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient.Builder {
        return OkHttpClient()
            .newBuilder()
    }

    @Provides
    fun provideMoshiConverter(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}