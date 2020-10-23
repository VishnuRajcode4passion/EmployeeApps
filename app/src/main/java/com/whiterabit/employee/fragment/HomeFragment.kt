package com.whiterabit.employee.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.whiterabit.employee.R
import com.whiterabit.employee.adapter.ListDataAdapter
import com.whiterabit.employee.databinding.ActivityMainBinding
import com.whiterabit.employee.interfaces.EmployeeListInterface
import com.whiterabit.employee.model.Employee_datclass
import com.whiterabit.employee.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
//created by vishnu
class HomeFragment : Fragment(),EmployeeListInterface {
    var viewModel: HomeViewModel? = null
    var activityMainBinding: ActivityMainBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activityMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.activity_main,
            container,
            false
        )
        viewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.getApplication()).create(
            HomeViewModel::class.java
        )

        viewModel!!.InfiniteLoopViewModel(activity!!)
        activityMainBinding!!.infiniteviewModel = viewModel
        activityMainBinding!!.executePendingBindings()

        activityMainBinding!!.executePendingBindings()


        return activityMainBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        eventManage()

    }

    fun eventManage(){
        sv_employee.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter!!.filter.filter(newText)
                return false
            }

        })
    }
    var employeeList:ArrayList<Employee_datclass>? = null
    private fun dataObserver() {
        viewModel!!.resultData!!.observe(
            activity!!,
            Observer<ArrayList<Employee_datclass>> { successList ->
                // update UI
                if (successList.isNotEmpty()) {
                    this.employeeList = successList
                    adapter!!.setListAdapter(activity!!, successList)
                }else{
                    viewModel!!.callService()
                }

            })
        viewModel!!.progressBar!!.observe(activity!!, Observer<Boolean> { successCode ->
            // update UI
            activity!!.runOnUiThread(Runnable() {
                run() {
                    if (successCode == true) {
                        activityMainBinding?.progress?.visibility = View.VISIBLE
                        activity!!.getWindow()?.setFlags(
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        )
                    } else {

                        activityMainBinding?.progress?.visibility = View.GONE
                        activity!!.getWindow()
                            ?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    }
                }
            })

        })
    }

    var adapter: ListDataAdapter? = null
    fun setAdapter() {
        adapter = ListDataAdapter(activity!!,this)
        activityMainBinding!!.dataAdapter = adapter
    }

    override fun onItemClick(employee: Employee_datclass) {
        var bundle = bundleOf("employee_details" to Gson().toJson(employee))
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
    }

    override fun onUpdateSearch(employeeFilterList: ArrayList<Employee_datclass>) {
        System.out.println("searchData"+Gson().toJson(employeeFilterList))

            adapter!!.setListAdapter(activity!!, employeeFilterList)

    }

    override fun onResume() {
        super.onResume()
        dataObserver()
    }

}