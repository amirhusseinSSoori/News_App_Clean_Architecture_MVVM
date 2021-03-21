package com.amirhusseinsoori.newsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amirhusseinsoori.common.BaseFragment
import com.amirhusseinsoori.newsapp.R
import com.amirhusseinsoori.newsapp.databinding.FragmentSavedNewsBinding
import com.amirhusseinsoori.newsapp.ui.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment:BaseFragment<FragmentSavedNewsBinding>(FragmentSavedNewsBinding::inflate) {
    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}