package com.example.secondhand.dto

data class PcFilter @JvmOverloads constructor(

    val brandName: String? = null,
    val cpuId: Int? = null,
    val gpuId: Int? = null,
    val ramSize: Int? = null
)
