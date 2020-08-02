package com.dsu.wiproapplication.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsu.wiproapplication.databinding.RowHomeBinding
import com.dsu.wiproapplication.model.FactRows
import com.dsu.wiproapplication.view.base.BaseViewHolder
import com.dsu.wiproapplication.viewmodel.AdapterHomeViewModel

class HomeAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    private var mFactRowsList: MutableList<FactRows> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding: RowHomeBinding =
            RowHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * Add data in list and notify adapter
     */
    fun addItems(mFactRowsList: List<FactRows>) {
        this.mFactRowsList = ArrayList()
        this.mFactRowsList.addAll(mFactRowsList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mFactRowsList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ItemViewHolder(binding: RowHomeBinding) : BaseViewHolder(binding.root) {
        var rowBinding = binding
        private var adapterHomeViewModel: AdapterHomeViewModel? = null
        override fun onBind(position: Int) {
            adapterHomeViewModel = AdapterHomeViewModel(mFactRowsList[position])
            rowBinding.viewModel = adapterHomeViewModel
            rowBinding.executePendingBindings()
        }
    }
}