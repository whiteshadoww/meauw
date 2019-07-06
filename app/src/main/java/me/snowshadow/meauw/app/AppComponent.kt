package me.snowshadow.meauw.app

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.snowshadow.meauw.activities.MainActivityModule
import me.snowshadow.meauw.utils.di.ViewModelModule
import javax.inject.Singleton


@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        MainActivityModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(application: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(appContext: Context): Builder

        fun build(): AppComponent
    }

}


