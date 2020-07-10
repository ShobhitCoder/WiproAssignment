package com.dsu.wiproapplication

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dsu.wiproapplication.view.home.HomeActivity
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * Created by Sobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    private var recyclerViewMatcher: RecyclerViewMatcher? = null

    @get:Rule
    var homeActivity: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java, false, false)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        recyclerViewMatcher = RecyclerViewMatcher(R.id.factRecyclerView)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    /*
    * Test if the recyclerView content is populated with the desired data on Successful data load
    * */
    @Test
    fun assertScreenStateSuccess() {
        homeActivity.launchActivity(null)

        /**
         * Assuming that 5 sec will be enough for network response
         * Ideally IdlingResource should have been used instead of Thread.sleep(),
         * but somehow it wasn't working and due to restricted time frame, it has been used.
         *
         */
        Thread.sleep(5000)

        onView(withId(R.id.toolBar)).check(matches(isDisplayed()))

        onView(
            withRecyclerView(R.id.factRecyclerView)
                .atPositionOnView(0, R.id.titleTextView)
        )
            .check(matches(withText("Beavers")))

        onView(
            withRecyclerView(R.id.factRecyclerView)
                .atPositionOnView(0, R.id.factImageView)
        ).check(matches(isDisplayed()))

        onView(
            withRecyclerView(R.id.factRecyclerView)
                .atPositionOnView(0, R.id.descriptionTextView)
        )
            .check(matches(isDisplayed()))
    }

    /*
    * Test if the recycler is present and nothing gets populated until the request is succeeded
    * Test If the error condition are being triggered successfully
    */
    @Test
    fun assertScreenStateFailure() {
        homeActivity.launchActivity(Intent().putExtra("isError", true))
        Thread.sleep(2000)
        //UI test
        onView(withId(R.id.toolBar)).check(matches(isDisplayed()))

        onView(withId(R.id.factRecyclerView)).check(matches(isDisplayed()))

        assertThat(getRecyclerViewCount(), `is`(0))

        assert(
            homeActivity.activity.onUnknownError or homeActivity.activity.onUnknownErrorCode
                    or homeActivity.activity.onNetworkError or homeActivity.activity.onTimeout
        )
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    private fun getRecyclerViewCount(): Int {
        val recyclerView = homeActivity.activity.findViewById(R.id.factRecyclerView) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }
}
