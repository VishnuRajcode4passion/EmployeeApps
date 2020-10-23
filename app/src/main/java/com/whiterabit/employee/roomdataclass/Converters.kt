package com.whiterabit.employee.roomdataclass

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.whiterabit.employee.model.Employee_datclass
import com.whiterabit.employee.model.address_dataclass
import com.whiterabit.employee.model.company_dataclass
import org.json.JSONObject


/**
 * //created by vishnu
 */

open class Converters {


    @TypeConverter
    fun fromStrinssssg(value: String): JSONObject {
        val listType = object : TypeToken<JSONObject>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLissssssr(list: JSONObject): String {
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromString(value: String): Array<Employee_datclass> {
        val listType = object : TypeToken<Array<Employee_datclass>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(list: Array<Employee_datclass>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    @TypeConverter
    fun fromstringtoaddress(value: String): address_dataclass {
        val listType = object : TypeToken<address_dataclass>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromarraytoaddress(list: address_dataclass): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    @TypeConverter
    fun fromstringtocompany(value: String): company_dataclass {
        val listType = object : TypeToken<company_dataclass>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromarraytoacompany(list: company_dataclass): String {
        val gson = Gson()
        return gson.toJson(list)
    }



}
