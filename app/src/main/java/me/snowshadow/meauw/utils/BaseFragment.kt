package me.snowshadow.meauw.utils

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import me.snowshadow.meauw.activities.MainViewModel
import me.snowshadow.meauw.utils.di.Injectable
import javax.inject.Inject

abstract class BaseFragment : Fragment(), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    val mainViewModel by lazy { ViewModelProviders.of(this, factory)[MainViewModel::class.java] }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}