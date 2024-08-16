package com.raxors.otakuhub.di

import android.util.Log
import com.raxors.otakuhub.data.api.ShikimoriOneApi
import com.raxors.otakuhub.data.api.ShikimoriOneApiImpl
import com.raxors.otakuhub.data.repository.OtakuHubRepositoryImpl
import com.raxors.otakuhub.domain.repository.OtakuHubRepository
import com.raxors.otakuhub.ui.screens.auth.AuthViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<OtakuHubRepository> { OtakuHubRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { AuthViewModel() }
}

val ktorModule = module {
    factory<ShikimoriOneApi> { ShikimoriOneApiImpl(get()) }

    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        useAlternativeNames = true
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    }
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }
                }
                level = LogLevel.ALL
            }
            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
            //TODO auth token set here
//            install(Auth) {
//
//            }
        }
    }
}

val appModule = module {
    includes(ktorModule, viewModelModule, repositoryModule)
}