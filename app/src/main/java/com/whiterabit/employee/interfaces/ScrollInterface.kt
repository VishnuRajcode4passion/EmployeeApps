package com.whiterabit.employee.interfaces

import com.whiterabit.employee.model.Employee_datclass
//created by vishnu
interface EmployeeListInterface {
    fun onItemClick(get: Employee_datclass)
    fun onUpdateSearch(employeeFilterList: ArrayList<Employee_datclass>)
}