package dev.kaman.util

import android.content.Context
import android.widget.Toast

fun authToast(
    context: Context
) {
    Toast.makeText(
        context,
        "Email/password tidak sesuai",
        Toast.LENGTH_SHORT
    ).show()
}