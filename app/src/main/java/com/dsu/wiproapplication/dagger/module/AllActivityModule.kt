package com.wipro.assessment.dagger.module

import com.dsu.wiproapplication.api.AppAPIs
import com.dsu.wiproapplication.viewmodel.HomeViewModel
import com.dsu.wiproapplication.viewmodel.SplashViewModel
import com.wipro.assessment.global.rxjava.SchedulerProvider
import dagger.Module
import dagger.Provides


/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
@Module
class AllActivityModule {

    @Provides
    internal fun provideSplashViewModel(appAPIs: AppAPIs, schedulerProvider: SchedulerProvider): SplashViewModel {
        return SplashViewModel(appAPIs, schedulerProvider)
    }

    @Provides
    internal fun provideHomeViewModel(appAPIs: AppAPIs, schedulerProvider: SchedulerProvider): HomeViewModel {
        return HomeViewModel(appAPIs, schedulerProvider)
    }
}