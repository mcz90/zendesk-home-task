package com.czyzewski.zendeskhometask.network.di

import android.content.Context
import com.czyzewski.zendeskhometask.network.api.ZendeskApi
import com.czyzewski.zendeskhometask.network.datasource.ITicketsDataSource
import com.czyzewski.zendeskhometask.network.datasource.TicketsDataSource
import com.czyzewski.zendeskhometask.network.interceptor.AuthInterceptor
import com.czyzewski.zendeskhometask.network.networkhandling.NetworkConnectivityManager
import com.czyzewski.zendeskhometask.network.networkhandling.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideTicketsDataSource(api: ZendeskApi): ITicketsDataSource {
        return TicketsDataSource(api)
    }

    @Provides
    @Singleton
    fun provideZendeskApiService(retrofit: Retrofit): ZendeskApi {
        return retrofit.create(ZendeskApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converter: MoshiConverterFactory,
        adapter: NetworkResponseAdapterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl("https://z3nmobiletechtest.zendesk.com")
            .client(okHttpClient).addCallAdapterFactory(adapter).addConverterFactory(converter)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor())
            //TODO only debug
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })

        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideNetworkResponseAdapterFactory(
        connectivityManager: NetworkConnectivityManager,
    ): NetworkResponseAdapterFactory {
        return NetworkResponseAdapterFactory(connectivityManager)
    }


    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi).asLenient()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(
        @ApplicationContext appContext: Context
    ): NetworkConnectivityManager {
        return NetworkConnectivityManager(appContext)
    }
}