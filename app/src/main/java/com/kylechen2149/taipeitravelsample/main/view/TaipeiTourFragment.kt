package com.kylechen2149.taipeitravelsample.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kylechen2149.taipeitravelsample.R
import com.kylechen2149.taipeitravelsample.databinding.FragmentTaipeiTourBinding
import com.kylechen2149.taipeitravelsample.main.viewmodel.TaipeiTourViewModel
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
            initData()
        }

        lifecycleOwner = this@TaipeiTourFragment.viewLifecycleOwner
    }.root

}