package com.jbs.ttechnique.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jbs.ttechnique.BuildConfig
import com.jbs.ttechnique.data.local.AppDatabase
import com.jbs.ttechnique.data.local.UserDao
import com.jbs.ttechnique.data.model.Id
import com.jbs.ttechnique.data.model.User
import com.jbs.ttechnique.data.model.UserResponse
import com.jbs.ttechnique.data.remote.*
import com.jbs.ttechnique.repository.UsersRepository
import com.jbs.ttechnique.utils.CONNECT_TIMEOUT
import com.jbs.ttechnique.utils.READ_TIMEOUT
import com.jbs.ttechnique.utils.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    private val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .setLenient()
        .excludeFieldsWithoutExposeAnnotation()
        .registerTypeAdapter(Id::class.java, IdDeserializer())
        .registerTypeAdapter(UserResponse::class.java, UserListDeserializer())
        .registerTypeAdapter(User::class.java, UserDeserializer())
        .create()


    @Provides
    fun provideUsersService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideUsersRemoteDataSource(characterService: UserService) = UserRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: UserRemoteDataSource, localDataSource: UserDao) = UsersRepository(remoteDataSource, localDataSource)
}