package com.example.aeonapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aeonapp.R
import com.example.aeonapp.utils.BaseFragment
import com.example.aeonapp.utils.PresentersStorage
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment(), LoginView {

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    private lateinit var presenter: LoginPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun attachPresenter() {
        val presenter = PresentersStorage.getPresenter(viewId)
        if (presenter !is LoginPresenter) {
            this.presenter = LoginPresenter()
            return
        }
        this.presenter = presenter
    }

    override fun getPresenter(): LoginPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener {
            login()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun showError() {
        textInputLayoutLogin.error = "Пользователь незарегистрирован"
        textInputLayoutPassword.error = "Пользователь незарегистрирован"
    }

    private fun login() {
        if (validateLogin() && validatePassword()) {
            presenter.loginUser(
                login = editTextLogin.text.toString(),
                password = editTextPassword.text.toString()
            )
        }
    }

    private fun validateLogin(): Boolean {
        if (loginIsEmpty()) {
            textInputLayoutLogin.error = null
            return true
        } else {
            textInputLayoutLogin.error = "Поле не может быть пустым"
            return false
        }
    }

    private fun loginIsEmpty(): Boolean {
        return editTextLogin.text.toString().isNotEmpty()
    }

    private fun validatePassword(): Boolean {
        if (passwordIsEmpty()) {
            textInputLayoutPassword.error = null
            return true
        } else {
            textInputLayoutPassword.error = "Поле не может быть пустым"
            return false
        }
    }

    private fun passwordIsEmpty(): Boolean {
        return editTextPassword.text.toString().isNotEmpty()
    }
}