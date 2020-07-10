package com.wipro.assessment.dagger.module

import com.dsu.wiproapplication.api.AppAPIs
import com.dsu.wiproapplication.viewmodel.SplashViewModel
import com.wipro.assessment.global.rxjava.SchedulerProvider
import dagger.Module
import dagger.Provides


/**
 * Created by Shobhit Gupta on 11, December, 2019.
 * fiitjeeshobhit@gmail.com
 */
@Module
class AllActivityModule {

    @Provides
    internal fun provideSplashViewModel(appAPIs: AppAPIs, schedulerProvider: SchedulerProvider): SplashViewModel {
        return SplashViewModel(appAPIs, schedulerProvider)
    }
}