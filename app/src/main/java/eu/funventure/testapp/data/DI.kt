package eu.funventure.testapp.data

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.funventure.testapp.data.local.AppDatabase
import eu.funventure.testapp.data.remote.ApiInterface
import eu.funventure.testapp.domain.NumberRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRepository(
        api: ApiInterface,
        dataBase: AppDatabase
    ): NumberRepository {
        return NumberRepositoryImp(api = api, dataBase = dataBase.dataNumbers())
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): ApiInterface = Retrofit.Builder()
        .baseUrl("http://numbersapi.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                requestBuilder.header("Content-Type", "application/json")
                chain.proceed(requestBuilder.build())
            })
            .addInterceptor(interceptor).build()
    }


    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = AppDatabase::class.java, name = "DataNumbers"
        ).allowMainThreadQueries().build()
    }
}