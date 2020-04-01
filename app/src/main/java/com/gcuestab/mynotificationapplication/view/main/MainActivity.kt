package com.gcuestab.mynotificationapplication.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gcuestab.mynotificationapplication.R
import com.gcuestab.mynotificationapplication.data.NotificationLocalDataSourceImpl
import com.gcuestab.mynotificationapplication.data.NotificationRepository
import com.gcuestab.mynotificationapplication.data.database.NotificationDataBaseRoom
import com.gcuestab.mynotificationapplication.view.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val repository: NotificationRepository by lazy {
        NotificationRepository(
            localDataSource = NotificationLocalDataSourceImpl(
                database = NotificationDataBaseRoom.getDataBase(context = applicationContext)
            )
        )
    }

    private val viewModel: MainViewModel by lazy {
        MainViewModelFactory(
            repository
        ).create(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        rvActivityMain?.adapter =
            MainAdapter(itemPressed = { notification ->
                startActivity(DetailActivity.getIntent(context = this, notification = notification))
            })

        srlActivityMain?.setOnRefreshListener {
            viewModel.refreshLaunched()
        }

        viewModel.notifications.observe(this, Observer { notifications ->
            (rvActivityMain?.adapter as? MainAdapter?)?.submitList(notifications)
            srlActivityMain?.isRefreshing = false
        })
    }
}
