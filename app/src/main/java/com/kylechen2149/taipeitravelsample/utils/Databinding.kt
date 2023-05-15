package com.kylechen2149.taipeitravelsample.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kylechen2149.taipeitravelsample.R
import com.kylechen2149.taipeitravelsample.adapter.TaipeiTourListDetailAdapter
import com.kylechen2149.taipeitravelsample.main.model.TaipeiTourResponse
import com.kylechen2149.taipeitravelsample.main.viewmodel.TaipeiTourViewModel
import com.squareup.picasso.Picasso

@BindingAdapter("bind:visibility")
fun setVisibility(view: View, isVisibility: Boolean) {
    view.visibility = if (isVisibility) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("bind:setVisibility")
fun setVisibilityGone(view: View, isVisibility: Boolean) {
    view.visibility = if (isVisibility) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:tourListDetailItems", "bind:viewModel", requireAll = false)
fun setTourListDetailItemsAdapter(
    recyclerView: RecyclerView,
    items: MutableList<TaipeiTourResponse>?,
    viewModel: TaipeiTourViewModel?
) {
    if (items == null || viewModel == null) return
    recyclerView.adapter = TaipeiTourListDetailAdapter(items, viewModel)
}

@BindingAdapter("app:imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url == null || url.isNullOrEmpty()) return
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_baseline_error_outline_24)
        .error(R.drawable.ic_baseline_error_outline_24)
        .into(imageView)
}