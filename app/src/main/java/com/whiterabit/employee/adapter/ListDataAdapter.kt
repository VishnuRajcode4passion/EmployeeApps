package com.whiterabit.employee.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.whiterabit.employee.R
import com.whiterabit.employee.databinding.LayoutAdapterlistBinding
import com.whiterabit.employee.interfaces.EmployeeListInterface
import com.whiterabit.employee.model.Employee_datclass
import kotlin.collections.ArrayList
//created by vishnu
class ListDataAdapter(
    var activity: Activity,
    var employeeListInterface: EmployeeListInterface

) : RecyclerView.Adapter<ListDataAdapter.MyViewHolder>(), Filterable {
    var adapterBinding: LayoutAdapterlistBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        adapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(activity),
            R.layout.layout_adapterlist,
            parent,
            false
        )

        return MyViewHolder(adapterBinding!!)

    }

    override fun getItemCount(): Int {
        if (employeeList != null) {
            return employeeList!!.size
        } else {
            return 0
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(employeeList!!.get(position))
        holder.setIsRecyclable(false);
        Glide.with(activity)
            .load(employeeList!!.get(position).profile_image)
            .circleCrop()
            .into(holder.iv_employee!!)
        holder.itemView.setOnClickListener {
            employeeListInterface.onItemClick(employeeList!!.get(position))

        }
    }
    var filteredList: MutableList<Employee_datclass>? = null
    var employeeList: List<Employee_datclass>? = null
    fun setListAdapter(
        activity: Activity,
        dataList: ArrayList<Employee_datclass>
    ) {
        this.activity = activity
        this.employeeList = dataList
        notifyDataSetChanged()
    }
    var employeeFilterList = listOf<Employee_datclass>()
    override fun getFilter(): Filter {
        Log.e("infilter","true")

        return object : Filter() {
            protected override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()

                if (charString.isNullOrBlank() || charSequence=="") {
                    System.out.println("name:"+charSequence)
                    employeeFilterList = employeeList!!
                } else {
                    filteredList = arrayListOf()
                    for (row in employeeList!!) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.name!!.toLowerCase().contains(charString.toLowerCase()) || row.name!!.contains(charSequence)) {
                            filteredList?.add(row)
                        }
                    }

                    employeeFilterList = filteredList!!
                }

                val filterResults = FilterResults()
                filterResults.values = employeeFilterList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                if (employeeFilterList!=null ) {
                    Log.e("filterResults","in side publishResults ")
                    employeeFilterList = filterResults.values as ArrayList<Employee_datclass>
                    employeeListInterface!!.onUpdateSearch((employeeFilterList as ArrayList<Employee_datclass>)!!)
                    notifyDataSetChanged()
                }else{
                    Log.e("filterResults","in side else publishResults ")

                }
            }
        }
    }


    inner class MyViewHolder(val binding: LayoutAdapterlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal var iv_employee: ImageView? = null

        init {
            iv_employee = itemView.findViewById(R.id.iv_employee)
        }

        fun bind(data: Any) {
            binding.setVariable(
                BR.employeeListModel,
                data
            ) //BR - generated class; BR.user - 'user' is variable name declared in layout
            binding.executePendingBindings()
        }
    }
}