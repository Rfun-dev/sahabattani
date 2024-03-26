package com.motionhack.paktani.util

import com.motionhack.paktani.R
import com.motionhack.paktani.profile.Review

object Dummy {
    val profileList = arrayListOf(
        Review(R.drawable.person_review, R.string.review_1, R.string.star_1),
        Review(R.drawable.person_review, R.string.review_2, R.string.star_2),
        Review(R.drawable.person_review, R.string.review_3, R.string.star_3),
    )
}