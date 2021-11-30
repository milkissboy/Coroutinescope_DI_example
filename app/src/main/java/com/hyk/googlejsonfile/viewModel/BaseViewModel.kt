package com.hyk.googlejsonfile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hyk.googlejsonfile.extends.empty

open class BaseViewModel : ViewModel() {

    // error Msg
    private val errorString = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = errorString

    protected fun transferError(errorMessage: String) {
        errorString.value = errorMessage
        clearError()
    }

    private fun clearError() {
        errorString.value = String.empty
    }
}