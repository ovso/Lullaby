package io.github.ovso.data.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.ovso.data.lullaby.LullabyMapper
import io.github.ovso.data.lullaby.LullabyRepositoryImpl
import io.github.ovso.data.lullaby.ResProvider
import io.github.ovso.data.lullaby.ResProviderImpl
import io.github.ovso.domain.repository.LullabyRepository
import io.github.ovso.domain.usecase.LullabyUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

  @Singleton
  @Provides
  fun providesResProvider(@ApplicationContext context: Context): ResProvider =
    ResProviderImpl(context)

  @Singleton
  @Provides
  fun providesLullabyMapper(): LullabyMapper = LullabyMapper()

  @Singleton
  @Provides
  fun providesLullabyRepository(
    mapper: LullabyMapper,
    resProvider: ResProvider
  ): LullabyRepository {
    return LullabyRepositoryImpl(
      mapper = mapper,
      resProvider = resProvider
    )
  }
}

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
  @Provides
  fun providesUseCase(repository: LullabyRepository): LullabyUseCase = LullabyUseCase(repository)
}
