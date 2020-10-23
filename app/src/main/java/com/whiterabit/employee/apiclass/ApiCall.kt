package com.whiterabit.employee.apicall

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.whiterabit.employee.ApiClass.ApiInterface
import com.whiterabit.employee.interfaces.CallBackInterface
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import org.json.JSONException
import org.json.JSONObject

//created by vishnu
class ApiCall {


    companion object {


        const val BaseUrl = "http://www.mocky.io/v2/"

        val interceptor = HttpLoggingInterceptor()
        var callBackInterface: CallBackInterface? = null
        private var apiCall: ApiCall? = null
        private var Context: Context? = null

        fun getInstance(callBackInterface: CallBackInterface?, context: Context?): ApiCall {
            this.callBackInterface = callBackInterface
            if (apiCall == null) {
                apiCall = ApiCall()
                this.Context = context
            }
            return apiCall!!
        }

        fun initApiCall(baseUrl: String): ApiInterface {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            // Log.e("token",commonFunction().getSharedPreferences(Context!!,"token"))

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)

                //.writeTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .build()
                    chain.proceed(newRequest)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }


    }


    fun <T> callApi(t: Call<T>, resulCode: Int) {

        val connectivityManager =
            Context!!.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

      //  if (isConnected) {
            t.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>?, response: Response<T>?) {


                    if (response?.code() == 200) {
                        try {

                            callBackInterface!!.handleResponse(response?.body(), resulCode)

                        } catch (e: Exception) {
                            e.printStackTrace()
                            callBackInterface!!.ErrorMessage("Error:Something went wrong")
                        }

                    } else {
                        try {
                            var jsonObject: JSONObject? = null

                            jsonObject = JSONObject(response?.errorBody()?.string())
                            val userMessage = jsonObject.getString("message")

                            callBackInterface!!.ErrorMessage(userMessage!!)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            callBackInterface!!.ErrorMessage("Error:Something went wrong")

                        }


                    }
                }

                override fun onFailure(call: Call<T>?, t: Throwable?) {
                    t!!.printStackTrace()
                    t.message
                    try {
                        Log.e("eroor", "handles " + t.message)
                        callBackInterface!!.handleError(t!!, resulCode)

                    } catch (e: Exception) {
                        callBackInterface!!.ErrorMessage("Error:Something went wrong")
                        Log.e("eroor", "handles " + t.message)
                    }
                }
            })

//        } else {
//            callBackInterface!!.ErrorMessage("No Internet Connection")
//        }

    }


}