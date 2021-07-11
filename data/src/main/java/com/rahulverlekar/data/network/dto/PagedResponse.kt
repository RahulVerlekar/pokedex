package com.rahulverlekar.data.network.dto

data class PagedDTO<T>(
    val next: String? = null,
    val previous: String? = null,
    val count: Int = 0,
    val results: List<T>? = null
)