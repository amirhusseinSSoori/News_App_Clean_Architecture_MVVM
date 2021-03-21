package com.amirhusseinsoori.newsapp.source

import com.amirhusseinsoori.newsapp.db.ArticleDao
import javax.inject.Inject

class Local @Inject constructor(val db:ArticleDao) {


}