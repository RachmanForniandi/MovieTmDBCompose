package rachman.forniandi.movietmdbcompose.di


import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import rachman.forniandi.movietmdbcompose.BuildConfig.BASE_URL
import rachman.forniandi.movietmdbcompose.data.remote.APIService
import rachman.forniandi.movietmdbcompose.data.remote.source.RemoteDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        /*val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }*/
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideCheckerInterceptor(@ApplicationContext context: Context, chuckerCollector: ChuckerCollector): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .alwaysReadResponseBody(true)
            .maxContentLength(250_000L).build()
    }


    @Provides
    @Singleton
    fun provideCheckerCollector(@ApplicationContext context: Context): ChuckerCollector {
        return ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTmdBApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: APIService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

}