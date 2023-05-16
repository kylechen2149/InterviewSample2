package com.kylechen2149.taipeitravelsample.common.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kylechen2149.taipeitravelsample.R
import com.kylechen2149.taipeitravelsample.databinding.FragmentTaipeiTourWebviewBinding



class TaipeiTourDetailWebView : Fragment() {

    private val args: TaipeiTourDetailWebViewArgs by navArgs()
    private lateinit var binding: FragmentTaipeiTourWebviewBinding
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
    ) = DataBindingUtil.inflate<FragmentTaipeiTourWebviewBinding>(
        inflater,
        R.layout.fragment_taipei_tour_webview,
        container,
        false
    ).apply {
        binding = this
        webView.apply {
            isVerticalScrollBarEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.javaScriptEnabled = true
            settings.cacheMode = WebSettings.LOAD_NO_CACHE
            settings.databaseEnabled = false
            settings.domStorageEnabled = true
            settings.supportZoom()
            webViewClient = WebViewClient()
            loadUrl(args.url)
        }

        lifecycleOwner = this@TaipeiTourDetailWebView.viewLifecycleOwner
    }.root

    inner class WebViewClient : android.webkit.WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.progressBar.visibility = View.GONE
        }
    }
}