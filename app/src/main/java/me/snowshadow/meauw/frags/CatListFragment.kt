package me.snowshadow.meauw.frags

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_updates_cats_list.*
import me.snowshadow.meauw.R
import me.snowshadow.meauw.utils.BaseFragment
import me.snowshadow.meauw.view.adapter.SwipeAdapter


class CatListFragment : BaseFragment() {

    companion object {

        private var favourites: CatListFragment? = null
        private var dislikes: CatListFragment? = null

        fun getDis(): CatListFragment {
            if (null == dislikes) {
                dislikes = CatListFragment()
                dislikes?.arguments = Bundle().apply { putBoolean("isFav", false) }
            }
            return dislikes!!
        }

        fun getFav(): CatListFragment {
            if (null == favourites) {
                favourites = CatListFragment()
                favourites?.arguments = Bundle().apply { putBoolean("isFav", true) }
            }
            return favourites!!
        }

    }

    private val adapter by lazy { SwipeAdapter(SwipeAdapter.ViewType.LIKES) }
    private var isFav = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isFav = arguments?.getBoolean("isFav")!!
        return inflater.inflate(R.layout.fragment_updates_cats_list, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cat_list_rec.layoutManager = LinearLayoutManager(this.context)
        cat_list_rec.adapter = adapter

        (if (isFav) mainViewModel.favCats else mainViewModel.unlikedCats)
            .subscribe {
                adapter.setCats(it)
            }
    }


}
