package com.kylechen2149.taipeitravelsample.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylechen2149.taipeitravelsample.main.model.TaipeiTourResponse
import com.kylechen2149.taipeitravelsample.main.repository.TaipeiTourRepository
import com.kylechen2149.taipeitravelsample.utils.SUB_URL
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class TaipeiTourViewModel(private val taipeiTourRepository: TaipeiTourRepository) : ViewModel() {

    val toolbarTitle = MutableStateFlow("TaipeiTour")
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    val page = MutableStateFlow(1)
    private val _tourListDetail = MutableStateFlow(listOf<TaipeiTourResponse>())
    val tourListDetail = _tourListDetail.asStateFlow()
    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore
    val loadingMoreData = MutableSharedFlow<Unit>()
    val isRecordsChanged = MutableSharedFlow<Boolean>()
    val isSwipeRefresh = MutableSharedFlow<Boolean>()

    fun onLoadingMore() = viewModelScope.launch {
        loadingMoreData.emit(Unit)
    }

    fun onLanguageClick() = viewModelScope.launch {

    }

    fun initData(_page: Int = 1, isInquiryMore: Boolean = false) = viewModelScope.launch {
        _isLoading.emit(true)
        taipeiTourRepository.getAllAttractions("zh-tw$SUB_URL", _page)
            .onEach {

                if(isInquiryMore){
                    _tourListDetail.value = _tourListDetail.value.plus(it.data)
                    isRecordsChanged.emit(true)
                }else{
                    _tourListDetail.value = it.data
                }

                if(it.data.isNullOrEmpty())
                    _isLoadingMore.emit(false)
                else
                    _isLoadingMore.emit(true)

                isSwipeRefresh.emit(true)

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