package com.example.pagingpractice.di

import android.content.Context
import com.example.pagingpractice.api.ApiService
import com.example.pagingpractice.repository.QuotesRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.quotable.io/"

    @Singleton
    @Provides
    fun getContext(@ApplicationContext context: Context): Context {
        return context
    }

    var gson = GsonBuilder().setLenient().create()

    fun provideHttpLoggingInterCeptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder =
        OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request: Request = chain.request().newBuilder()
                    .build()
                chain.proceed(request)

            }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService) = QuotesRepository(apiService)

   /* @Singleton
    @Provides
    fun getAppDataBase(@ApplicationContext context: Context): QuotesDataBase {
        return QuotesDataBase.getDatabase(context)
    }*/

   /* @Singleton
    @Provides
    fun getAppDao(appDataBase: QuotesDataBase): QuotesDao {
        return appDataBase.getQuotesDao()
    }
*/

}