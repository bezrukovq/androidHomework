package com.example.vladimir.navigation

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.widget.EditText

class EditDialog : DialogFragment() {
    private var callback: MyListener? = null
    private lateinit var login: EditText
    private lateinit var email: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialogf_edit, null)
        val adb = AlertDialog.Builder(activity)
        login = view.findViewById(R.id.et_login)
        email = view.findViewById(R.id.et_email)
        adb.setTitle("")
                .setView(view)
                .setNegativeButton("CANCEL") { dialogInterface, i -> dismiss() }
                .setPositiveButton("SAVE") { dialogInterface, i -> callback!!.callback(login.text.toString(), email.text.toString()) }
        return adb.create()
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MyListener) {
            callback = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement MyListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }
}
