package io.github.ovso.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import io.github.ovso.data.lullaby.LullabyMapper
import io.github.ovso.data.lullaby.ResProvider
import io.github.ovso.data.lullaby.ResProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

  @Singleton
  @Provides
  fun providesResProvider(@ActivityContext context: Context): ResProvider = ResProviderImpl(context)

  @Singleton
  @Provides
  fun providesLullabyMapper(): LullabyMapper = LullabyMapper()


}
