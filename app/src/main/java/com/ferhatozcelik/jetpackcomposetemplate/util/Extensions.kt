package com.ferhatozcelik.jetpackcomposetemplate.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

fun View.show() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun String.debug(message: String) {
    Log.d(this, message)
}

fun EditText.modifyText(numberText: String) {
    this.setText(numberText)
    this.setSelection(numberText.length)
}

fun Context.goURL(url: String) {
    try {
        val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(myIntent)
    } catch (e: ActivityNotFoundException) {
        this.toast("No application can handle this request. Please install a webbrowser")
        e.printStackTrace()
    }
}