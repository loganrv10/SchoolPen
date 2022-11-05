package com.asmanmirza.schoolpen.presentation.main.learn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.com.stackimageview.customviews.StackImageView
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide

class AdapterWorldKnowledge(var context: Context, var data:ArrayList<ModelWKnowledge>): RecyclerView.Adapter<AdapterWorldKnowledge.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.dpImage);
        val recImages: RecyclerView = itemView.findViewById(R.id.recImages);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_learn_world_knowledge, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").into(holder.image)
        holder.recImages.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recImages.adapter = AdapterImages(context, ArrayList());
    }

    override fun getItemCount(): Int {
        return 1
    }
}


class AdapterImages(var context: Context, var data:ArrayList<String>): RecyclerView.Adapter<AdapterImages.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_world_knowledge_image, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }

}