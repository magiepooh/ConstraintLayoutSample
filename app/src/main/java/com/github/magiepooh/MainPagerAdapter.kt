package com.github.magiepooh

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  override fun getItem(position: Int): Fragment = when (Page.values()[position]) {
    Page.FIRST -> MainPageFragment.newInstance()
    Page.SECOND -> MainPageFragment.newInstance()
  }

  override fun getPageTitle(position: Int): CharSequence? = Page.values()[position].name

  override fun getCount(): Int = Page.values().size

  private enum class Page {
    FIRST,
    SECOND
  }


}