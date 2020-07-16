package com.dsu.wiproapplication.view.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dsu.wiproapplication.R
import com.dsu.wiproapplication.databinding.FragmentHomeBinding
import com.dsu.wiproapplication.model.FactResponse
import com.dsu.wiproapplication.navigator.HomeNavigator
import com.dsu.wiproapplication.view.home.HomeActivity
import com.dsu.wiproapplication.view.home.adapter.HomeAdapter
import com.dsu.wiproapplication.viewmodel.HomeViewModel
import com.dsu.wiproapplicationglobal.isNetworkConnected
import com.dsu.wiproapplicationglobal.showSnackBar


/**
 * A simple [Fragment] subclass.
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
class HomeFragment : Fragment(), HomeNavigator {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private var homeActivity: HomeActivity? = null
    private var homeAdapter: HomeAdapter? = null
    private var binding: FragmentHomeBinding? = null
    var homeViewModel: HomeViewModel? = null
    private var isError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            isError = it.getBoolean("isError")
        }

        homeActivity = activity as HomeActivity
        homeViewModel?.navigator = this
        homeAdapter = HomeAdapter()
        binding?.factRecyclerView?.adapter = homeAdapter
        binding?.factSwipeRefreshLayout?.setOnRefreshListener {
            getFactData()
        }

        observeData()
        if (homeViewModel?.factRowsListResponse?.value == null)
            getFactData()
        if (isError)
            homeViewModel?.cancelRequest()
    }

    /**
     * Get data from server if network available
     */
    private fun getFactData() {
        isNetworkConnected {
            when {
                it -> {
                    homeViewModel?.getFactData()
                }
                else -> {
                    onRefresh(false)
                    onNetworkError()
                }
            }
        }
    }

    /**
     * Subscribe observe so we detect data when any changes in live data
     */
    private fun observeData() {
        homeViewModel?.factRowsListResponse?.observe(this, Observer<FactResponse> { it ->
            it?.let {
                homeAdapter?.addItems(homeViewModel!!.checkData(it.rows))
            }
        })
    }

    override fun onRefresh(isRefresh: Boolean) {
        binding?.factSwipeRefreshLayout?.isRefreshing = isRefresh
    }

    override fun onUnknownErrorCode(statusCode: Int, errorMessage: String) {
        binding?.factRecyclerView.showSnackBar(errorMessage)
        homeActivity?.onUnknownErrorCode = true
    }

    override fun onUnknownError(errorMessage: String) {
        binding?.factRecyclerView.showSnackBar(errorMessage)
        homeActivity?.onUnknownError = true
    }

    override fun onTimeout() {
        binding?.factRecyclerView.showSnackBar(activity?.getString(R.string.requestFailed))
        homeActivity?.onTimeout = true
    }

    override fun onNetworkError() {
        binding?.factRecyclerView.showSnackBar(activity?.getString(R.string.noInternet))
        homeActivity?.onNetworkError = true
    }
}
