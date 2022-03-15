package io.github.ovso.data.di

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
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
  fun providesRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder().apply {
      baseUrl(baseUrl)
      client(client)
      addConverterFactory(GsonConverterFactory.create())
    }.build()
  }

  @Provides
  fun providesBaseUrl() =
    "https://blogattach.naver.com/5bce47f7e5b9bf634fadcffbc3215e2388d32dcca6/20220315_281_blogfile/ovso_1647271814804_5V3fR3_json/"

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

  @Provides
  fun providesRemoteConfig(): FirebaseRemoteConfig {
    return Firebase.remoteConfig.apply {
      val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 0
      }
      setConfigSettingsAsync(configSettings)
      setDefaultsAsync(mapOf("lullaby" to "empty"))
    }
  }
}
