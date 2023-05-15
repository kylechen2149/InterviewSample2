package com.kylechen2149.taipeitravelsample.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaipeiTourResponse(
    val name: String? = "",
    val introduction: String? = "",
    val address: String? = "",
    val modified: String? = "",
    val url: String? = "",
    val images: List<ImageList>
) : Parcelable {
    fun getFirstPic() = images.firstOrNull()?.src + images.firstOrNull()?.ext
}
@Parcelize
data class ImageList(
    val src: String,
    val ext: String
) : Parcelable {
    fun getCompletedPath() = src + ext
}