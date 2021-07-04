package com.example.aeonapp.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)

fun String.removeDuplicates() : String{
    val words: List<String> = this.split("\\s+".toRegex())
    val setOfWords: LinkedHashSet<String> = LinkedHashSet<String>(words)

    val result = StringBuilder()
    val index = 0

    for (s in setOfWords) {
        if (index > 0) result.append(" ")
        result.append("$s ")
        index.inc()
    }

    return result.toString()
}

fun Double.convertAmountToDecimalFormat() : String{
    val decimalFormat = DecimalFormat("###.#")
    return decimalFormat.format(this)
}

fun String?.checkCurrency() : String {
    return if (this == "" || this == null) {
        "(Нет данных)"
    } else {
        this
    }
}

fun Long.setCreatedTime(): String {
    return if (this == 0L) {
        "(Нет данных)"
    } else {
        this.convertLongToTime()
    }
}

private fun Long.convertLongToTime(): String {
    val date = Date(this)
    val timeFormat = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return timeFormat.format(date)
}
