package com.asmanmirza.schoolpen.presentation.main.live

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView

class AdapterLiveClasses(var context: Context) : RecyclerView.Adapter<AdapterLiveClasses.ViewHolder>() {


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        val imageDp:CircleImageView = itemView.findViewById(R.id.imageDp)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_live_class1, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Handler().postDelayed(Runnable {
            Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").thumbnail(0.5f).into(holder.imageDp);
        }, 200)

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return 9
    }
}