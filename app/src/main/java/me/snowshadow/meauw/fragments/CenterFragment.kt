package me.snowshadow.meauw.fragments


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import io.reactivex.android.schedulers.AndroidSchedulers
import me.snowshadow.meauw.R
import me.snowshadow.meauw.utils.BaseFragment

class CenterFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_center, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()
        mainViewModel.showSwipe
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                Navigation.findNavController(activity as Activity, R.id.nav_main_center_fragment)
                    .navigate(
                        if (it)
                            R.id.swipeFragment
                        else R.id.topPicksFragment
                    )
            }
    }

}
