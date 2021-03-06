package org.sopt.androidassingment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidassingment.data.FollowerData
import org.sopt.androidassingment.databinding.ItemFollowerListBinding

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val followerList = mutableListOf<FollowerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size

    class FollowerViewHolder(private val binding: ItemFollowerListBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData){
            //데이터 바인딩
            binding.follower = data
        }
    }
}