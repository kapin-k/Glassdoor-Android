package com.intern.glassdoor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intern.glassdoor.R
import com.intern.glassdoor.activities.DetailActivity
import com.intern.glassdoor.models.Salary
import kotlinx.android.synthetic.main.cell_detail_salary.view.*

class DetailSalaryAdapter(val context: DetailActivity, val salaries: ArrayList<Salary>) :
    RecyclerView.Adapter<DetailSalaryAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.cell_detail_salary, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val salary = salaries.get(position)
        holder.companyName.text = salary.employerName
        val imageUrl = salary.sqLogoUrl
        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.no_logo)
            .into(holder.companyLogo)

        holder.jobTitle.text = salary.jobTitle
        var compensation = salary.basePay.symbol
        val basePay = salary.basePay.amount
        if (basePay > 1000) {
            compensation += (basePay / 1000).toString() + "k/"
        }
        if (salary.payPeriod.equals("ANNUAL")) {
            compensation += "yr"
        } else {
            compensation += "hr"
        }
        holder.salaryCompensation.text = compensation

    }

    override fun getItemCount(): Int {
        return salaries.size
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyLogo = view.detail_salary_companyLogo
        val companyName = view.detail_salary_companyName
        val salaryCompensation = view.detail_salary_compensation
        val jobTitle = view.detail_salaryPosition
    }
}