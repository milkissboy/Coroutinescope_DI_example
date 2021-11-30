package com.hyk.googlejsonfile.repository.service

import com.hyk.googlejsonfile.model.DataShop
import com.hyk.googlejsonfile.model.ResponseBase
import com.hyk.googlejsonfile.repository.NetRetrofit
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    //@GET("file/d/1OfbcPpn7z_xyDk1R-IB7hDarpszuVmTv?export=download")
    @GET("file/d/1OfbcPpn7z_xyDk1R-IB7hDarpszuVmTv/view?usp=sharing")
    fun getProduct() : Call<ResponseBase<ArrayList<DataShop>>>

    companion object {
        fun create(): ProductService = NetRetrofit.retrofit.create(ProductService::class.java)
    }
}