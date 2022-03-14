package io.github.ovso.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.ovso.data.lullaby.LullabyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

  @Provides
  fun providesRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder().apply {
      baseUrl("https://blogattach.naver.com/0d9811a2b8efe93519fb99ad95750873df847e9a77/20220314_264_blogfile/ovso_1647262504720_rwobRr_json/")
      client(client)
      addConverterFactory(GsonConverterFactory.create())
    }.build()
  }

  @Provides
  fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder().apply {
      addInterceptor(loggingInterceptor)
    }.build()
  }

  @Provides
  fun providesLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      setLevel(HttpLoggingInterceptor.Level.BASIC)
    }
  }

  @Singleton
  @Provides
  fun providesLullabyService(retrofit: Retrofit): LullabyService {
    return retrofit.create()
  }
}
