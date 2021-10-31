package org.sopt.androidassingment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.androidassingment.databinding.ItemFollowerListBinding

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val followerList = mutableListOf<FollowerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        Glide.with(parent.context)
            .load("https://avatars.githubusercontent.com/u/68214704?v=4")
            .circleCrop()
            .into(binding.ivProfile)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size

    class FollowerViewHolder(private val binding: ItemFollowerListBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData){
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.introduction
        }
    }
}