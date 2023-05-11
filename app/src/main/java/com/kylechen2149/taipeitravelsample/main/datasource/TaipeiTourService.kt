package com.kylechen2149.taipeitravelsample.main.datasource

import com.kylechen2149.taipeitravelsample.common.model.CommonResponse
import com.kylechen2149.taipeitravelsample.main.model.TaipeiTourResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface TaipeiTourService {

    @GET
    fun getAllAttractions(
        @Url url: String,
        @Query("page") page: Int
    ): Flow<CommonResponse<List<TaipeiTourResponse>>>

}