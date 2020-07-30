package com.isidroid.b21.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.isidroid.b21.App
import com.isidroid.b21.clean.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
        MvpModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}


fun Context.appComponent() = (applicationContext as App).appComponent
fun Fragment.appComponent() = requireActivity().appComponent()