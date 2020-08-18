package com.intern.glassdoor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intern.glassdoor.R
import com.intern.glassdoor.activities.MainActivity
import com.intern.glassdoor.models.Review
import kotlinx.android.synthetic.main.cell_review.view.*
import java.text.DecimalFormat


class ReviewsAdapter(
    val mainContext: MainActivity,
    val reviews: List<Review>,
    val reviewsMap: MutableMap<String, ArrayList<Review>>
) :
    RecyclerView.Adapter<ReviewsAdapter.CustomViewHolder>() {

    var employerNames: ArrayList<String> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(mainContext).inflate(R.layout.cell_review, parent, false)
        for (key in reviewsMap) {
            employerNames.add(key.key)
        }
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.companyName.text = employerNames.get(position)
        val reviews = reviewsMap.get(employerNames.get(position))
        val reviewsCount = reviewsMap.get(employerNames.get(position))!!.size
        var rating = 0.0
        var imageUrl = ""
        for (review in reviews!!.iterator()) {
            rating += review.overallNumeric
            imageUrl = review.sqLogoUrl
        }
        rating = rating / reviewsCount
        val decimalFormat = DecimalFormat("0.0")
        rating = java.lang.Double.parseDouble(decimalFormat.format(rating))
        if (rating < 3) {
            holder.companyRating.setTextColor(this.mainContext.getResources().getColor(R.color.negativeReviewColor))
            holder.companyRating.setBackgroundResource(R.drawable.border_red)
        } else if (rating > 4) {
            holder.companyRating.setTextColor(this.mainContext.getResources().getColor(R.color.positiveReviewColor))
            holder.companyRating.setBackgroundResource(R.drawable.border_green)
        } else {
            holder.companyRating.setTextColor(this.mainContext.getResources().getColor(R.color.neutralReviewColor))
            holder.companyRating.setBackgroundResource(R.drawable.border_orange)
        }
        holder.companyRating.text = "${rating} ★"
        holder.companyReviewCount.text = "(" + reviewsCount.toString() + " Reviews)"
        //sqLogoUrl
        Glide
            .with(mainContext)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.no_logo)
            .into(holder.companyLogo);
        holder.cell.setOnClickListener {
            mainContext.gotoReviewDetailedActivity(reviews)
        }
    }

    override fun getItemCount(): Int {
        return reviewsMap.size
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyRating = view.review_companyRating
        val companyLogo = view.review_companyLogo
        val companyName = view.review_companyName
        val companyReviewCount = view.review_reviewCount
        val cell = view
    }
}