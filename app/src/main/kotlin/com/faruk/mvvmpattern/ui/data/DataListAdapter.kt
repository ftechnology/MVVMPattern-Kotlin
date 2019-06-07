package com.faruk.mvvmpattern.ui.data

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.faruk.mvvmpattern.R
import com.faruk.mvvmpattern.databinding.DataItemBinding
import com.faruk.mvvmpattern.model.Data

class DataListAdapter: RecyclerView.Adapter<DataListAdapter.ViewHolder>() {
    private lateinit var dataList:List<Data>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListAdapter.ViewHolder {
        val binding: DataItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.data_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataListAdapter.ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return if(::dataList.isInitialized) dataList.size else 0
    }

    fun updatePostList(postList:List<Data>){
        this.dataList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: DataItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = DataViewModel()

        fun bind(Data:Data){
            viewModel.bind(Data)
            binding.viewModel = viewModel
        }
    }
}