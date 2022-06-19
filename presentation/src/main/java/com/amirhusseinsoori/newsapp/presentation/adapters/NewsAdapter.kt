package com.amirhusseinsoori.newsapp.presentation.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.amirhusseinsoori.newsapp.databinding.ItemArticlePreviewBinding
import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class NewsAdapter(private val interaction: OnBreakingListener? = null) :
    PagingDataAdapter<ArticleDomain, NewsAdapter.BreakingNewsViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticleDomain>() {

            override fun areItemsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain) = oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: ArticleDomain, newItem: ArticleDomain) = oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingNewsViewHolder {
        val binding =
            ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreakingNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreakingNewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    inner class BreakingNewsViewHolder
    constructor(
        private val binding: ItemArticlePreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item != null) interaction?.onBreakingItemClick(item)
                }
            }
        }

        fun bind(item: ArticleDomain) = binding.apply {
            var shimmer =Shimmer.ColorHighlightBuilder()
                .setBaseColor(Color.parseColor("#F3F3F3"))
                .setBaseAlpha(1F)
                .setHighlightColor(Color.parseColor("#E7E7E7"))
                .setHighlightAlpha(1F)
                .setDropoff(50F)
                .build()
            var shimmerDrawable=ShimmerDrawable()
            shimmerDrawable.setShimmer(shimmer)





            Glide.with(root).load(item.urlToImage).placeholder(shimmerDrawable).into(ivArticleImage)
            tvSource.text = item.source!!.name
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvPublishedAt.text = item.publishedAt
        }
    }


//    private var onItemClickListener: ((Article) -> Unit)? = null
//    fun setOnItemClickListener(listener: (Article) -> Unit){
//        onItemClickListener = listener
//    }


    interface OnBreakingListener {
        fun onBreakingItemClick(item: ArticleDomain)
    }


}