package com.github.magiepooh

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.github.magiepooh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.pagerSimulation.adapter = MainPagerAdapter(supportFragmentManager)
        binding.tab.setupWithViewPager(binding.pagerSimulation)

        binding.layoutSelectPlan.doOnPreDraw {
            hideSelectPlan()
        }
        binding.layoutToggleSelectPlan.setOnClickListener {
            if (binding.layoutSelectPlan.translationY == 0f) {
                hideSelectPlan()
            } else {
                showSelectPlan()
            }
        }
        lifecycle
    }

    private fun showSelectPlan() {
        val selectInvestmentStyleAnim = ObjectAnimator.ofFloat(
            binding.layoutSelectPlan,
            View.TRANSLATION_Y, binding.layoutSelectPlan.measuredHeight.toFloat(), 0f
        )
        AnimatorSet().apply {
            duration = 200
            interpolator = FastOutSlowInInterpolator()
            playTogether(selectInvestmentStyleAnim)
        }.start()
    }

    private fun hideSelectPlan() {
        if (binding.layoutSelectPlan.measuredHeight.toFloat() == 0f) return

        val selectInvestmentStyleAnim = ObjectAnimator.ofFloat(
            binding.layoutSelectPlan,
            View.TRANSLATION_Y, 0f,
            binding.layoutSelectPlan.measuredHeight.toFloat()
        )
        AnimatorSet().apply {
            duration = 200
            interpolator = FastOutSlowInInterpolator()
            playTogether(selectInvestmentStyleAnim)
        }.start()
    }
}
