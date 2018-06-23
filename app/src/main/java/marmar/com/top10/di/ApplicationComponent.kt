package marmar.com.top10.di

import dagger.Component
import marmar.com.top10.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
public interface ApplicationComponent {
    fun inject(activity: MainActivity)
}