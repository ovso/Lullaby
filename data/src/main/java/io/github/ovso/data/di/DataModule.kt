package io.github.ovso.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import io.github.ovso.data.lullaby.LullabyRepositoryImpl
import io.github.ovso.data.lullaby.LullabyService
import io.github.ovso.domain.repository.LullabyRepository
import io.github.ovso.domain.usecase.LullabyUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

  @Singleton
  @Provides
  fun providesLullabyRepository(
    service: LullabyService
  ): LullabyRepository {
    return LullabyRepositoryImpl(
      service = service,
    )
  }
}

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
  @Provides
  fun providesUseCase(repository: LullabyRepository): LullabyUseCase = LullabyUseCase(repository)
}
