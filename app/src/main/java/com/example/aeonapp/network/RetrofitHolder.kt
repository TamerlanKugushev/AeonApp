package com.example.aeonapp.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHolder {

    private const val BASE_URL = "http://82.202.204.94/"

    var apiService: ApiService =
        provideRetrofit()
            .create(ApiService::class.java)

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .baseUrl(BASE_URL)
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return provideOkHttpClientBuilder()
            .build()
    }

    private fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
             connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            addInterceptor(Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("app-key", "12345")
                    .addHeader("v", "1")
                    .build()
                chain.proceed(request)
            })
        }
    }

    private fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

}