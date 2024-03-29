package com.amirhusseinsoori.newsapp.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.amirhusseinsoori.data.network.model.Article
import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.newsapp.databinding.ItemArticlePreviewBinding
import com.bumptech.glide.Glide


class SavedNewsAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticleDomain>() {

        override fun areItemsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain) = oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain) = oldItem == newItem

    }
    val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding =
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedNewsViewHolder(binding ,interaction)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SavedNewsViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<ArticleDomain>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }

    class SavedNewsViewHolder
    constructor(
        private val binding: ItemArticlePreviewBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ArticleDomain) = binding.apply {
            root.setOnClickListener {
                interaction?.onItemSelected(item)
            }
            Glide.with(root).load(item.urlToImage).into(ivArticleImage)
            tvSource.text = item.source?.name?:""
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvPublishedAt.text = item.publishedAt

        }
    }

    interface Interaction {
        fun onItemSelected(item: ArticleDomain)
    }
}
