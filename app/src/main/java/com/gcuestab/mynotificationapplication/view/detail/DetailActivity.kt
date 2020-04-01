package com.gcuestab.mynotificationapplication.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gcuestab.mynotificationapplication.R
import com.gcuestab.mynotificationapplication.view.entity.Notification
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<DetailViewModel> {
        parametersOf(intent.extras?.getParcelable(DATA))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        viewModel.notification.observe(this, Observer { notification ->
            tvActivityDetailTitle?.text = when (notification.type) {
                Notification.Type.DELIVERY -> getString(R.string.delivery_notification)
                Notification.Type.APPOINTMENT -> getString(R.string.appointment_notification)
                Notification.Type.UNKNOWN -> getString(R.string.unknown_notification)
            }

            tvActivityDetailDate?.text = getString(
                R.string.date_notification,
                notification.dateToDays(),
                notification.dateToHours()
            )

            tvActivityDetailDescription?.text = notification.description

            tvActivityDetailRead?.text =
                if (notification.isRead) getString(R.string.read)
                else getString(R.string.unread)
        })
    }

    companion object {
        private const val DATA = "DATA"

        fun getIntent(context: Context, notification: Notification): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(DATA, notification)
            }
    }
}