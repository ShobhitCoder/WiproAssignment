package com.dsu.wiproapplication.dagger.builder

import com.dsu.wiproapplication.view.home.HomeActivity
import com.dsu.wiproapplication.view.splash.SplashActivity
import com.wipro.assessment.dagger.module.ViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
@Module
abstract class AllActivityBuilder {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity
}