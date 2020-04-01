package com.gcuestab.mynotificationapplication.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gcuestab.mynotificationapplication.R
import com.gcuestab.mynotificationapplication.view.entity.Notification

class MainAdapter(var itemPressed: (Notification) -> Unit) :
    ListAdapter<Notification, MainAdapter.NotificationsHolder>(object :
        DiffUtil.ItemCallback<Notification>() {
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean =
            oldItem.id == newItem.id

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsHolder =
        NotificationsHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        )

    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        holder.run {
            val item = getItem(position)

            bind(item = item)

            itemView.setOnClickListener {
                itemPressed(item)
            }
        }
    }

    inner class NotificationsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Notification) {
            itemView.run {
                val title = when (item.type) {
                    Notification.Type.DELIVERY -> context.getString(R.string.delivery_notification)
                    Notification.Type.APPOINTMENT -> context.getString(R.string.appointment_notification)
                    Notification.Type.UNKNOWN -> context.getString(R.string.unknown_notification)
                }
                findViewById<AppCompatTextView>(R.id.tvItemMainTitle).text = title
                findViewById<AppCompatTextView>(R.id.tvItemMainDate).text = context.getString(
                    R.string.date_notification,
                    item.dateToDays(),
                    item.dateToHours()
                )
                findViewById<AppCompatTextView>(R.id.tvItemMainDescription).text = item.description
            }
        }

    }

}
