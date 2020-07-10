package com.dsu.wiproapplication.model


/**
 * Created by Shobhit Gupta on 10, July, 2020.
 * fiitjeeshobhit@gmail.com
 */
data class FactResponse(
    val rows: List<FactRows>,
    val title: String
)

data class FactRows(
    val description: String,
    val imageHref: String,
    val title: String
)