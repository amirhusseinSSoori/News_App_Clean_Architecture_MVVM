package com.amirhusseinsoori.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.amirhusseinsoori.common.BaseFragment
import com.amirhusseinsoori.newsapp.R
import com.amirhusseinsoori.newsapp.adapters.NewsAdapter
import com.amirhusseinsoori.newsapp.databinding.FragmentBreakingNewsBinding
import com.amirhusseinsoori.newsapp.ui.viewModel.NewsViewModel
import com.amirhusseinsoori.newsapp.util.LoadStateAdapterNews
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BreakingNewsFragment :
    BaseFragment<FragmentBreakingNewsBinding>(FragmentBreakingNewsBinding::inflate) {

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
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.breakingNews().collect {
                binding.rvBreakingNews.setHasFixedSize(true)
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
                binding.rvBreakingNews.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = LoadStateAdapterNews { adapter.retry() },
                    footer = LoadStateAdapterNews { adapter.retry() },
                )

                adapter.addLoadStateListener { loadState ->
                    binding.progressbarCallVideo.isVisible =
                        loadState.source.refresh is LoadState.Loading
                    binding.rvBreakingNews.isVisible =
                        loadState.source.refresh is LoadState.NotLoading
                    binding.buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                    binding.textViewError.isVisible = loadState.source.refresh is LoadState.Error

                    // empty view
                    if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        adapter.itemCount < 1
                    ) {
                        binding.rvBreakingNews.isVisible = false
                        binding.textViewEmpty.isVisible = true
                    } else {
                        binding.textViewEmpty.isVisible = false
                    }

                }
            }
        }
    }


}




