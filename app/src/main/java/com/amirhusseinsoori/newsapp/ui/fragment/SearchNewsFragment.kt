package com.amirhusseinsoori.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.amirhusseinsoori.common.BaseFragment
import com.amirhusseinsoori.newsapp.adapters.NewsAdapter
import com.amirhusseinsoori.newsapp.databinding.FragmentSearchNewsBinding
import com.amirhusseinsoori.newsapp.ui.viewModel.SearchViewModel
import com.amirhusseinsoori.newsapp.paging.LoadStateAdapterNews
import com.amirhusseinsoori.newsapp.util.onTextChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SearchNewsFragment :
    BaseFragment<FragmentSearchNewsBinding>(FragmentSearchNewsBinding::inflate) {


    private val viewModel: SearchViewModel by viewModels()
    lateinit var adapterNews: NewsAdapter

    @Inject
    lateinit var timer: Timer
    private val handlerException = CoroutineExceptionHandler { _, throwable ->

        Log.e("Error", "show Error Message : ${throwable.message}")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterNews = NewsAdapter()

        //  searchOnCollect("")
        binding.etSearch.onTextChange {
            if (!etSearch.text.trim().toString().isNullOrEmpty()) {
                timer.schedule(object : TimerTask() {
                    override fun run() {

                        CoroutineScope(Dispatchers.Main + Job() + handlerException).launch {
                            searchOnCollect(it)

                        }


                    }
                }, 1000)
            }
        }

    }


    fun searchOnCollect(q: String) {

        lifecycleScope.launch(Dispatchers.Main + Job() + handlerException) {
            viewModel.searchNews(q)
                .collect {
                    rvSearchNews.setHasFixedSize(true)

                    adapterNews.submitData(viewLifecycleOwner.lifecycle, it)


                    binding.rvSearchNews.adapter = adapterNews


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
        }

    }
}