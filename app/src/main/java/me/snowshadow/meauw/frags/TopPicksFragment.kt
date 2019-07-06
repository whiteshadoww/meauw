package me.snowshadow.meauw.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_main_top_picks.*
import me.snowshadow.meauw.R
import me.snowshadow.meauw.utils.BaseFragment
import me.snowshadow.meauw.view.adapter.SwipeAdapter

class TopPicksFragment : BaseFragment() {

    private val compositeDisposal = CompositeDisposable()
    private val adapter by lazy { SwipeAdapter(SwipeAdapter.ViewType.TOP_PICKS) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_top_picks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        top_picks_rec.layoutManager = GridLayoutManager(this.context, 2)
        top_picks_rec.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        compositeDisposal.add(mainViewModel.cats.observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                adapter.setCats(it)
            })
        mainViewModel.loadMore()
    }

    override fun onPause() {
        compositeDisposal.dispose()
        super.onPause()
    }

}
