package me.snowshadow.meauw.frags

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import com.yuyakaido.android.cardstackview.*
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.card_swipe_cat.view.*
import kotlinx.android.synthetic.main.fragment_swipe.*
import me.snowshadow.meauw.R
import me.snowshadow.meauw.repo.Cat
import me.snowshadow.meauw.utils.BaseFragment
import me.snowshadow.meauw.view.adapter.SwipeAdapter


class SwipeFragment : BaseFragment(), CardStackListener {


    private val manager by lazy { CardStackLayoutManager(this.context, this) }
    private val adapter by lazy { SwipeAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()

    }

    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()

        mainViewModel.cats.observeOn(AndroidSchedulers.mainThread())
            .subscribe { adapter.setCats(it) }

        mainViewModel.loadMore()
    }


    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        card_stack_view.layoutManager = manager
        card_stack_view.adapter = adapter
        card_stack_view.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

        like_button.setOnClickListener {
            onCardDragging(Direction.Right, 1f)
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            card_stack_view.swipe()
        }

        redo_button.setOnClickListener {


            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            card_stack_view.rewind()
        }

        reject_button.setOnClickListener {

            onCardDragging(Direction.Left, 1f)
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            card_stack_view.swipe()
        }


    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        when (direction) {
            Direction.Left -> manager.topView.nope.alpha = ratio * 1.3f
            Direction.Right -> manager.topView.like.alpha = ratio * 1.3f
            Direction.Top -> {

                manager.topView.nope.alpha = 0f
                manager.topView.like.alpha = 0f
            }
            else -> {
                manager.topView.nope.alpha = 0f
                manager.topView.like.alpha = 0f
            }
        }


    }

    override fun onCardSwiped(direction: Direction) {

        var cat: Cat?

        cat = adapter.getCat(manager.topPosition - 1)

        when (direction) {
            Direction.Left -> cat.isFav = 0
            Direction.Right -> cat.isFav = 1
            else -> cat = null
        }
        if (cat != null) {
            mainViewModel.saveCat(cat)
        }

    }

    override fun onCardRewound() {
        manager.topView.nope.alpha = 0f
        manager.topView.like.alpha = 0f
        mainViewModel.removeCat(adapter.getCat(manager.topPosition))
    }

    override fun onCardCanceled() {
        manager.topView.nope.alpha = 0f
        manager.topView.like.alpha = 0f
    }

    override fun onCardAppeared(view: View, position: Int) {

        if (position == adapter.itemCount - 1) mainViewModel.loadMore()
    }

    override fun onCardDisappeared(view: View, position: Int) {
    }

}
