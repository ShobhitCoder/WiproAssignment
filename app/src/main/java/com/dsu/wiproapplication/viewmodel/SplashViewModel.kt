package com.dsu.wiproapplication.viewmodel

import com.dsu.wiproapplication.api.AppAPIs
import com.dsu.wiproapplication.global.AppConstants
import com.dsu.wiproapplication.navigator.ActivityNavigator
import com.wipro.assessment.global.rxjava.SchedulerProvider
import com.wipro.assessment.view.base.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Shobhit Gupta on 10, July, 2020
 * fiitjeeshobhit@gmail.com
 */
class SplashViewModel(appAPIs: AppAPIs, schedulerProvider: SchedulerProvider) :
    BaseViewModel<ActivityNavigator>(appAPIs, schedulerProvider) {
    init {
        delayScreen()
    }

    /**
     * Delay splash screen for some time
     */
    private fun delayScreen() {
        Single.fromCallable {}
            .subscribeOn(schedulerProvider.io())
            .delay(
                AppConstants.SPLASH_DELAY.toLong(),
                TimeUnit.MILLISECONDS,
                AndroidSchedulers.mainThread()
            )
            .doOnSuccess {  }
            .subscribe()
    }
}