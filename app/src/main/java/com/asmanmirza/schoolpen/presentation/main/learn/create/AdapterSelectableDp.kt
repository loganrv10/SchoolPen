package com.asmanmirza.schoolpen.presentation.main.learn.create

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import de.hdodenhof.circleimageview.CircleImageView

class AdapterSelectableDp(var context: Context, var data:ArrayList<ModelDp>, var selected:Boolean):RecyclerView.Adapter<AdapterSelectableDp.ViewHolder>(){
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val dp:CircleImageView = itemView.findViewById(R.id.dpImage);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dp, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val md = data[position]
        Glide.with(context).load(md.imageUrl).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.dp)

        if(selected){
            AddQuestionFragment.instance.setSelectedSize(data.size)
        }
    }

    override fun getItemCount(): Int {
        return if(data.size > 10){
            10
        }else{
            data.size
        }
    }


}