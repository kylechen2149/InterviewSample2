package com.example.taipeitravelsample.index.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taipeitravelsample.R
import com.example.taipeitravelsample.index.viewmodel.TaipeiTourViewModel

class TaipeiTourFragment : Fragment() {

    companion object {
        fun newInstance() = TaipeiTourFragment()
    }

    private lateinit var viewModel: TaipeiTourViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_taipei_tour, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaipeiTourViewModel::class.java)
        // TODO: Use the ViewModel
    }

}