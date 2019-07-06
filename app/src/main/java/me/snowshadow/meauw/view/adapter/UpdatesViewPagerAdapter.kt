package me.snowshadow.meauw.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import me.snowshadow.meauw.frags.CatListFragment

class UpdatesViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CatListFragment.getFav()
            1 -> CatListFragment.getDis()
            else -> CatListFragment.getDis()
        }
    }

    override fun getCount(): Int {
        return 2
    }

}