package com.rahulverlekar.data.network.common

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by Rahul Verlekar on 18/06/21.
 */

class ErrorAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        check(returnType is ParameterizedType) { "$returnType must be parameterized. Raw types are not supported" }

        val containerType = getParameterUpperBound(0, returnType)
        if (getRawType(containerType) != BaseResponse::class.java) {
            return null
        }

        val errorBodyConverter =
            retrofit.nextResponseBodyConverter<BaseResponse<Any>>(null, containerType, annotations)
        return ErrorAdapter<Any>(containerType, errorBodyConverter)
    }

    class ErrorAdapter<T>(
        private val responseType: Type,
        private val converter: Converter<ResponseBody, BaseResponse<Any>>
    ) :
        CallAdapter<BaseResponse<T>, Call<BaseResponse<T>>> {
        override fun adapt(call: Call<BaseResponse<T>>): Call<BaseResponse<T>> {
            return ErrorCall(call, converter)
        }

        override fun responseType() = responseType
    }

    class ErrorCall<T>(
        private val call: Call<BaseResponse<T>>,
        private val converter: Converter<ResponseBody, BaseResponse<Any>>
    ) :
        Call<BaseResponse<T>> {
        override fun enqueue(callback: Callback<BaseResponse<T>>) {
            call.enqueue(object : Callback<BaseResponse<T>> {
                override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
                    callback.onFailure(this@ErrorCall, BaseNetworkException(t.localizedMessage))
                }

                override fun onResponse(
                    call: Call<BaseResponse<T>>,
                    response: Response<BaseResponse<T>>
                ) {
                    if (response.isSuccessful) {
                        callback.onResponse(this@ErrorCall, response)
                    } else {
                        try {
                            val value = response.errorBody()
                            if (value != null) {
                                val error = converter.convert(value)
                                val e =
                                    BaseNetworkException(error?.message ?: "Something went wrong")
                                callback.onFailure(this@ErrorCall, e)
                            } else {
                                callback.onFailure(
                                    this@ErrorCall,
                                    BaseNetworkException("Something went wrong")
                                )
                            }
                        } catch (e: Exception) {
                            callback.onFailure(this@ErrorCall, BaseNetworkException("Something went wrong"))
                        }
                    }
                }
            })
        }

        override fun timeout(): Timeout = call.timeout()

        override fun clone(): Call<BaseResponse<T>> = ErrorCall(call.clone(), converter)

        override fun isExecuted(): Boolean = synchronized(this) {
            call.isExecuted
        }

        override fun isCanceled(): Boolean = synchronized(this) {
            call.isCanceled
        }

        override fun cancel() = synchronized(this) {
            call.cancel()
        }

        override fun execute(): Response<BaseResponse<T>> {
            throw UnsupportedOperationException("Network Response call does not support synchronous execution")
        }

        override fun request(): Request = call.request()
    }
}