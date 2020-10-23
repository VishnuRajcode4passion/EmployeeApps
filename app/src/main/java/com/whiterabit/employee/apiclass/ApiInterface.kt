package com.whiterabit.employee.ApiClass

import com.whiterabit.employee.model.Employee_datclass
import retrofit2.Call
import retrofit2.http.*
//created by vishnu
interface ApiInterface {


    @GET("5d565297300000680030a986")
    fun getList(): Call<List<Employee_datclass>>

}

