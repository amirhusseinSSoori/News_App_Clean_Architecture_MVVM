package com.amirhusseinsoori.newsapp.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.amirhusseinsoori.newsapp.common.BaseFragment
import com.amirhusseinsoori.newsapp.databinding.FragmentSavedNewsBinding
import com.amirhusseinsoori.newsapp.presentation.ui.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment:BaseFragment<FragmentSavedNewsBinding>(FragmentSavedNewsBinding::inflate) {
    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}