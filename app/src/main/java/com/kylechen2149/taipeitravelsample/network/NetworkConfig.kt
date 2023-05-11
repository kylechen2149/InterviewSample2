package com.kylechen2149.taipeitravelsample.network

import com.kylechen2149.taipeitravelsample.utils.BASE_URL
import com.kylechen2149.taipeitravelsample.utils.FlowCallAdapterFactory
import com.kylechen2149.taipeitravelsample.utils.KEY_HEADER
import com.kylechen2149.taipeitravelsample.utils.KEY_HEADER_VALUE
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun getTokenInterceptor() = Interceptor { chain ->
    val request = chain.request().newBuilder()
        .addHeader(KEY_HEADER, KEY_HEADER_VALUE)
        .build()

    return@Interceptor chain.proceed(request)
}

private fun getOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(getHttpLoggingInterceptor())
    .addInterceptor(getTokenInterceptor())
    .build()

private fun getMoshiConverterFactory(moshi: Moshi? = null): MoshiConverterFactory {
    return moshi?.let { MoshiConverterFactory.create(it) } ?: MoshiConverterFactory.create()
}

fun getRetrofit(moshi: Moshi? = null): Retrofit = Retrofit.Builder()
    .client(getOkHttpClient())
    .baseUrl(BASE_URL)
    .addConverterFactory(getMoshiConverterFactory(moshi))
    .addCallAdapterFactory(FlowCallAdapterFactory.create())
    .build()