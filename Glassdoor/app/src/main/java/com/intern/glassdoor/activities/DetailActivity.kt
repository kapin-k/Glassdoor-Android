package com.intern.glassdoor.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.intern.glassdoor.R
import com.intern.glassdoor.fragments.DetailReviewFragment
import com.intern.glassdoor.models.Interview
import com.intern.glassdoor.models.Review
import com.intern.glassdoor.models.Salary
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        removeActionBar()
        handleIntentAction()
        setButtonListeners()
    }

    private fun removeActionBar() {
        supportActionBar?.let { actionBar ->
            actionBar.hide()
        }
    }

    private fun setButtonListeners() {
        backButton.setOnClickListener {
            this.finish()
        }
    }

    private fun handleIntentAction() {
        val type = intent.getStringExtra("detail")
        if (type!!.equals("TYPE_REVIEW")) {
            loadReviewFragment()
        } else if (type.equals("TYPE_INTERVIEW")) {
            loadInterviewFragment()
        } else if (type.equals("TYPE_SALARY")) {
            loadSalaryFragment()
        }
    }

    private fun loadSalaryFragment() {
        val gson = Gson()
        val jsonString = intent.getStringExtra("salaries")
        val type = object : TypeToken<ArrayList<Salary>>() {}.type
        val salaries: ArrayList<Salary> = gson.fromJson(jsonString!!, type)
        if (salaries != null) {
            val interview = salaries.get(0)
            val imageUrl = interview.sqLogoUrl
            Glide
                .with(this)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.no_logo)
                .into(detail_companyLogo)
            detail_companyName.text = interview.employerName
        }
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(
            R.id.detail_Container,
            DetailReviewFragment(this, null, salaries, null, "TYPE_SALARY")
        ).commit()
    }

    private fun loadInterviewFragment() {
        val gson = Gson()
        val jsonString = intent.getStringExtra("interviews")
        val type = object : TypeToken<ArrayList<Interview>>() {}.type
        val interviews: ArrayList<Interview> = gson.fromJson(jsonString!!, type)
        if (interviews != null) {
            val interview = interviews.get(0)
            val imageUrl = interview.sqLogoUrl
            Glide
                .with(this)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.no_logo)
                .into(detail_companyLogo)
            detail_companyName.text = interview.employerName
        }
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(
            R.id.detail_Container,
            DetailReviewFragment(this, null, null, interviews, "TYPE_INTERVIEW")
        ).commit()
    }

    private fun loadReviewFragment() {
        val gson = Gson()
        val jsonString = intent.getStringExtra("reviews")
        val type = object : TypeToken<ArrayList<Review>>() {}.type
        val reviews: ArrayList<Review> = gson.fromJson(jsonString!!, type)
        if (reviews != null) {
            val review = reviews.get(0)
            val imageUrl = review.sqLogoUrl
            Glide
                .with(this)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.no_logo)
                .into(detail_companyLogo)
            detail_companyName.text = review.employerName
        }
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(
            R.id.detail_Container,
            DetailReviewFragment(this, reviews, null, null, "TYPE_REVIEW")
        ).commit()
    }
}
