package com.example.aeonapp.utils

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BasePresenter<T : BaseView> {

    protected var viewCompositeDisposable = CompositeDisposable()
    protected var dataCompositeDisposable = CompositeDisposable()
    private var view: WeakReference<T>? = null

    open fun bindView(view: T) {
        this.view = WeakReference(view)
    }

    open fun unbindView() {
        this.view = null
        viewCompositeDisposable.clear()
    }

    open fun destroyPresenter() {
        dataCompositeDisposable.clear()
    }

    fun getView(): T? {
        return view?.get()
    }

    fun savePresenter(viewId: String) {
        PresentersStorage.savePresenter(viewId, this)
    }

    fun removePresenter(viewId: String) {
        PresentersStorage.removePresenter(viewId)
    }
}