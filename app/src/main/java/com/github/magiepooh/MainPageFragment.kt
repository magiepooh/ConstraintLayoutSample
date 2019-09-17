package com.github.magiepooh

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.magiepooh.databinding.FragmentMainPageBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainPageFragment : Fragment() {
    companion object {
        fun newInstance() = MainPageFragment()
    }

    private lateinit var binding: FragmentMainPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            updateDescription(R.color.coral)
        }
    }

    private fun updateDescription(
        @ColorRes characterColorResId: Int
    ) {
        val characterColor = ContextCompat.getColor(requireContext(), characterColorResId)
        SpannableStringBuilder().apply {
            append(getString(R.string.omakase_simulation_chart_future_description_1))
            append(
                descriptionEnhancedString(
                    getString(
                        R.string.omakase_simulation_chart_future_description_man,
                        "1,000"
                    ),
                    characterColor
                )
            )
            append(getString(R.string.omakase_simulation_chart_future_description_2))
            append(
                descriptionEnhancedString(
                    getString(
                        R.string.omakase_simulation_chart_future_description_man,
                        "2,000"
                    ), characterColor
                )
            )
            append(getString(R.string.omakase_simulation_chart_future_description_3))
            append(
                descriptionEnhancedString(
                    getString(
                        R.string.omakase_simulation_chart_future_description_percent,
                        "70"
                    ), characterColor
                )
            )
            append(getString(R.string.omakase_simulation_chart_future_description_4))
            val targetFinalPrice = "AAAA"
            append(
                descriptionEnhancedString(
                    getString(
                        R.string.omakase_simulation_chart_future_description_more_man,
                        targetFinalPrice
                    ),
                    characterColor
                )
            )
            append(getString(R.string.omakase_simulation_chart_future_description_5))
        }.let {
            binding.description.text = it
            binding.executePendingBindings()
        }
    }

    private fun descriptionEnhancedString(str: String, color: Int): SpannableString {
        return SpannableString(str).apply {
            setSpan(
                ForegroundColorSpan(color), 0, length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(AbsoluteSizeSpan(20, true), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(StyleSpan(Typeface.BOLD), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}
