package com.intern.glassdoor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.intern.glassdoor.R
import com.intern.glassdoor.activities.DetailActivity
import com.intern.glassdoor.adapters.DetailInterviewsAdapter
import com.intern.glassdoor.adapters.DetailReviewsAdapter
import com.intern.glassdoor.adapters.DetailSalaryAdapter
import com.intern.glassdoor.models.Interview
import com.intern.glassdoor.models.Review
import com.intern.glassdoor.models.Salary
import kotlinx.android.synthetic.main.fragment_detail_review.*


class DetailReviewFragment(
    val detailContext: DetailActivity,
    val reviews: ArrayList<Review>?,
    val salaries: ArrayList<Salary>?,
    val interviews: ArrayList<Interview>?,
    val type: String
) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (type.equals("TYPE_REVIEW")) {
            return inflater.inflate(R.layout.fragment_detail_review, container, false)
        } else if (type.equals("TYPE_INTERVIEW")) {
            return inflater.inflate(R.layout.fragment_detail_review, container, false)
        } else {
            return inflater.inflate(R.layout.fragment_detail_review, container, false)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if (type.equals("TYPE_REVIEW")) {
            detail_recyclerview.layoutManager = LinearLayoutManager(context)
            detail_recyclerview.adapter = DetailReviewsAdapter(detailContext, reviews!!)
        } else if (type.equals("TYPE_INTERVIEW")) {
            detail_recyclerview.layoutManager = LinearLayoutManager(context)
            detail_recyclerview.adapter = DetailInterviewsAdapter(detailContext, interviews!!)
        } else {
            detail_recyclerview.layoutManager = LinearLayoutManager(context)
            detail_recyclerview.adapter = DetailSalaryAdapter(detailContext, salaries!!)
        }
    }


}
