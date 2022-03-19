package io.github.ovso.data.lullaby

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import io.github.ovso.domain.Lullaby
import io.github.ovso.domain.repository.LullabyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LullabyRepositoryImpl @Inject constructor(
  private val remoteConfig: FirebaseRemoteConfig,
) : LullabyRepository {

  private val selected = MutableStateFlow(setOf<Lullaby>())

  // Used to make suspend functions that read and update state safe to call from any thread
  private val mutex = Mutex()


  override suspend fun getLullabies(): List<Lullaby> {
    return withContext(Dispatchers.IO) {
      fetchItems()
    }
  }

  private val json = Json { ignoreUnknownKeys = true }

  private suspend fun fetchItems() = suspendCoroutine<List<Lullaby>> { continuation ->
    remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
      when (task.isSuccessful) {
        true -> {
          val jsonString = remoteConfig["lullaby2"].asString();
          json.decodeFromString<List<LullabyResponse>>(jsonString)
            .map { it.toLullaby() }.also { continuation.resume(it) }
        }
        else -> continuation.resume(emptyList())
      }
    }

  }

  override suspend fun toggleSelection(item: Lullaby) =
    withContext(Dispatchers.Default) {
      mutex.withLock {
        val set = selected.value.toMutableSet().apply {
          removeAll {
            item != it
          }
        }
        set.addOrRemove(item)
        selected.value = set
      }
    }

  override fun observeSelected(): Flow<Set<Lullaby>> = selected
}
