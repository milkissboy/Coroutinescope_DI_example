package com.hyk.googlejsonfile.repository.service

import com.hyk.googlejsonfile.d
import com.hyk.googlejsonfile.model.DataShop
import com.hyk.googlejsonfile.repository.GetCallback
import com.hyk.googlejsonfile.model.ResponseBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

interface ProductDataSource {

    fun getProduct(callback: GetCallback<Object>)
}

class ProductDataSourceImpl @Inject constructor() : ProductDataSource {

    override fun getProduct(callback: GetCallback<Object>) {
        ProductService.create().getProduct()
            .enqueue(object : Callback<ResponseBase<ArrayList<DataShop>>> {
                override fun onResponse(
                    call: Call<ResponseBase<ArrayList<DataShop>>>,
                    response: Response<ResponseBase<ArrayList<DataShop>>>
                ) {
                    //responseSeparation(callback, response)

                    d("response : $response")
                }

                override fun onFailure(
                    call: Call<ResponseBase<ArrayList<DataShop>>>,
                    t: Throwable
                ) {

                    d("t : $t")

                    //responseHandlingError(callback, t.toString(), errorFunc)
                }
            })
    }
}