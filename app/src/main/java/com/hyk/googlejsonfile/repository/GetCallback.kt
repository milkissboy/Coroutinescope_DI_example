package com.hyk.googlejsonfile.repository

import com.google.gson.Gson
import com.hyk.googlejsonfile.BuildConfig
import com.hyk.googlejsonfile.model.ResponseBase
import com.hyk.googlejsonfile.w
import retrofit2.Response
import java.io.Reader

interface GetCallback<T> {
    fun onResponse(isSuccess: Boolean, response: ResponseBase<T>)
    //fun onDataNotAvailable(message: String)
}

inline fun <reified T : Any> Reader.fromJson(): T = Gson().fromJson(this, T::class.java)

inline fun <reified T> responseSeparation(
    callback: GetCallback<T>,
    response: Response<ResponseBase<T>>?
) {
    response?.let {
        if (response.isSuccessful) {    // success
            response.body()?.let {
                if (BuildConfig.DEBUG)
                    w("[${callback.javaClass.enclosingMethod?.name}] res : ${Gson().toJson(it.shop)}")
                callback.onResponse(true, it)
            }
        } else {                        // fail

        }
    }
}