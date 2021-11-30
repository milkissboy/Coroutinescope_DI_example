package com.hyk.googlejsonfile.repository

import android.annotation.SuppressLint
import android.os.Build
import com.hyk.googlejsonfile.BuildConfig
import com.hyk.googlejsonfile.repository.client.NullOnEmptyConverterFactory
import com.hyk.googlejsonfile.w
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import com.google.gson.GsonBuilder

import com.google.gson.Gson




object NetRetrofit {

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    }

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_ADDRESS)
        .client(provideClientDevice())
        .addConverterFactory(NullOnEmptyConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson)) //파싱등록
        .build()

    private fun provideClientDevice(): OkHttpClient {
        val httpClientBuilder: OkHttpClient.Builder = getOkHttpBuilder()
        httpClientBuilder.addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            w("req - url " + request.url)
            chain.proceed(request)
        }.addInterceptor(httpLoggingInterceptor)
        return httpClientBuilder.build()
    }

    private fun getOkHttpBuilder(): OkHttpClient.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            OkHttpClient().newBuilder()
        } else {
            // Workaround for the error
            // "Caused by: com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException:
            // Could not validate certificate: Certificate expired at".
            getUnsafeOkHttpClient()
        }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf(
                @SuppressLint("CustomX509TrustManager")
                object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<out java.security.cert.X509Certificate>?,
                        authType: String?
                    ) = Unit

                    override fun checkServerTrusted(
                        chain: Array<out java.security.cert.X509Certificate>?,
                        authType: String?
                    ) = Unit

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> =
                        arrayOf()
                }
            )
            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(
                sslSocketFactory,
                trustAllCerts[0] as X509TrustManager
            )
            builder.hostnameVerifier { _, _ -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
}