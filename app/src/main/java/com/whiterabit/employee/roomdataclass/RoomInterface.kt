package com.whiterabit.employee.roomdataclass

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.whiterabit.employee.model.Employee_datclass
//created by vishnu
@Dao
interface RoomInterface {
    @Query("select * from EmployeeBook")
    fun getAllEmployee(): List<Employee_datclass>

    @Insert(onConflict = REPLACE)
    fun insertEmployee(task: Employee_datclass): Long
}