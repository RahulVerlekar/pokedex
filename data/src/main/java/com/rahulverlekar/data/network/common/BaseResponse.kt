package com.rahulverlekar.data.network.common

data class BaseResponse<T>(val status: Int, val message: String?, val data: T?)