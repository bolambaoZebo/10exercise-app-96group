package com.ninetysixgroup.exercisetips

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ninetysixgroup.model.WorkOutModel
import kotlinx.android.synthetic.main.home_cardview.view.*

class RecyclerAdapter (context: Context, list: ArrayList<WorkOutModel>, private val listener: onItemClicked) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val context: Context = context
    var list: ArrayList<WorkOutModel> = list

    private inner class View1ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = view.home_cardview_image
        var cardView = view.home_cardview
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return View1ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.home_cardview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = list.get(position)

        if(holder is View1ViewHolder){
            holder.image.setImageResource(item.image)
            holder.cardView.setOnClickListener{
                listener.onClicked(context, item.content, item.imageContent)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface onItemClicked {
        fun onClicked(context: Context, content: String, image: Int)
    }
}