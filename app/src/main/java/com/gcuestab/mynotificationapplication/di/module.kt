package com.gcuestab.mynotificationapplication.di

import com.gcuestab.mynotificationapplication.view.detail.DetailViewModel
import com.gcuestab.mynotificationapplication.view.entity.Notification
import com.gcuestab.mynotificationapplication.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(repository = get())
    }

    viewModel { (notification: Notification) ->
        DetailViewModel(repository = get(), notification = notification)
    }
}