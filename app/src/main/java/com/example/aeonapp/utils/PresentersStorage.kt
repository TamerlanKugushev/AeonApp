package com.example.aeonapp.utils

object PresentersStorage {

    private val presenters = hashMapOf<String, BasePresenter<out BaseView>>()

    fun getPresenter(viewId: String): BasePresenter<*>? {
        return presenters[viewId]
    }

    fun <T : BaseView> savePresenter(id: String, presenter: BasePresenter<T>) {
        presenters[id] = presenter
    }

    fun removePresenter(viewId:String){
        presenters.remove(viewId)
    }

}