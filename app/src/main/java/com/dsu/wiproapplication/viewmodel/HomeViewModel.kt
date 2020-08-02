package com.dsu.wiproapplication.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.dsu.wiproapplication.api.AppAPIs
import com.dsu.wiproapplication.api.error.GetRetroFitError
import com.dsu.wiproapplication.model.FactResponse
import com.dsu.wiproapplication.model.FactRows
import com.dsu.wiproapplication.navigator.HomeNavigator
import com.dsu.wiproapplicationglobal.isNotEmptyAndNull
import com.wipro.assessment.global.rxjava.SchedulerProvider
import com.wipro.assessment.view.base.BaseViewModel
import io.reactivex.disposables.Disposable

/**
 * Created by Shobhit Gupta on 10, July, 2020
 * fiitjeeshobhit@gmail.com
 */
class HomeViewModel(appAPIs: AppAPIs, schedulerProvider: SchedulerProvider) :
    BaseViewModel<HomeNavigator>(appAPIs, schedulerProvider) {
    var title = ObservableField("Fact")
    var factRowsListResponse: MutableLiveData<FactResponse>? = null

    init {
        factRowsListResponse = MutableLiveData()
    }

    /**
     * Get fact data from server
     */
    private var disposable: Disposable? = null

    fun getFactData() {
        mNavigator?.onRefresh(true)
        disposable = appAPIs.getFactData()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ response ->
                mNavigator?.onRefresh(false)
                factRowsListResponse?.value = response
                title.set(response.title)
            }, { throwable ->
                mNavigator?.onRefresh(false)
                GetRetroFitError(mNavigator, throwable)
            }, {

            })
        compositeDisposable.add(disposable!!)
    }

    fun cancelRequest() {
        disposable?.dispose()
        mNavigator?.onRefresh(false)
        mNavigator?.onTimeout()
    }

    /**
     * Verify server data, if all key data coming blank, will not add
     */
    fun checkData(factRowsList: List<FactRows>): List<FactRows> {
        val list = ArrayList<FactRows>()
        if (!factRowsList.isNullOrEmpty()) {
            for (element in factRowsList) {
                if (element.title.isNotEmptyAndNull() ||
                    element.description.isNotEmptyAndNull() ||
                    element.imageHref.isNotEmptyAndNull()
                ) list.add(element)
            }
        }
        return list
    }
}