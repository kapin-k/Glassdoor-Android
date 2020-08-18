package com.intern.glassdoor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intern.glassdoor.R
import com.intern.glassdoor.activities.MainActivity
import com.intern.glassdoor.models.Salary
import kotlinx.android.synthetic.main.cell_salary.view.*

class SalaryAdapter(val context: MainActivity,val salariesMap: MutableMap<String, ArrayList<Salary>>,val salaries:ArrayList<Salary>) :
    RecyclerView.Adapter<SalaryAdapter.CustomViewHolder>() {

    var employerNames:ArrayList<String> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.cell_salary, parent, false)
        for(key in salariesMap){
            employerNames.add(key.key)
        }
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.companyName.text = employerNames.get(position)
        val salaries = salariesMap.get(employerNames.get(position))!!
        holder.companySalaryCount.text = "("+salaries.size.toString()+" Salaries)"
        var payList:ArrayList<Int> = arrayListOf()
        var imageUrl = ""
        for(salary in salaries){
            payList.add(salary.basePay.amount)
            imageUrl = salary.sqLogoUrl
        }
        var min = Integer.MAX_VALUE
        var max = Integer.MIN_VALUE
        for(pay in payList){
            if(pay < min){
                min = pay
            }
            if(pay > max){
                max = pay
            }
        }
        if(min == max) {
            holder.companySalaryRange.text = "$${min/1000}K - "
        } else {
            holder.companySalaryRange.text = "$${min/1000}K - $${max/1000}K"
        }
        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.no_logo)
            .into(holder.companyLogo);
        holder.view.setOnClickListener {
            context.gotoSalaryDetailedActivity(salaries)
        }
    }

    override fun getItemCount(): Int {
        return salariesMap.size
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companySalaryRange = view.salary_range
        val companyName = view.salary_companyName
        val companySalaryCount = view.salary_companySalaryCount
        val companyLogo = view.salary_companyLogo
        val view = view
    }
}