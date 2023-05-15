package com.kylechen2149.taipeitravelsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kylechen2149.taipeitravelsample.R
import com.kylechen2149.taipeitravelsample.databinding.LayoutImageBannerListBinding
import com.kylechen2149.taipeitravelsample.main.model.ImageList

class ImageBannerAdapter(
    private val items: List<ImageList>
    ) : RecyclerView.Adapter<ImageBannerAdapter.ImageBannerItemViewHolder>() {

        class ImageBannerItemViewHolder(
            private val binding: LayoutImageBannerListBinding
        ): RecyclerView.ViewHolder(binding.root){
            fun bind(banner: ImageList) {
                binding.banner = banner
                binding.executePendingBindings()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageBannerItemViewHolder {
            val binding = DataBindingUtil.inflate<LayoutImageBannerListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.layout_image_banner_list,
                parent,
                false
            )
            return ImageBannerItemViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ImageBannerItemViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount() = items.size

        override fun getItemViewType(position: Int) = position
}

