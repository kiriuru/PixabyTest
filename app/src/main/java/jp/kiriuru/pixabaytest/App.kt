package jp.kiriuru.pixabaytest

import android.app.Application
import jp.kiriuru.pixabaytest.di.AppComponent
import jp.kiriuru.pixabaytest.di.DaggerAppComponent

open class App : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)

    }
}
//
//    lateinit var appComponent: AppComponent
//        private set

//    override fun onCreate() {
//        super.onCreate()
//        appComponent = DaggerAppComponent.factory().create(applicationContext)
//    }
//}
//val Context.appComponent: AppComponent
//    get() = when (this) {
//        is App -> appComponent
//        else -> applicationContext.appComponent
//    }