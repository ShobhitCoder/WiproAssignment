package com.dsu.wiproapplication.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsu.wiproapplication.databinding.RowHomeBinding
import com.dsu.wiproapplication.model.FactRows
import com.dsu.wiproapplication.view.base.BaseViewHolder
import com.dsu.wiproapplication.viewmodel.AdapterHomeViewModel

class HomeAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    private var factRowsList: MutableList<FactRows> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding: RowHomeBinding = RowHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * Add data in list and notify adapter
     */
    fun addItems(factRowsList: List<FactRows>) {
        this.factRowsList = ArrayList()
        this.factRowsList.addAll(factRowsList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return factRowsList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ItemViewHolder(binding: RowHomeBinding) : BaseViewHolder(binding.root) {
        var rowBinding = binding
        private var adapterHomeViewModel: AdapterHomeViewModel? = null
        override fun onBind(position: Int) {
            adapterHomeViewModel = AdapterHomeViewModel(factRowsList[position])
            rowBinding.viewModel = adapterHomeViewModel
            rowBinding.executePendingBindings()
        }
    }
}