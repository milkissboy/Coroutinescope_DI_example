package com.hyk.googlejsonfile.viewModel

import androidx.lifecycle.viewModelScope
import com.hyk.googlejsonfile.d
import com.hyk.googlejsonfile.model.ResponseBase
import com.hyk.googlejsonfile.repository.GetCallback
import com.hyk.googlejsonfile.repository.service.ProductDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val dataSource: ProductDataSource) : BaseViewModel() {

    init {
    }

    fun getProduct() {
        viewModelScope.launch {
            dataSource.getProduct(object : GetCallback<Object> {
                override fun onResponse(isSuccess: Boolean, response: ResponseBase<Object>) {

                }
            })

            /*dataSource.getProductList()?.let {
                d("result : ", it)
            }*/
        }
    }

}