package com.dsu.wiproapplication.navigator


/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
interface ActivityNavigator {
    /**
     * It's use for pass reference navigation activity
     */
    fun startActivity(activity: Class<*>)
}
