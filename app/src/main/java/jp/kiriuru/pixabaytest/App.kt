package jp.kiriuru.pixabaytest

import android.app.Application
import jp.kiriuru.pixabaytest.di.AppComponent
import jp.kiriuru.pixabaytest.di.DaggerAppComponent

open class App : Application() {

    //Example from https://github.com/android/architecture-samples/tree/dev-dagger
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)

    }
}
