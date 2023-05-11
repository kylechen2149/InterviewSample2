package com.kylechen2149.taipeitravelsample.main.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylechen2149.taipeitravelsample.main.model.TaipeiTourResponse
import com.kylechen2149.taipeitravelsample.main.repository.TaipeiTourRepository
import com.kylechen2149.taipeitravelsample.utils.SUB_URL
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class TaipeiTourViewModel(private val taipeiTourRepository: TaipeiTourRepository) : ViewModel() {

    val toolbarTitle = MutableStateFlow("TaipeiTour")
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private var page = 1
    private val _tourListDetail = MutableStateFlow(listOf<TaipeiTourResponse>())
    val tourListDetail = _tourListDetail.asStateFlow()
    fun onLanguageClick() = viewModelScope.launch {

    }

    fun initData() = viewModelScope.launch {
        _isLoading.emit(true)
        taipeiTourRepository.getAllAttractions("zh-tw$SUB_URL", page)
            .onEach {
                _tourListDetail.value = it.data
            }
            .catch {
                Timber.e("error=${it.localizedMessage}")
            }
            .onCompletion {
                _isLoading.emit(false)
            }
            .collect()
    }
}