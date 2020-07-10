package com.wipro.assessment.global.rxjava

import androidx.annotation.NonNull
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
class AppSchedulerProvider : SchedulerProvider {

    @NonNull
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    @NonNull
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    @NonNull
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
