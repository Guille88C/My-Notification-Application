package com.gcuestab.mynotificationapplication.di

import com.gcuestab.mynotificationapplication.view.detail.DetailViewModel
import com.gcuestab.mynotificationapplication.view.entity.Notification
import com.gcuestab.mynotificationapplication.view.main.MainViewModel
import com.gcuestab.pushnotification.di.getNotificationRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        getNotificationRepository(context = androidApplication())
    }

    viewModel {
        MainViewModel(repository = get())
    }

    viewModel { (notification: Notification) ->
        DetailViewModel(repository = get(), notification = notification)
    }
}