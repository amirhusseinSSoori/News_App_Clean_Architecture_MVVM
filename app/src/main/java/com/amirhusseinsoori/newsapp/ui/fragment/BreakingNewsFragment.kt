package com.amirhusseinsoori.newsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amirhusseinsoori.newsapp.R
import com.amirhusseinsoori.newsapp.ui.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakingNewsFragment:Fragment(R.layout.fragment_breaking_news) {


    private val viewModel: NewsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}