package io.github.ovso.data.lullaby

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.IOException
import javax.inject.Inject

interface ResProvider {
  suspend fun getLullabies(
    path: String,
    name: String
  ): List<LullabySectionResponse>
}

class ResProviderImpl @Inject constructor(
  @ActivityContext private val context: Context,
) : ResProvider {
  override suspend fun getLullabies(
    path: String,
    name: String,
  ): List<LullabySectionResponse> {
    return try {
      val inputStream = context.assets.open("$path/$name")
      val use = inputStream.bufferedReader().use(BufferedReader::readText)
      Json.decodeFromString<Response>(use).lullabies
    } catch (e: IOException) {
      emptyList()
    }
  }
}
