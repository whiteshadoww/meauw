package me.snowshadow.meauw.frags


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_updates.*
import me.snowshadow.meauw.R
import me.snowshadow.meauw.utils.BaseFragment
import me.snowshadow.meauw.view.adapter.UpdatesViewPagerAdapter

class UpdatesFragment : BaseFragment() {

    private val pagerAdapter by lazy { UpdatesViewPagerAdapter(childFragmentManager) }

    private lateinit var headerView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        headerView = (context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.updates_custom_tabs, null, false)

        return inflater.inflate(R.layout.fragment_updates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updates_view_pager.adapter = pagerAdapter
        updates_tablayout.setupWithViewPager(updates_view_pager)

        val linearLayout1 = headerView.findViewById(R.id.ll) as LinearLayout
        val linearLayout2 = headerView.findViewById(R.id.ll2) as LinearLayout

        updates_tablayout.tabRippleColor = null
        updates_tablayout.getTabAt(0)?.customView = linearLayout1
        updates_tablayout.getTabAt(1)?.customView = linearLayout2
    }

}


