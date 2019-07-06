package me.snowshadow.meauw.app

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import me.snowshadow.meauw.BuildConfig
import me.snowshadow.meauw.repo.database.DataBase
import me.snowshadow.meauw.repo.network.CatService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesOkHttp3(): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.HOURS)
            .addInterceptor { chain ->
                val req = chain.request()
                    .newBuilder()
                    .addHeader("x-api-key", BuildConfig.X_API_KEY)
                    .build()
                chain.proceed(req)
            }.build()

    }

    @Provides
    @Singleton
    fun providesApi(okHttpClient: OkHttpClient): CatService {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(Config.apiUrl)
            .client(okHttpClient)
            .build().create(CatService::class.java)

    }


    @Provides
    @Singleton
    fun providesDataBase(context: Context): DataBase {

        val room = Room
            .databaseBuilder(context, DataBase::class.java, "meauw.db")

        return with(room) {
            allowMainThreadQueries()
            build()
        }

    }

}
