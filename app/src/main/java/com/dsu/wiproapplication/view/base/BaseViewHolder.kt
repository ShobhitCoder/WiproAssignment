package com.wipro.assessment.view.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Sobhit on 08/13/19.
 */

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(position: Int)
}
