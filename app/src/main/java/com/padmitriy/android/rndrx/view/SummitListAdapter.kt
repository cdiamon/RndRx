package com.padmitriy.android.rndrx.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.padmitriy.android.rndrx.R
import com.padmitriy.android.rndrx.model.Summit
import com.padmitriy.android.rndrx.util.GlideApp
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_summit.view.*
import java.util.*

class SummitListAdapter(val summitListener: SummitListener) :
    RecyclerView.Adapter<SummitListAdapter.SummitViewHolder>() {

    private var summits: List<Summit> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_summit, parent, false)

        return SummitViewHolder(view)
    }

    override fun onBindViewHolder(holder: SummitViewHolder, position: Int) {
        holder.init(summits[position])
    }

    fun setSummits(summits: List<Summit>) {
        this.summits = summits
        notifyDataSetChanged()
    }

    override fun getItemCount() = summits.size

    inner class SummitViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        private val context: Context = containerView.context

        fun init(summit: Summit) {
            with(containerView) {


                GlideApp.with(this)
                    .load(summit.picture)
                    .error(resources.getDrawable(R.drawable.mountain_default))
                    .apply(RequestOptions.circleCropTransform())
                    .into(summitImage)

                summitName.text = summit.name

                summitHeight.text = "${summit.height} m."

                summitRating.rating = summit.rating

                summitRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

                    summits[adapterPosition].rating = rating
                    summitListener.onRatingChanged(summits[adapterPosition])
//                    summit.rating = rating
//                    summitListener.onRatingChanged(summit)
                }
            }
        }
    }

    interface SummitListener {
        fun onRatingChanged(summitId: Summit)
    }
}
