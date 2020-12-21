package com.minafkamel.musically.util

import android.content.Context
import androidx.annotation.StringRes

/**
 * A utility class that provides strings values given a key
 */
class StringProvider(private val context: Context) {

    fun getString(@StringRes key: Int) = context.getString(key)
}