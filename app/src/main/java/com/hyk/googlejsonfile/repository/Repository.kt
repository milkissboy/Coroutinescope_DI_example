package com.hyk.googlejsonfile.repository

import com.hyk.googlejsonfile.model.DataShop
import com.hyk.googlejsonfile.model.ResponseBase
import com.hyk.googlejsonfile.repository.service.ProductService
import retrofit2.Call
import javax.inject.Inject

class Repository @Inject constructor(private val service: ProductService) : BaseRepository() {

    suspend fun getProductList(): Call<ResponseBase<ArrayList<DataShop>>>? = coroutineFunc("getProductList") {
        service.getProduct()
    }
}