package com.amirhusseinsoori.newsapp.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.amirhusseinsoori.newsapp.R
import com.amirhusseinsoori.newsapp.adapters.NewsAdapter
import com.amirhusseinsoori.newsapp.ui.viewModel.NewsViewModel
import com.amirhusseinsoori.newsapp.ui.viewModel.SearchViewModel
import com.amirhusseinsoori.newsapp.util.LoadStateAdapterNews
import com.amirhusseinsoori.newsapp.util.onTextChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import java.util.*

@AndroidEntryPoint
class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {


    private val viewModel: SearchViewModel by viewModels()
    lateinit var adapterNews: NewsAdapter
    private val handlerException = CoroutineExceptionHandler { _, throwable ->

        Log.e("Error", "onViewCreated:${throwable.message}")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterNews = NewsAdapter()

        //  searchOnCollect("")
        etSearch.onTextChange {
            if (!etSearch.text.trim().toString().isNullOrEmpty()) {
                Timer().schedule(object : TimerTask() {
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

        lifecycleScope.launch(Dispatchers.Main + Job()+handlerException) {
            viewModel.searchNews(q)
                .collect {
                    rvSearchNews.setHasFixedSize(true)

                    adapterNews.submitData(viewLifecycleOwner.lifecycle, it)


                    rvSearchNews.adapter = adapterNews


//                    rvSearchNews.adapter = adapterNews.withLoadStateHeaderAndFooter(
//                        header = LoadStateAdapterNews { adapterNews.retry() },
//                        footer = LoadStateAdapterNews { adapterNews.retry() },
//                    )


//                    adapterNews.addLoadStateListener { loadState ->
//                        progressbarCallVideo_search.isVisible =
//                            loadState.source.refresh is LoadState.Loading
//                        rvSearchNews.isVisible = loadState.source.refresh is LoadState.NotLoading
//                        button_retry_search.isVisible = loadState.source.refresh is LoadState.Error
//                        text_view_error_search.isVisible =
//                            loadState.source.refresh is LoadState.Error
//
//                        // empty view
//                        if (loadState.source.refresh is LoadState.NotLoading &&
//                            loadState.append.endOfPaginationReached &&
//                            adapterNews.itemCount < 1
//                        ) {
//                            rvSearchNews.isVisible = false
//                            text_view_empty_search.isVisible = true
//                        } else {
//                            text_view_empty_search.isVisible = false
//                        }
//
//                    }
                }
        }

    }
}