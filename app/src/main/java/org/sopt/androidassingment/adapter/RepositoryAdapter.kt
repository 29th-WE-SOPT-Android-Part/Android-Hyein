package org.sopt.androidassingment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidassingment.data.RepositoryData
import org.sopt.androidassingment.databinding.ItemRepositoryListBinding

class RepositoryAdapter: RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    val repositoryList = mutableListOf<RepositoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(repositoryList[position])
    }

    override fun getItemCount(): Int = repositoryList.size

    class RepositoryViewHolder(private val binding: ItemRepositoryListBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepositoryData){
            binding.tvName.text = data.repo_name
            binding.tvIntroduce.text = data.repo_introduction
        }
    }
}