package com.vtorushin.feature.onboarding.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.vtorushin.feature.onboarding.databinding.FragmentOnboardingItemBinding
import com.vtorushin.feature.onboarding.domain.PagerContentData

class OnboardingAdapter(private val items: List<PagerContentData>) :
    RecyclerView.Adapter<OnBoardingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val binding = FragmentOnboardingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = ITEMS_COUNT
}

class OnBoardingViewHolder(private val binding: FragmentOnboardingItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: PagerContentData) {
        binding.itemImage.setImageResource(data.imageId)
        binding.itemText.text = data.text
        binding.buttons.check(binding.buttons[data.position].id)
        if (data.position == 0)
            binding.leftArrow.visibility = View.INVISIBLE
        if (data.position == ITEMS_COUNT - 1)
            binding.rightArrow.visibility = View.INVISIBLE
    }
}

private const val ITEMS_COUNT = 3