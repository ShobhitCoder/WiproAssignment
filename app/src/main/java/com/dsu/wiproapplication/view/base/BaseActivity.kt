package com.wipro.assessment.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection


/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = mViewModel ?: viewModel
        viewDataBinding?.setVariable(bindingVariable, mViewModel)
        viewDataBinding?.executePendingBindings()
    }
}

