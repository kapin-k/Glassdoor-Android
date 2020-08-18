package com.intern.glassdoor.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.intern.glassdoor.activities.MainActivity
import com.intern.glassdoor.fragments.InterviewFragment
import com.intern.glassdoor.fragments.ReviewFragment
import com.intern.glassdoor.fragments.SalaryFragment
import com.intern.glassdoor.models.Interview
import com.intern.glassdoor.models.Review
import com.intern.glassdoor.models.Salary

class ViewPagerAdapter(
    val context: MainActivity,
    val fragmentManager: FragmentManager,
    val listOfInterviews: ArrayList<Interview>,
    val listOfSalaries: ArrayList<Salary>,
    val listOfReviews: ArrayList<Review>
) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ReviewFragment(context, listOfReviews)
            1 -> return InterviewFragment(context, listOfInterviews)
            2 -> return SalaryFragment(context, listOfSalaries)
        }
        return ReviewFragment(context, listOfReviews)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Reviews"
            1 -> return "Interviews"
            2 -> return "Salaries"
        }
        return "Reviews"
    }
}