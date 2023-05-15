package com.kylechen2149.taipeitravelsample.di

import com.kylechen2149.taipeitravelsample.main.datasource.TaipeiTourService
import com.kylechen2149.taipeitravelsample.main.repository.TaipeiTourRepository
import com.kylechen2149.taipeitravelsample.main.viewmodel.TaipeiTourDetailViewModel
import com.kylechen2149.taipeitravelsample.main.viewmodel.TaipeiTourViewModel
import com.kylechen2149.taipeitravelsample.network.getRetrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val appModule = module {
    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single { getRetrofit(get()) }
}

val apiModule = module {
    single<TaipeiTourService> { get<Retrofit>().create(TaipeiTourService::class.java) }
}

val repoModule = module {
    single { TaipeiTourRepository(get()) }
}

val viewModelModule = module {
    viewModel { TaipeiTourViewModel(get()) }
    viewModel { TaipeiTourDetailViewModel() }
}

val koinModules = listOf(appModule, apiModule, repoModule, viewModelModule)
