package com.intern.glassdoor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.intern.glassdoor.R
import com.intern.glassdoor.activities.MainActivity
import com.intern.glassdoor.adapters.InterviewsAdapter
import com.intern.glassdoor.models.Interview
import kotlinx.android.synthetic.main.fragment_interview.*

class InterviewFragment(val mainContext: MainActivity, val listOfInterviews: ArrayList<Interview>) :
    Fragment() {
    var interviewsMap: MutableMap<String, ArrayList<Interview>> = mutableMapOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        for (interview in listOfInterviews) {
            val employerName = interview.employerName
            val interviews = interviewsMap.get(employerName)
            if (interviews == null) {
                interviewsMap.put(employerName, arrayListOf(interview))
            } else {
                interviews.add(interview)
                interviewsMap.put(employerName, interviews)
            }
        }
        interview_recyclerView.layoutManager = LinearLayoutManager(context)
        interview_recyclerView.adapter =
            InterviewsAdapter(mainContext, listOfInterviews, interviewsMap)
    }
}
