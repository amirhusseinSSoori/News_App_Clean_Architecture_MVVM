package com.amirhusseinsoori.newsapp.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.amirhusseinsoori.domain.entity.ArticleDomain
import com.amirhusseinsoori.newsapp.R
import com.amirhusseinsoori.newsapp.common.BaseFragment
import com.amirhusseinsoori.newsapp.presentation.adapters.NewsAdapter
import com.amirhusseinsoori.newsapp.databinding.FragmentSearchNewsBinding
import com.amirhusseinsoori.newsapp.presentation.adapters.LoadStateAdapterNews
import com.amirhusseinsoori.newsapp.presentation.ui.viewModel.SearchViewModel
import com.amirhusseinsoori.newsapp.presentation.util.onTextChange
import com.amirhusseinsoori.newsapp.presentation.util.sendArgByGson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SearchNewsFragment :
    BaseFragment<FragmentSearchNewsBinding>(FragmentSearchNewsBinding::inflate),
    NewsAdapter.OnBreakingListener {


    private val viewModel: SearchViewModel by viewModels()

    lateinit var adapterNews: NewsAdapter

    @Inject
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterNews = NewsAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        R.id.saved_List
        binding.etSearch.onTextChange {
            if (!etSearch.text.trim().toString().isNullOrEmpty()) {
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                            searchOnCollect(it)
                        }
                    }
                }, 1000)
            }
        }





    }

    private suspend fun searchOnCollect(q: String) {
        viewModel.searchNews(q).collect { search ->
            adapterNews.submitData(viewLifecycleOwner.lifecycle, search)
            binding.rvSearchNews.adapter = adapterNews
        }
        binding.rvSearchNews.adapter = adapterNews.withLoadStateHeaderAndFooter(
            header = LoadStateAdapterNews { adapterNews.retry() },
            footer = LoadStateAdapterNews { adapterNews.retry() },
        )

        adapterNews.addLoadStateListener { loadState ->
            binding.progressbarCallVideoSearch.isVisible =
                loadState.source.refresh is LoadState.Loading
            binding.rvSearchNews.isVisible =
                loadState.source.refresh is LoadState.NotLoading
            binding.buttonRetrySearch.isVisible =
                loadState.source.refresh is LoadState.Error
            binding.textViewErrorSearch.isVisible =
                loadState.source.refresh is LoadState.Error

            // empty view
            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached &&
                adapterNews.itemCount < 1
            ) {
                binding.rvSearchNews.isVisible = false
                binding.textViewEmptySearch.isVisible = true
            } else {
                binding.textViewEmptySearch.isVisible = false
            }
        }
    }

    override fun onBreakingItemClick(item: ArticleDomain) {

        val action = SearchNewsFragmentDirections.actionSearchNewsFragmentToArticleFragment(
            sendArgByGson(item)
        )
        findNavController().navigate(action)
    }

}
