package com.whiterabit.employee.interfaces
//created by vishnu
interface CallBackInterface {
    fun <T> handleResponse(response: T?, resultCode: Int)
    fun handleError(error: Throwable, resultCode: Int)
    fun ErrorMessage(errormessage: String)
}