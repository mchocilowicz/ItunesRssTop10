package marmar.com.top10.activity

import android.support.v7.app.AppCompatActivity
import marmar.com.top10.di.ApplicationComponent
import marmar.com.top10.initialization.MyApplication

open class BaseActivity: AppCompatActivity() {

    fun getInjector(): ApplicationComponent {
        return getApplicationComponent()
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (application as MyApplication).component
    }
}