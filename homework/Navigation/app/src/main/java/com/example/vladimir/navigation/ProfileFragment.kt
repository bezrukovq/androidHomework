package com.example.vladimir.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.support.v4.app.FragmentManager
import com.example.vladimir.navigation.R.id.tv_email

class ProfileFragment : Fragment() {
    private lateinit var button: Button
    private lateinit var tvLogin: TextView
    private lateinit var tvEmail: TextView
    private var login = ""
    private var email = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        button = view.findViewById(R.id.btn_edit)
        tvLogin = view.findViewById(R.id.tv_login)
        tvLogin.text = login
        tvEmail = view.findViewById(R.id.tv_email)
        tvEmail.text = email
        button.setOnClickListener { edit() }
        retainInstance = true
        return view
    }

    fun saveData(login: String, email: String) {
        tvLogin.text = login
        this.login = login
        this.email = email
        tvEmail.text = email
    }

    fun edit() {
        val dialogF = EditDialog()
        dialogF.isCancelable = false
        dialogF.show(activity!!.supportFragmentManager, "dia1")
    }

    companion object {
        val TAG = "ProfileFragmentTAG"
    }
}


