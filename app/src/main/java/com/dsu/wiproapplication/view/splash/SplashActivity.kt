package com.dsu.wiproapplication.view.splash

import android.content.Intent
import android.os.Bundle
import com.dsu.wiproapplication.BR
import com.dsu.wiproapplication.R
import com.dsu.wiproapplication.databinding.ActivitySplashBinding
import com.dsu.wiproapplication.navigator.ActivityNavigator
import com.dsu.wiproapplication.viewmodel.SplashViewModel
import com.wipro.assessment.view.base.BaseActivity
import javax.inject.Inject

/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(),
    ActivityNavigator {
    override fun startActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
        finish()
    }

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() = mSplashViewModel

    //Field injection
    @Inject
    lateinit var mSplashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSplashViewModel.mNavigator = this
    }

}
