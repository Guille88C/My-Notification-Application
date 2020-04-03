package com.gcuestab.mynotificationapplication.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gcuestab.mynotificationapplication.R
import com.gcuestab.mynotificationapplication.view.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

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

        bActivityMainClear?.setOnClickListener {
            viewModel.clearPressed()
        }

        viewModel.notifications.observe(this, Observer { notifications ->
            (rvActivityMain?.adapter as? MainAdapter?)?.submitList(notifications)
            srlActivityMain?.isRefreshing = false
        })
    }
}
