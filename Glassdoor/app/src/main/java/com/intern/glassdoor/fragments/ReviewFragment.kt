package com.intern.glassdoor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.intern.glassdoor.R
import com.intern.glassdoor.activities.MainActivity
import com.intern.glassdoor.adapters.ReviewsAdapter
import com.intern.glassdoor.models.Review
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment(val mainContext: MainActivity, val listOfReviews: ArrayList<Review>) :
    Fragment() {

    var reviewsMap: MutableMap<String, ArrayList<Review>> = mutableMapOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        for (review in listOfReviews) {
            val employerName = review.employerName
            val reviews = reviewsMap.get(employerName)
            if (reviews == null) {
                reviewsMap.put(employerName, arrayListOf(review))
            } else {
                reviews.add(review)
                reviewsMap.put(employerName, reviews)
            }
        }
        review_recyclerView.layoutManager = LinearLayoutManager(context)
        review_recyclerView.adapter = ReviewsAdapter(mainContext, listOfReviews, reviewsMap)
    }
}
