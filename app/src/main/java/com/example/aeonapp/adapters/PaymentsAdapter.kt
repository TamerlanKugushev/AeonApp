package com.example.aeonapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aeonapp.R
import com.example.aeonapp.data.models.PaymentInfo
import com.example.aeonapp.extensions.*
import kotlinx.android.synthetic.main.payments_list_item.view.*

class PaymentsAdapter : ListAdapter<PaymentInfo, PaymentsAdapter.PaymentsViewHolder>(PaymentsDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentsViewHolder {
        val view = parent.inflate(R.layout.payments_list_item)
        return PaymentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class PaymentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(payment: PaymentInfo) {
            itemView.tvDesc.text = payment.desc.removeDuplicates()
            itemView.tvAmount.text =  payment.amount.convertAmountToDecimalFormat()
            itemView.tvCurrency.text = payment.currency.checkCurrency()
            itemView.tvCreated.text = payment.created.setCreatedTime()
        }
    }

    class PaymentsDiffUtilCallback : DiffUtil.ItemCallback<PaymentInfo>() {

        override fun areItemsTheSame(oldItem: PaymentInfo, newItem: PaymentInfo): Boolean {
            return oldItem.desc == newItem.desc
        }

        override fun areContentsTheSame(oldItem: PaymentInfo, newItem: PaymentInfo): Boolean {
            return oldItem == newItem
        }
    }
}