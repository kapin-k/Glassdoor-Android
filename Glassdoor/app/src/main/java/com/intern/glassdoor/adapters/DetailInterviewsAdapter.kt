package com.intern.glassdoor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intern.glassdoor.R
import com.intern.glassdoor.activities.DetailActivity
import com.intern.glassdoor.models.Interview
import kotlinx.android.synthetic.main.cell_detail_interview.view.*

class DetailInterviewsAdapter(val context: DetailActivity, val interviews: ArrayList<Interview>) :
    RecyclerView.Adapter<DetailInterviewsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.cell_detail_interview, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val interview = interviews.get(position)
        holder.interviewPosition.text = interview.jobTitle
        holder.applicationDetails.text = interview.processAnswer
        var questionsString = ""
        for (question in interview.questions) {
            questionsString += question.question + "\n"
        }
        holder.interviewQuestions.text = questionsString
        val interviewExperience = interview.processOverallExperience
        val difficulty = interview.processDifficulty
        if (interviewExperience != null) {
            if (interviewExperience.equals("Positive")) {
                holder.interviewIcon.setImageResource(R.drawable.square_green)
                holder.interviewText.text = "Positive Experience"
            } else if (interviewExperience.equals("Negative")) {
                holder.interviewIcon.setImageResource(R.drawable.square_red)
                holder.interviewText.text = "Negative Experience"
            } else {
                holder.interviewIcon.setImageResource(R.drawable.square_orange)
                holder.interviewText.text = "Neutral Experience"
            }
        } else {
            holder.interviewLayout.visibility = View.GONE
        }
        if (difficulty != null) {
            if (difficulty.equals("Average")) {
                holder.experienceIcon.setImageResource(R.drawable.square_green)
                holder.experienceText.text = "Average Interview"
            } else if (difficulty.equals("Difficult")) {
                holder.experienceIcon.setImageResource(R.drawable.square_red)
                holder.experienceText.text = "Difficult Interview"
            } else {
                holder.experienceIcon.setImageResource(R.drawable.square_orange)
                holder.experienceText.text = "Easy Interview"
            }
        } else {
            holder.experienceLayout.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return interviews.size
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val interviewPosition = view.interview_position
        val applicationDetails = view.interview_application
        val interviewQuestions = view.interview_questions
        val experienceLayout = view.experienceLayout
        val experienceIcon = view.experienceIcon
        val experienceText = view.experienceText
        val interviewLayout = view.interviewLayout
        val interviewIcon = view.interviewIcon
        val interviewText = view.interiewText
    }
}