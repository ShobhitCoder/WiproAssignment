package com.dsu.wiproapplication.api

import com.dsu.wiproapplication.model.FactResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers


/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
interface AppAPIs {
    @Headers("Content-Type: application/json")
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFactData(): Observable<FactResponse>
}