package com.amirhusseinsoori.newsapp.data.network.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amirhusseinsoori.newsapp.R
import kotlinx.android.synthetic.main.chat_history_load_state_footer.view.*

class LoadStateAdapterNews(private val retry: () -> Unit) :
    LoadStateAdapter<LoadStateAdapterNews.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {


        return Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_history_load_state_footer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.itemView.btnRetryFooter.setOnClickListener {

            retry.invoke()
        }


        holder.itemView.progressBarRetry.isVisible = loadState is LoadState.Loading
        holder.itemView.btnRetryFooter.isVisible = loadState !is LoadState.Loading
        holder.itemView.txtRetryError.isVisible = loadState !is LoadState.Loading
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


