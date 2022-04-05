package com.example.real_netvorking_app.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.real_netvorking_app.R
import com.example.real_netvorking_app.fragment.HomeFragment
import com.example.real_netvorking_app.model.Home
import java.util.ArrayList

class HomeAdapter(var context: HomeFragment, var home: ArrayList<Home>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: ArrayList<Home>){
        home.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_layoute, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val home = home!!.get(position)

        if (holder is CustomViewHolder) {
            val tv_img = holder.tv_img
            val lay_click = holder.lay_click
            Glide.with(context).load(home.urls.small).placeholder(ColorDrawable(Color.parseColor(home.color))).into(tv_img)

//            lay_click.setOnClickListener {
//                context.openItemDetails(home)
//            }
        }
    }

    private fun isFooter(position: Int): Boolean {
        return position == home!!.size -1
    }

    private fun isHeader(position: Int): Boolean {
        return position == 0
    }

    override fun getItemCount(): Int {
        return home!!.size
    }
}

class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var tv_img : ImageView
    val lay_click: LinearLayout

    init {
        tv_img = view.findViewById(R.id.tv_img)
        lay_click = view.findViewById(R.id.lay_click)
    }
}