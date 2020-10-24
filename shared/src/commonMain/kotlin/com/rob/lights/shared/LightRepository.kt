package com.rob.lights.shared

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class LightRepository {

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getLights() : Lights =
        httpClient.get(LIGHTS_ENDPOINT)

    companion object {
        private const val LIGHTS_ENDPOINT =
            "https://ktor-hello-world-wdjfakho4a-uc.a.run.app/esblights"
    }

}

