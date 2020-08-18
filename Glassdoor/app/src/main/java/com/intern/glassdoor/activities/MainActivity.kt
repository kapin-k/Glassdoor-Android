package com.intern.glassdoor.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.intern.glassdoor.R
import com.intern.glassdoor.adapters.ViewPagerAdapter
import com.intern.glassdoor.models.Interview
import com.intern.glassdoor.models.Result
import com.intern.glassdoor.models.Review
import com.intern.glassdoor.models.Salary
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    private val backgroundScope = CoroutineScope(newSingleThreadContext("background"))
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val context = this
    private val JSON_URL =
        "https://raw.githubusercontent.com/vikrama/feed-json-sample/master/feed.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDataFromApi()
    }

    fun gotoReviewDetailedActivity(reviews: ArrayList<Review>) {
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra("detail", "TYPE_REVIEW")
        val gson = Gson()
        val jsonString = gson.toJson(reviews)
        i.putExtra("reviews", jsonString)
        startActivity(i)
    }

    fun gotoInterviewDetailedActivity(interviews: ArrayList<Interview>) {
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra("detail", "TYPE_INTERVIEW")
        val gson = Gson()
        val jsonString = gson.toJson(interviews)
        i.putExtra("interviews", jsonString)
        startActivity(i)
    }

    fun gotoSalaryDetailedActivity(interviews: ArrayList<Salary>) {
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra("detail", "TYPE_SALARY")
        val gson = Gson()
        val jsonString = gson.toJson(interviews)
        i.putExtra("salaries", jsonString)
        startActivity(i)
    }

    private fun getDataFromApi() {
        tablayout.visibility = View.INVISIBLE
        backgroundScope.launch {
            //val jsonData = readUrl(JSON_URL)
            var data = ""
            val url = URL(JSON_URL)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            val inputStream = httpURLConnection.getInputStream()
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String? = ""
            while (line != null) {
                line = bufferedReader.readLine()
                line?.let {
                    data = (data.toString() + it)
                }
            }
            val jsonData = JSONObject(data)
            val responseData = jsonData.getJSONObject("response")
            val resultData = responseData.getJSONArray("results")
            val listOfResults: ArrayList<Result> = arrayListOf()
            val listOfReviews: ArrayList<Review> = arrayListOf()
            val listOfSalaries: ArrayList<Salary> = arrayListOf()
            val listOfInterviews: ArrayList<Interview> = arrayListOf()
            for (i in 0..resultData.length() - 1) {
                val json = resultData.get(i)
                val gs = Gson()
                val result = gs.fromJson(json.toString(), Result::class.java)
                if (result.type.equals("REVIEW_RESULT")) {
                    listOfReviews.add(result.review!!)
                } else if (result.type.equals("INTERVIEW_RESULT")) {
                    listOfInterviews.add(result.interview!!)
                } else if (result.type.equals("SALARY_RESULT")) {
                    listOfSalaries.add(result.salary!!)
                }
                listOfResults.add(result)
            }
            mainScope.launch {
                viewPager.adapter = ViewPagerAdapter(
                    context,
                    supportFragmentManager,
                    listOfInterviews,
                    listOfSalaries,
                    listOfReviews
                )
                tablayout.setupWithViewPager(viewPager)
                tablayout.visibility = View.VISIBLE
                progress_bar.visibility = View.GONE
            }
        }
    }
}