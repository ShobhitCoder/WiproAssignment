package com.dsu.wiproapplication.dagger.builder

import com.dsu.wiproapplication.view.splash.SplashActivity
import com.wipro.assessment.dagger.module.AllActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Shobhit Gupta on 11, December, 2019.
 * fiitjeeshobhit@gmail.com
 */
@Module
abstract class AllActivityBuilder {
    @ContributesAndroidInjector(modules = [AllActivityModule::class])
    internal abstract fun bindSplashActivity(): SplashActivity
}