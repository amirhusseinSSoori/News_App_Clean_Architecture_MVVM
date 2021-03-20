package com.amirhusseinsoori.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.amirhusseinsoori.newsapp.R
import com.amirhusseinsoori.newsapp.adapters.NewsAdapter
import com.amirhusseinsoori.newsapp.ui.viewModel.NewsViewModel
import com.amirhusseinsoori.newsapp.util.LoadStateAdapterNews
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private lateinit var adapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewsAdapter()
        setupCollect()

        button_retry.setOnClickListener {
            setupCollect()

        }


    }

    private fun setupCollect() {
        viewModel.getSource().observe(viewLifecycleOwner, {

            rvBreakingNews.setHasFixedSize(true)
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
            rvBreakingNews.adapter = adapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapterNews { adapter.retry() },
                footer = LoadStateAdapterNews { adapter.retry() },
            )

            adapter.addLoadStateListener { loadState ->
                progressbarCallVideo.isVisible = loadState.source.refresh is LoadState.Loading
                rvBreakingNews.isVisible = loadState.source.refresh is LoadState.NotLoading
                button_retry.isVisible = loadState.source.refresh is LoadState.Error
                text_view_error.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    rvBreakingNews.isVisible = false
                    text_view_empty.isVisible = true
                } else {
                    text_view_empty.isVisible = false
                }

            }
        })
    }


}
