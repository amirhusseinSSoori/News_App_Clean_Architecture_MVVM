package com.amirhusseinsoori.newsapp.repository

import com.amirhusseinsoori.newsapp.db.ArticleDao
import javax.inject.Inject

class NewsRepository @Inject constructor(val db:ArticleDao) {
}