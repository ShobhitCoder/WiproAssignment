package com.dsu.wiproapplication.dagger.component

import android.app.Application
import com.dsu.wiproapplication.WiproApplication
import com.dsu.wiproapplication.dagger.builder.AllActivityBuilder
import com.wipro.assessment.dagger.module.AppModule
import com.wipro.assessment.dagger.module.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, RetrofitModule::class, AllActivityBuilder::class])
interface AppComponent {

    fun inject(mApplication: WiproApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appBuilder(application: Application): Builder

        fun buildAppComponent(): AppComponent

    }
}

