package com.whiterabit.employee.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.whiterabit.employee.R
import com.whiterabit.employee.databinding.FragmentDetailsLayoutBinding
import com.whiterabit.employee.viewmodel.DetailsViewModel
import com.google.gson.Gson
import com.whiterabit.employee.model.Employee_datclass
import kotlinx.android.synthetic.main.fragment_details_layout.*

//created by vishnu
/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFragment : Fragment() {
    var dataDetails: Employee_datclass? = null
    var viewModel: DetailsViewModel? = null
    var activityMainBinding: FragmentDetailsLayoutBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activityMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details_layout,
            container,
            false
        )
        viewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.getApplication()).create(
            DetailsViewModel::class.java
        )

        viewModel!!.detailViewModel(activity!!)
       // activityMainBinding!!.detailsViewModel = viewModel
        activityMainBinding!!.executePendingBindings()

        return activityMainBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var stateData = arguments?.getString("employee_details")!!
        System.out.println("dataDetails:"+stateData)
        if (stateData.isNotBlank()) {
            dataDetails = Gson().fromJson(stateData, Employee_datclass::class.java)
            activityMainBinding!!.detailsModel = dataDetails
            Glide.with(activity!!)
                .load(dataDetails!!.profile_image)
                .circleCrop()
                .into(iv_employe!!)
        }

    }

}