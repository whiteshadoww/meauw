package me.snowshadow.meauw.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import me.snowshadow.meauw.fragments.CenterFragment
import me.snowshadow.meauw.fragments.ProfileFragment
import me.snowshadow.meauw.fragments.UpdatesFragment

class MainSlidePagerAdapter constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> CenterFragment()
            2 -> UpdatesFragment()
            else -> ProfileFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
