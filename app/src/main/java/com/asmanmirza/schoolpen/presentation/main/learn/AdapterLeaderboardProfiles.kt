package com.asmanmirza.schoolpen.presentation.main.learn

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView

class AdapterLeaderboardProfiles(var context: Context) :RecyclerView.Adapter<AdapterLeaderboardProfiles.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val image:CircleImageView = itemView.findViewById(R.id.led_profile_image);
        val pos:TextView = itemView.findViewById(R.id.textPosition);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_leaderboard_profiles, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.pos.text = "${position + 2}"
        Glide.with(context).load("https://api.lorem.space/image/face?w=15$position&h=15$position").thumbnail(0.5f).into(holder.image);

    }

    override fun getItemCount(): Int {

        return 4
    }
}