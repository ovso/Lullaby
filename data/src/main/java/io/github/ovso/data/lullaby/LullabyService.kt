package io.github.ovso.data.lullaby

import retrofit2.http.GET

interface LullabyService {

  @GET("data.json")
  suspend fun get(): Response
}
