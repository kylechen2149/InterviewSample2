package com.kylechen2149.taipeitravelsample.main.model

data class TaipeiTourResponse(
    val name: String? = "",
    val introduction: String? = "",
    val address: String? = "",
    val modified: String? = "",
    val url: String? = "",
    val images: List<ImageList>
) {
    fun getFirstPic() = images.firstOrNull()?.src + images.firstOrNull()?.ext
}

data class ImageList(
    val src: String,
    val ext: String
)