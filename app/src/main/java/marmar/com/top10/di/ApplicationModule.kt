package marmar.com.top10.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import marmar.com.top10.ItunesRssService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
public class ApplicationModule(val app: Application) {

    @Provides
    @Singleton
    fun getContext(): Context = app

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun getItunesRSSApi(retrofit: Retrofit): ItunesRssService {
        return retrofit
                .create(ItunesRssService::class.java)
    }

}