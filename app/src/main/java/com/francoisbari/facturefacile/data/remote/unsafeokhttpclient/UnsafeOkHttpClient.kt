package com.francoisbari.facturefacile.data.remote.unsafeokhttpclient

import android.annotation.SuppressLint
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object UnsafeOkHttpClient {

    private val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    })

    private val unsafeSslContext = SSLContext.getInstance("SSL")
        .apply {
            init(null, trustAllCerts, java.security.SecureRandom())
        }

    private val interceptor = okhttp3.logging.HttpLoggingInterceptor().apply {
        level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
    }

    fun getUnsafeOkHttpClient(): okhttp3.OkHttpClient {
        return okhttp3.OkHttpClient.Builder()
            .sslSocketFactory(
                unsafeSslContext.socketFactory,
                trustAllCerts[0] as X509TrustManager
            )
            .addInterceptor(interceptor)
            .hostnameVerifier { _, _ -> true }
            .build()
    }
}