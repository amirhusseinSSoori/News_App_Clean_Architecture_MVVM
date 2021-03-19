package com.amirhusseinsoori.newsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.amirhusseinsoori.newsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment:Fragment(R.layout.fragment_article) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}