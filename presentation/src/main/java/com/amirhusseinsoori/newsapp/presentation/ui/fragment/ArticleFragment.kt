package com.amirhusseinsoori.newsapp.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs

import com.amirhusseinsoori.newsapp.common.BaseFragment
import com.amirhusseinsoori.newsapp.databinding.FragmentArticleBinding
import com.amirhusseinsoori.newsapp.presentation.ui.viewModel.ArticleViewModel
import com.amirhusseinsoori.newsapp.presentation.util.getArgByGson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : BaseFragment<FragmentArticleBinding>(FragmentArticleBinding::inflate)
{

    private val viewModel: ArticleViewModel by viewModels()
    private val args: ArticleFragmentArgs by navArgs()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.state.collect(){article->
                binding.webView.apply {
                    webViewClient = WebViewClient()
                    loadUrl(article.url?:"")
                }


            }
        }
        binding.fab.setOnClickListener {
            viewModel.insertArticle(getArgByGson(args.article))
        }


    }





}