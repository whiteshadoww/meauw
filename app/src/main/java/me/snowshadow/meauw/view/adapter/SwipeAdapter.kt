package me.snowshadow.meauw.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_likes.view.*
import kotlinx.android.synthetic.main.card_swipe_cat.view.*
import kotlinx.android.synthetic.main.card_top_picks.view.*
import me.snowshadow.meauw.R
import me.snowshadow.meauw.repo.Cat


class SwipeAdapter constructor(var type: ViewType = ViewType.HOT_PICK) :
    RecyclerView.Adapter<SwipeAdapter.SwipeHolder>() {


    enum class ViewType { HOT_PICK, TOP_PICKS, LIKES }

    private var cats = ArrayList<Cat>()

    fun setCats(value: ArrayList<Cat>) {
        val callback = CatDiffCallback(cats, value)
        val result = DiffUtil.calculateDiff(callback)
        cats = value
        result.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeHolder {

        return SwipeHolder(
            LayoutInflater.from(parent.context).inflate(
                when (type) {
                    ViewType.TOP_PICKS -> R.layout.card_top_picks
                    ViewType.HOT_PICK -> R.layout.card_swipe_cat
                    else -> R.layout.card_likes
                },
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = cats.size

    override fun onBindViewHolder(holder: SwipeHolder, position: Int) {
        holder.bind()
    }

    fun getCat(topPosition: Int) = cats[topPosition]
    fun getCats() = cats

    inner class SwipeHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind() {
            val cat = cats[adapterPosition]
            when (type) {
                ViewType.TOP_PICKS -> Picasso.get().load(cat.url).into(v.top_pic_image)
                ViewType.LIKES -> Picasso.get().load(cat.url).into(v.like_image)
                else -> {
                    v.nope.alpha = 0f
                    v.like.alpha = 0f

                    Picasso.get().load(cat.url).into(v.swipe_image)
                }
            }

        }

    }


}

class CatDiffCallback(
    private val old: List<Cat>,
    private val aNew: List<Cat>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return aNew.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition].id == aNew[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return old[oldPosition] == aNew[newPosition]
    }

}