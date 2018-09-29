package com.example.vladimir.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private var login = ""
    private var email = ""


    companion object {
        const val TAG = "ProfileFragmentTAG"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        retainInstance = true
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_login.text = login
        tv_email.text = email
        btn_edit.setOnClickListener { edit() }

    }

    fun saveData(login: String, email: String) {
        tv_email.text = email
        tv_login.text = login
        this.email = email
        this.login = login
    }

    private fun edit() {
        val dialogF = EditDialog()
        dialogF.isCancelable = false
        dialogF.show(activity?.supportFragmentManager, "dia1")
    }

}


