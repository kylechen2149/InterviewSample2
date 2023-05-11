package com.kylechen2149.taipeitravelsample.common.model

data class CommonResponse<T>(
    val total: Int,
    val data: T
)
