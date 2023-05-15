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

class TaipeiTourDetailViewModel : ViewModel() {

    val onBackClick = MutableSharedFlow<Unit>()
    fun onBackClick() = viewModelScope.launch {
        onBackClick.emit(Unit)
    }
}