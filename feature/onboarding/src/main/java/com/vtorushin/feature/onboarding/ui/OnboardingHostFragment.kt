package com.vtorushin.feature.onboarding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vtorushin.feature.onboarding.R
import com.vtorushin.feature.onboarding.databinding.FragmentOnboardingHostBinding
import com.vtorushin.feature.onboarding.domain.PagerContentData

class OnboardingHostFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOnboardingHostBinding.inflate(inflater, container, false)
        binding.onBoardingPager.adapter = OnboardingAdapter(
            listOf(
                PagerContentData(R.drawable.credit_card, getString(R.string.first_screen), 0),
                PagerContentData(R.drawable.coins_and_arrows, getString(R.string.second_screen), 1),
                PagerContentData(R.drawable.safe, getString(R.string.third_screen), 2)
            )
        )
        return binding.root
    }

    companion object {
        const val TAG = "On Boarding Fragment"
    }
}