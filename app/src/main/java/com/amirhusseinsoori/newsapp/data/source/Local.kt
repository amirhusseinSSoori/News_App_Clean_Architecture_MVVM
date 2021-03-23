package com.amirhusseinsoori.newsapp.data.source

import com.amirhusseinsoori.newsapp.data.db.ArticleDao
import javax.inject.Inject

class Local @Inject constructor(val db:ArticleDao) {


}