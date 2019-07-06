package me.snowshadow.meauw.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import com.polyak.iconswitch.IconSwitch
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.main_custom_tabs.view.*
import me.snowshadow.meauw.R
import me.snowshadow.meauw.utils.BaseFragment
import me.snowshadow.meauw.view.adapter.MainSlidePagerAdapter


class MainFragment : BaseFragment() {

    private lateinit var slidePagerAdapter: MainSlidePagerAdapter
    private lateinit var headerView: View
    private lateinit var linearLayout2: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        headerView = (context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.main_custom_tabs, null, false)

        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    private lateinit var linearLayout1: LinearLayout

    private lateinit var linearLayout3: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.slidePagerAdapter = MainSlidePagerAdapter(childFragmentManager)
        main_view_pager.adapter = this.slidePagerAdapter
        main_tablayout.setupWithViewPager(main_view_pager)
        main_view_pager.setCurrentItem(1, true)

        linearLayout1 = headerView.findViewById(R.id.ll) as LinearLayout
        linearLayout3 = headerView.findViewById(R.id.ll3) as LinearLayout
        linearLayout2 = headerView.findViewById(R.id.ll2) as RelativeLayout

        main_tablayout.tabRippleColor = null
        main_tablayout.getTabAt(0)?.customView = linearLayout1
        main_tablayout.getTabAt(1)?.customView = linearLayout2
        main_tablayout.getTabAt(2)?.customView = linearLayout3

        linearLayout2.center_tab_view.setOnClickListener { main_view_pager.setCurrentItem(1, true) }
        linearLayout2.icon_switch.setCheckedChangeListener { mainViewModel.showSwipe.onNext(it == IconSwitch.Checked.LEFT) }

        main_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                toggleCenterClick(position != 1)
                changeTabColor(position)

            }

            override fun onPageSelected(position: Int) {
                toggleCenterClick(position != 1)
            }

        })

    }

    private fun changeTabColor(position: Int) {

        when (position) {
            0 -> {
                linearLayout1.alpha = 1f
                linearLayout2.alpha = 0.5f
                linearLayout3.alpha = 0.5f
            }
            1 -> {
                linearLayout1.alpha = 0.5f
                linearLayout2.alpha = 1f
                linearLayout3.alpha = 0.5f
            }
            2 -> {
                linearLayout1.alpha = 0.5f
                linearLayout2.alpha = 0.5f
                linearLayout3.alpha = 1f
            }
        }

    }

    fun toggleCenterClick(enable: Boolean) {

        linearLayout2.center_tab_view.visibility = if (enable) View.VISIBLE
        else View.GONE

    }

}
