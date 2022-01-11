package io.github.ovso.domain.usecase

import io.github.ovso.domain.repository.LullabyRepository

interface LullabyUseCase

class LullabyUseCaseImpl(
  private val repository: LullabyRepository
) : LullabyUseCase {

}
