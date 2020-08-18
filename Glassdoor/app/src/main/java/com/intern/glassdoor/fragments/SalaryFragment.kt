package com.intern.glassdoor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.intern.glassdoor.R
import com.intern.glassdoor.activities.MainActivity
import com.intern.glassdoor.adapters.SalaryAdapter
import com.intern.glassdoor.models.Salary
import kotlinx.android.synthetic.main.fragment_salary.*

class SalaryFragment(val mainContext: MainActivity, val listOfSalaries: ArrayList<Salary>) :
    Fragment() {
    var salariesMap: MutableMap<String, ArrayList<Salary>> = mutableMapOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_salary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        for (salary in listOfSalaries) {
            val employerName = salary.employerName
            val salaries = salariesMap.get(employerName)
            if (salaries == null) {
                salariesMap.put(employerName, arrayListOf(salary))
            } else {
                salaries.add(salary)
                salariesMap.put(employerName, salaries)
            }
        }
        salary_recyclerView.layoutManager = LinearLayoutManager(context)
        salary_recyclerView.adapter = SalaryAdapter(mainContext, salariesMap, listOfSalaries)
    }
}

