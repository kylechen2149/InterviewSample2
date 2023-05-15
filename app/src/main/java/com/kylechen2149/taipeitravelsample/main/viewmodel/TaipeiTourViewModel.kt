package com.kylechen2149.taipeitravelsample.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylechen2149.taipeitravelsample.common.model.Language
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
    private val _languageList = MutableStateFlow(listOf(
        Language("zh-tw", "正體中文"),
        Language("zh-cn", "簡體中文"),
        Language("en", "英文"),
        Language("ja", "日文"),
        Language("ko", "韓文"),
        Language("es", "西班牙文"),
        Language("id", "印尼文"),
        Language("th", "泰文"),
        Language("vi", "越南文"))
    )
    val languageList = _languageList.asStateFlow()
    private val nowLanguage = MutableStateFlow(_languageList.value[0].code)
    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore
    val loadingMoreData = MutableSharedFlow<Unit>()
    val isRecordsChanged = MutableSharedFlow<Boolean>()
    val isSwipeRefresh = MutableSharedFlow<Boolean>()
    val showLanguageWindow = MutableSharedFlow<Unit>()
    val onLanguageItemClick = MutableSharedFlow<Language>()
    val onItemClickToDetail = MutableSharedFlow<TaipeiTourResponse>()
    fun onLoadingMore() = viewModelScope.launch {
        loadingMoreData.emit(Unit)
    }

    fun onLanguageClick() = viewModelScope.launch {
        showLanguageWindow.emit(Unit)
    }

    fun initData(_page: Int = 1, isInquiryMore: Boolean = false, language: String = nowLanguage.value) = viewModelScope.launch {
        _isLoading.emit(true)
        taipeiTourRepository.getAllAttractions("$language$SUB_URL", _page)
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

    fun onLanguageItemClick(item: Language) = viewModelScope.launch {
        nowLanguage.value = item.code //save the selected language in case swipe refresh
        onLanguageItemClick.emit(item)
    }

    fun onItemClick(item: TaipeiTourResponse) = viewModelScope.launch {
        onItemClickToDetail.emit(item)
    }
}