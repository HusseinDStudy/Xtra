package com.exact.xtra.ui.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.exact.xtra.R
import com.exact.xtra.ui.login.LoginActivity
import com.exact.xtra.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        val mainActivityViewModel = ViewModelProviders.of(activity).get(MainViewModel::class.java)
        mainActivityViewModel.isUserLoggedIn.observe(this, Observer { login.text = if (it) "Log Out" else "Log In" })
        login.setOnClickListener { activity.startActivityForResult(Intent(activity, LoginActivity::class.java).apply { putExtra("login", ((mainActivityViewModel.isUserLoggedIn.value != true))) }, 2) }
    }
}
