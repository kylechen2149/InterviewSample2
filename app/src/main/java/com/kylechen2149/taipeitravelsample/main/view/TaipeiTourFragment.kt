package com.kylechen2149.taipeitravelsample.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.kylechen2149.taipeitravelsample.R
import com.kylechen2149.taipeitravelsample.adapter.TaipeiTourListDetailAdapter
import com.kylechen2149.taipeitravelsample.databinding.FragmentTaipeiTourBinding
import com.kylechen2149.taipeitravelsample.main.viewmodel.TaipeiTourViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaipeiTourFragment : Fragment() {

    private val taipeiTourViewModel by viewModel<TaipeiTourViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createDataBindingView(inflater, container)
    }

    private fun createDataBindingView(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = DataBindingUtil.inflate<FragmentTaipeiTourBinding>(
        inflater,
        R.layout.fragment_taipei_tour,
        container,
        false
    ).apply {
        viewModel = this@TaipeiTourFragment.taipeiTourViewModel.apply {
            taipeiTourRefresh.setOnRefreshListener {
                initData()
                page.value = 1
            }
            taipeiTourNestedSV.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    if (isLoadingMore.value)
                        initData(++page.value, true)
                }
            })

            loadingMoreData.onEach {
                initData(++page.value, true)
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            isRecordsChanged.onEach {
                if (it) {
                    taipeiTourRV.adapter = TaipeiTourListDetailAdapter(
                        tourListDetail.value.toMutableList(),
                        taipeiTourViewModel
                    )
                    taipeiTourRV.adapter?.notifyItemRangeChanged(0, tourListDetail.value.size)
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            isSwipeRefresh.onEach {
                if(it)
                    if(taipeiTourRefresh.isRefreshing)
                        taipeiTourRefresh.isRefreshing = false
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }

        lifecycleOwner = this@TaipeiTourFragment.viewLifecycleOwner
    }.root

    override fun onResume() {
        super.onResume()
        taipeiTourViewModel.initData()
    }
}