package net.android.delorotheather.injection

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import net.android.delorotheather.database.AppDataBase
import net.android.delorotheather.database.PostDao
import net.android.delorotheather.networking.Api
import net.android.delorotheather.viewmodel.MyViewModel
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

var networkingModule = module {

    fun apiProvider(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    fun networkingProvider(): Retrofit {


        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    single { apiProvider(get()) }
    single { networkingProvider() }
}

var appdataBase = module {

    fun appDataBaseProvider(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java, "post")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun postDaoProvider(appDataBase: AppDataBase): PostDao {
        return appDataBase.postDao()
    }

    single { appDataBaseProvider(androidApplication()) }
    single { postDaoProvider(get()) }
}

var viewModelModule = module {

    viewModel {
        MyViewModel(get(), get())
    }
}

