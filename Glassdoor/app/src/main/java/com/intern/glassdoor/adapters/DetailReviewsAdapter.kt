package com.intern.glassdoor.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intern.glassdoor.R
import com.intern.glassdoor.activities.DetailActivity
import com.intern.glassdoor.models.Review
import kotlinx.android.synthetic.main.cell_detail_review.view.*


class DetailReviewsAdapter(val context: DetailActivity, val reviews: ArrayList<Review>) :
    RecyclerView.Adapter<DetailReviewsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.cell_detail_review, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val review = reviews.get(position)
        holder.heading.text = "\"${review.headline}\" "
        val rating = review.overallNumeric
        if (rating > 4) {
            holder.rating.setImageResource(R.drawable.fivestar)
        } else if (rating > 3) {
            holder.rating.setImageResource(R.drawable.fourstar)
        } else if (rating > 2) {
            holder.rating.setImageResource(R.drawable.threestar)
        } else if (rating > 1) {
            holder.rating.setImageResource(R.drawable.twostar)
        } else {
            holder.rating.setImageResource(R.drawable.onestar)
        }
        if (review.currentJob) {
            holder.position.text = "Current " + review.jobTitle
        } else {
            holder.position.text = "Former " + review.jobTitle
        }
        holder.pros.text = review.pros
        holder.cons.text = review.cons
        val ceoApproval = review.ceoApproval
        if (ceoApproval != null) {
            if (ceoApproval.equals("No Opinion")) {
                holder.ceoIcon.setImageResource(R.drawable.square_orange)
                holder.ceoText.text = "No Opinion of CEO"
            } else if (ceoApproval.equals("Approves")) {
                holder.ceoIcon.setImageResource(R.drawable.square_green)
                holder.ceoText.text = "Approves CEO"
            } else {
                holder.ceoIcon.setImageResource(R.drawable.square_red)
                holder.ceoText.text = "Disapproves of CEO"
            }
        } else {
            holder.ceoLayout.visibility = View.GONE
        }
        val outlook = review.overall
        if (outlook != null) {
            if (outlook.equals("Satisfied") || outlook.equals("Very Satisfied")) {
                holder.outlookIcon.setImageResource(R.drawable.square_green)
                holder.outlookText.text = "Positive Outlook"
            } else if (outlook.equals("Dissatisfied")) {
                holder.outlookIcon.setImageResource(R.drawable.square_red)
                holder.outlookText.text = "Negative Outlook"
            } else {
                holder.outlookIcon.setImageResource(R.drawable.square_orange)
                holder.outlookText.text = "Neutral Outlook"
            }
        } else {
            holder.outlookLayout.visibility = View.GONE
        }
        val recommends = review.recommendToFriend
        if (recommends == null) {
            holder.recommendLayout.visibility = View.GONE
        } else {
            if (recommends) {
                holder.recommendIcon.setImageResource(R.drawable.square_green)
                holder.recommendText.text = "Recommends"
            } else {
                holder.recommendIcon.setImageResource(R.drawable.square_red)
                holder.recommendText.text = "Doesn't Recommend"
            }
        }
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val heading = view.review_heading
        val rating = view.review_rating
        val position = view.review_position
        val pros = view.review_pros
        val cons = view.review_cons
        val ceoLayout = view.approveCeoLayout
        val ceoIcon = view.approveCeoIcon
        val ceoText = view.approveCeoText
        val recommendLayout = view.recommendLayout
        val recommendIcon = view.recommendIcon
        val recommendText = view.recommendText
        val outlookLayout = view.outlookLayout
        val outlookIcon = view.outlookIcon
        val outlookText = view.outlookText
    }
}