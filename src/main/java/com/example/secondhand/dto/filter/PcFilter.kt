package com.example.secondhand.dto.filter

data class PcFilter @JvmOverloads constructor(

    val brandName: String? = null,
    val cpuId: Int? = null,
    val gpuId: Int? = null,
    val ramSize: Int? = null
)
