package com.dsu.wiproapplication.navigator

/**
 * Created by Shobhit Gupta on 10, July, 2020
 * fiitjeeshobhit@gmail.com
 */
interface HomeNavigator : ErrorNavigator {

    fun onRefresh(isRefresh: Boolean)
}