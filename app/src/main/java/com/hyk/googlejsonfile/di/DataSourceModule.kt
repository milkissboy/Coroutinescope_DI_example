package com.hyk.googlejsonfile.di

import com.hyk.googlejsonfile.repository.service.ProductDataSource
import com.hyk.googlejsonfile.repository.service.ProductDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * DataSource class 여기에 추가
 *
 * @sample
 *   (이름)DataSource : 인터페이스 클래스
 *   (이름)DataSourceImpl : 실제 내용이 작성되어 있는 클래스
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {

    @Suppress("unused")
    @Binds
    abstract fun bindPopupDataSource(impl: ProductDataSourceImpl): ProductDataSource
}