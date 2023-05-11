package com.kylechen2149.taipeitravelsample.main.repository

import com.kylechen2149.taipeitravelsample.common.model.CommonResponse
import com.kylechen2149.taipeitravelsample.main.datasource.TaipeiTourService
import com.kylechen2149.taipeitravelsample.main.model.TaipeiTourResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call

class TaipeiTourRepository(private val taipeiTourService: TaipeiTourService) {

    fun getAllAttractions(url: String, page: Int): Flow<CommonResponse<List<TaipeiTourResponse>>> =
        taipeiTourService.getAllAttractions(url, page).flowOn(Dispatchers.IO)
}