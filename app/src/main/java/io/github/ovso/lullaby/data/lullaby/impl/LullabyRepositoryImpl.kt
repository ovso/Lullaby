package io.github.ovso.lullaby.data.lullaby.impl

import android.content.Context
import io.github.ovso.lullaby.data.LullabyModel
import io.github.ovso.lullaby.data.Result
import io.github.ovso.lullaby.data.lullaby.LullabyRepository
import io.github.ovso.lullaby.data.mapper.LullabyMapper
import io.github.ovso.lullaby.data.response.Response
import io.github.ovso.lullaby.utils.addOrRemove
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader

class LullabyRepositoryImpl(
    private val context: Context,
    private val mapper: LullabyMapper,
) : LullabyRepository {

    // for now, keep the selections in memory
    private val selected = MutableStateFlow(setOf<LullabyModel>())

    // Used to make suspend functions that read and update state safe to call from any thread
    private val mutex = Mutex()

    @Suppress("BlockingMethodInNonBlockingContext")
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getLullabies(): Result<List<LullabyModel>> =
        withContext(Dispatchers.Default) {
            try {
                // 로컬라이제이션 할 부분이다.
                val inputStream = context.assets.open("lullabies/lullabies.json")
                val stringJson = inputStream.bufferedReader().use(BufferedReader::readText)
                val response = Json.decodeFromString<Response>(stringJson)
                val result = mapper.mapFromList(response.items)
                return@withContext Result.Success(result)
            } catch (e: Exception) {
                return@withContext Result.Error(Exception(""))
            }
        }

    override suspend fun toggleSelection(model: LullabyModel) =
        withContext(Dispatchers.Default) {
            mutex.withLock {
                val set = selected.value.toMutableSet().apply {
                    removeAll {
                        model != it
                    }
                }
                set.addOrRemove(model)
                selected.value = set
            }
        }

    override fun observeSelected(): Flow<Set<LullabyModel>> = selected
}
