package com.intern.glassdoor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intern.glassdoor.R
import com.intern.glassdoor.activities.MainActivity
import com.intern.glassdoor.models.Interview
import kotlinx.android.synthetic.main.cell_interview.view.*

class InterviewsAdapter(val mainContext: MainActivity,val listOfInterviews: List<Interview>,val interviewsMap: MutableMap<String, ArrayList<Interview>>) :
    RecyclerView.Adapter<InterviewsAdapter.CustomViewHolder>() {
    var employerNames:ArrayList<String> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(mainContext).inflate(R.layout.cell_interview, parent, false)
        for(key in interviewsMap){
            employerNames.add(key.key)
        }
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val interviews = interviewsMap.get(employerNames.get(position))
        var imageUrl = ""
        for(interview in interviews!!.iterator()){
            imageUrl = interview.sqLogoUrl
        }
        holder.companyName.text = employerNames.get(position)
        holder.companyInterviewCount.text = "("+interviews!!.size.toString()+" Interviews)"
        Glide
            .with(mainContext)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.no_logo)
            .into(holder.companyLogo);
        holder.view.setOnClickListener {
            mainContext.gotoInterviewDetailedActivity(interviews)
        }
    }

    override fun getItemCount(): Int {
        return interviewsMap.size
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyName = view.interview_companyName
        val companyInterviewCount = view.interview_interviewCount
        val companyLogo = view.interview_companyLogo
        val view = view
    }
}