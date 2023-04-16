package com.example.secondhand.dto.filter

data class PhoneFilter @JvmOverloads constructor(

    val brandName: String? = null,
    val colorId: Int? = null,
    val osName: String? = null,
    val cameraMin: Int? = null,
    val cameraMax: Int? = null,
    val frontCameraMin: Int? = null,
    val frontCameraMax: Int? = null

)
