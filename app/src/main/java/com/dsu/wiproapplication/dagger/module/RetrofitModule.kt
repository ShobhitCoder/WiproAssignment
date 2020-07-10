package com.wipro.assessment.dagger.module

import com.dsu.wiproapplication.BuildConfig
import com.dsu.wiproapplication.api.AppAPIs
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
@Module
class RetrofitModule {

    internal val httpLoggingInterceptor: HttpLoggingInterceptor
        @Provides
        @Singleton
        get() = HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        )

    internal val gson: Gson
        @Provides
        @Singleton
        get() = GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Provides
    @Singleton
    internal fun getApiInterface(retroFit: Retrofit): AppAPIs {
        return retroFit.create(AppAPIs::class.java)
    }

    @Provides
    @Singleton
    internal fun getRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build()
    }
}
