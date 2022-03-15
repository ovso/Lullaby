package io.github.ovso.data.lullaby

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.ovso.domain.Lullaby
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.IOException
import javax.inject.Inject

interface ResProvider {
  suspend fun getLullabies(
    path: String,
    name: String
  ): List<Lullaby>
}

class ResProviderImpl @Inject constructor(
  @ApplicationContext private val context: Context,
) : ResProvider {
  override suspend fun getLullabies(
    path: String,
    name: String,
  ): List<Lullaby> {
    return withContext(Dispatchers.IO) {
      try {
        val inputStream = context.assets.open("$path/$name")
        val use = inputStream.bufferedReader().use(BufferedReader::readText)
        Json.decodeFromString<List<LullabyResponse>>(use).map { it.toLullaby() }
      } catch (e: IOException) {
        emptyList()
      }
    }
  }

}
