package com.amirhusseinsoori.newsapp.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.amirhusseinsoori.domain.entity.Article
import com.amirhusseinsoori.newsapp.common.BaseFragment
import com.amirhusseinsoori.newsapp.presentation.adapters.NewsAdapter
import com.amirhusseinsoori.newsapp.databinding.FragmentBreakingNewsBinding
import com.amirhusseinsoori.newsapp.presentation.ui.viewModel.NewsViewModel
import com.amirhusseinsoori.newsapp.data.network.paging.LoadStateAdapterNews
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BreakingNewsFragment :
    BaseFragment<FragmentBreakingNewsBinding>(FragmentBreakingNewsBinding::inflate),
    NewsAdapter.OnBreakingListener {

    private lateinit var adapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewsAdapter(this)
        binding.shimmer.startShimmer()

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

                binding.shimmer.stopShimmer()
                binding.shimmer.visibility = View.GONE
            }
        }
    }

    override fun onBreakingItemClick(item: Article) {
        val action =
            BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleFragment(item)
        findNavController().navigate(action)
    }


}




