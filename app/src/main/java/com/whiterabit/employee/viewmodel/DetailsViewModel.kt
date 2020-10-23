package com.whiterabit.employee.viewmodel

import android.app.Activity
import android.widget.LinearLayout
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel

//created by vishnu
class DetailsViewModel : ViewModel(), Observable {

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    var context: Activity? = null
    var view: LinearLayout? = null
    fun detailViewModel(
        context: Activity
    ) {
        this.context = context
        this.view = view
    }

}