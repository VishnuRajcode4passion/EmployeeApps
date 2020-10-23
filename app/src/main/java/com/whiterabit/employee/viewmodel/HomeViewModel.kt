package com.whiterabit.employee.viewmodel

import android.app.Activity
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whiterabit.employee.apicall.ApiCall
import com.whiterabit.employee.interfaces.CallBackInterface

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.whiterabit.employee.model.Employee_datclass
import com.whiterabit.employee.roomdataclass.AppDatabase
import io.reactivex.disposables.CompositeDisposable
import java.util.*

//created by vishnu
class HomeViewModel : ViewModel(), Observable, CallBackInterface {

    var app_database: AppDatabase? = null
    val disposable= CompositeDisposable()

    override fun <T> handleResponse(response: T?, resultCode: Int) {
        progressBar.value = false
        var data = response as ArrayList<Employee_datclass>

        response.forEach {
            var auctionDataStsatus = app_database?.roomdao?.insertEmployee(it!!)

        }
        resultData.value = data

    }

    override fun handleError(error: Throwable, resultCode: Int) {
        progressBar.value = false
    }

    override fun ErrorMessage(errormessage: String) {
        progressBar.value = false
        Toast.makeText(context, errormessage, Toast.LENGTH_SHORT).show()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    var resultData = MutableLiveData<ArrayList<Employee_datclass>>()
    var progressBar = MutableLiveData<Boolean>()

    var context: Activity? = null
    var view: LinearLayout? = null

    fun InfiniteLoopViewModel(
        context: Activity
    ) {
        this.context = context
        this.view = view
        app_database = AppDatabase.getInstance(context)
        var employee = app_database?.roomdao?.getAllEmployee()
        if(employee!!.isNotEmpty()){
            resultData.value =  employee as ArrayList<Employee_datclass>
        }else{
            callService()
        }

    }

    val ReqID = 100
    fun callService() {
        progressBar.value = true
        ApiCall.getInstance(this, context).callApi(
            ApiCall.initApiCall(ApiCall.BaseUrl).getList(),
            ReqID
        )
    }
}