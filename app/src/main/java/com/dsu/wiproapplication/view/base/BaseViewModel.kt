package com.wipro.assessment.view.base

import androidx.lifecycle.ViewModel
import com.dsu.wiproapplication.api.AppAPIs
import com.wipro.assessment.global.rxjava.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
abstract class BaseViewModel<N>(val appAPIs: AppAPIs, val schedulerProvider: SchedulerProvider) :
    ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var mNavigator: N? = null

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
