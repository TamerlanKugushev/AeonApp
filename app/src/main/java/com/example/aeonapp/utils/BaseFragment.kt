package com.example.aeonapp.utils

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import java.util.*

abstract class BaseFragment : Fragment(), BaseView {

    companion object {
        private const val VIEW_ID_KEY = "view_id_key"
    }

    protected lateinit var viewId: String
    private var isConfigurationChanged: Boolean = false

    abstract fun attachPresenter()

    abstract fun getPresenter(): BasePresenter<out BaseView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewId = savedInstanceState?.getString(VIEW_ID_KEY, null) ?: generateViewId()
        attachPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isConfigurationChanged = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        isConfigurationChanged = true
        outState.putString(VIEW_ID_KEY, viewId)
        getPresenter().savePresenter(viewId)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isConfigurationChanged){
            getPresenter().destroyPresenter()
            getPresenter().removePresenter(viewId)
        }
    }

    override fun generateViewId(): String {
        return UUID.randomUUID().toString()
    }

}