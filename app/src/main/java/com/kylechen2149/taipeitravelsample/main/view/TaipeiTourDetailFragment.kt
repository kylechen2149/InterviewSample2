package com.kylechen2149.taipeitravelsample.main.view

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kylechen2149.taipeitravelsample.R
import com.kylechen2149.taipeitravelsample.adapter.ImageBannerAdapter
import com.kylechen2149.taipeitravelsample.databinding.FragmentTaipeiTourDetailBinding
import com.kylechen2149.taipeitravelsample.main.viewmodel.TaipeiTourDetailViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class TaipeiTourDetailFragment : Fragment() {

    private val taipeiTourDetailViewModel by viewModel<TaipeiTourDetailViewModel>()
    private val args: TaipeiTourDetailFragmentArgs by navArgs()
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
    ) = DataBindingUtil.inflate<FragmentTaipeiTourDetailBinding>(
        inflater,
        R.layout.fragment_taipei_tour_detail,
        container,
        false
    ).apply {
        detail = args.tourDetail
        viewModel = this@TaipeiTourDetailFragment.taipeiTourDetailViewModel.apply {

            val imageBanner = ImageBannerAdapter(args.tourDetail.images)
            banner.apply {
                setAutoTurningTime(3000)
                adapter = imageBanner
            }

            onBackClick.onEach {
                findNavController().popBackStack()
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
        tvLink.apply {
            movementMethod = LinkMovementMethod.getInstance()
            setLinkTextColor(resources.getColor(R.color.colorPrimary))
            linksClickable = true
        }
        lifecycleOwner = this@TaipeiTourDetailFragment.viewLifecycleOwner
    }.root
}