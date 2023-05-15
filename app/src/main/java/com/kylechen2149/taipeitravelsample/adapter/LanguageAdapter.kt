package com.kylechen2149.taipeitravelsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kylechen2149.taipeitravelsample.R
import com.kylechen2149.taipeitravelsample.common.model.Language
import com.kylechen2149.taipeitravelsample.databinding.ItemLanguageBinding
import com.kylechen2149.taipeitravelsample.databinding.ItemTaipeiTourBinding
import com.kylechen2149.taipeitravelsample.main.model.TaipeiTourResponse
import com.kylechen2149.taipeitravelsample.main.viewmodel.TaipeiTourViewModel
import timber.log.Timber

class LanguageAdapter(
    private val items: MutableList<Language>,
    private val viewModel: TaipeiTourViewModel
) : RecyclerView.Adapter<LanguageAdapter.LanguageListViewHolder>() {

    class LanguageListViewHolder(
        private val binding: ItemLanguageBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Language, viewModel: TaipeiTourViewModel) {
            binding.item = item
            binding.viewModel = viewModel
            binding.root.setOnClickListener {
                viewModel.onItemClick(item)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageListViewHolder {
        val binding = DataBindingUtil.inflate<ItemLanguageBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_language,
            parent,
            false
        )
        return LanguageListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageListViewHolder, position: Int) {
        holder.bind(items[position], viewModel)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position
}