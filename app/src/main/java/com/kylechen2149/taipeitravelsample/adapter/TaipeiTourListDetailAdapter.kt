package com.kylechen2149.taipeitravelsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kylechen2149.taipeitravelsample.R
import com.kylechen2149.taipeitravelsample.databinding.ItemTaipeiTourBinding
import com.kylechen2149.taipeitravelsample.main.model.TaipeiTourResponse
import com.kylechen2149.taipeitravelsample.main.viewmodel.TaipeiTourViewModel
import timber.log.Timber

class TaipeiTourListDetailAdapter(
    private val items: MutableList<TaipeiTourResponse>,
    private val viewModel: TaipeiTourViewModel
) : RecyclerView.Adapter<TaipeiTourListDetailAdapter.TaipeiTourListViewHolder>() {

    class TaipeiTourListViewHolder(
        private val binding: ItemTaipeiTourBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: TaipeiTourResponse, viewModel: TaipeiTourViewModel) {
            binding.item = item
            binding.viewModel = viewModel
            binding.imageUrl = item.getFirstPic()
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaipeiTourListViewHolder {
        val binding = DataBindingUtil.inflate<ItemTaipeiTourBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_taipei_tour,
            parent,
            false
        )
        return TaipeiTourListViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: TaipeiTourListViewHolder, position: Int) {
        holder.bind(items[position], viewModel)
    }

    // return the number of the items in the list
    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position
}