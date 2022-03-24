package io.github.ovso.domain

sealed class Result<out T : Any> {
  object Loading : Result<Nothing>()
  data class Success<out T : Any>(val data: T) : Result<T>()
  data class Error<out T : Any>(val exception: Exception) : Result<T>()
}
