package marmar.com.top10.initialization

import android.app.Application
import marmar.com.top10.di.ApplicationComponent
import marmar.com.top10.di.ApplicationModule
import marmar.com.top10.di.DaggerApplicationComponent

class MyApplication: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = initDagger(this)
    }

    private fun initDagger(app: MyApplication): ApplicationComponent {
        return DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}