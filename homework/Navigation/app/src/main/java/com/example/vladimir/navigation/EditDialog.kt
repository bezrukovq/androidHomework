package com.example.vladimir.navigation

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.dialogf_edit.view.*

class EditDialog : DialogFragment() {

    private var callback: MyListener? = null
    lateinit var dialogView: View

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MyListener) {
            callback = context
        } else {
            throw RuntimeException(context?.toString() + " must implement MyListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        dialogView = LayoutInflater.from(activity).inflate(R.layout.dialogf_edit, null)
        val adb = AlertDialog.Builder(activity)
        adb.setTitle("Input Data")
                .setView(dialogView)
                .setNegativeButton("CANCEL") { dialogInterface, i -> dismiss() }
                .setPositiveButton("SAVE") { dialogInterface, i -> save() }
        return adb.create()
    }

    private fun save() {
        callback?.callback(dialogView.et_login.text.toString(), dialogView.et_email.text.toString())
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }
}
