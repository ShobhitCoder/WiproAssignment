package com.dsu.wiproapplication.viewmodel

import androidx.databinding.ObservableField
import com.dsu.wiproapplication.model.FactRows

/**
 * Created by Shobhit Gupta on 10, July, 2020
 * fiitjeeshobhit@gmail.com
 */
class AdapterHomeViewModel(factRows: FactRows) {
    var title = ObservableField(factRows.title)
    var description = ObservableField(factRows.description)
    var imageUrl = ObservableField(factRows.imageHref)
}