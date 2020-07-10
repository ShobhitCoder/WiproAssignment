package com.wipro.assessment.global.rxjava

import androidx.annotation.NonNull
import io.reactivex.Scheduler

/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
interface SchedulerProvider {
    @NonNull
    fun computation(): Scheduler

    @NonNull
    fun io(): Scheduler

    @NonNull
    fun ui(): Scheduler
}
