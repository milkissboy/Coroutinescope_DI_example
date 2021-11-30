package com.hyk.googlejsonfile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hyk.googlejsonfile.viewModel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        d("start [${BuildConfig.BUILD_TYPE}] ${BuildConfig.VERSION_NAME}")

        //initLiveDataObserver()

        viewModel.getProduct()
    }

    /*private fun initLiveDataObserver() {
        viewModel.apply {
        }
    }*/

    /*private fun loadData() {
        viewModel.getProduct()
    }*/
}