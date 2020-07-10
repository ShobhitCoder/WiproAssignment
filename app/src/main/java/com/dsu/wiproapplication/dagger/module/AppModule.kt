package com.wipro.assessment.dagger.module

import android.app.Application
import android.content.Context
import com.wipro.assessment.global.rxjava.AppSchedulerProvider
import com.wipro.assessment.global.rxjava.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
@Module
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}