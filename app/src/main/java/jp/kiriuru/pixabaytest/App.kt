package jp.kiriuru.pixabaytest

import android.app.Application
import android.content.Context
import jp.kiriuru.pixabaytest.di.AppComponent
import jp.kiriuru.pixabaytest.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }