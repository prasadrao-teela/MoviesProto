package com.goscale.assignment.di.component

import android.app.Application
import com.goscale.assignment.GoScaleApplication
import com.goscale.assignment.di.module.ActivityModule
import com.goscale.assignment.di.module.RetrofitModule
import com.goscale.assignment.di.module.RoomModule
import com.goscale.assignment.di.module.ViewModelModule
import com.goscale.assignment.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

/**
 * Created by Prasad Rao on 23-05-2020 13:07
 **/
@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        RetrofitModule::class,
        RoomModule::class
    ]
)
interface AppComponent {
    fun inject(context: GoScaleApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun roomDatabaseModule(roomModule: RoomModule): Builder

        fun build(): AppComponent
    }
}