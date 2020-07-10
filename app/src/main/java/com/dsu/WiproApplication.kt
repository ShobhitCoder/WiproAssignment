package com.dsu.wiproapplication

import android.app.Activity
import android.app.Application
import com.dsu.wiproapplication.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Shobhit Gupta on 10, July, 2020
 * fiitjeeshobhit@gmail.com
 */
class WiproApplication : Application(), HasActivityInjector {

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().appBuilder(this).buildAppComponent().inject(this)
    }
}