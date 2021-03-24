package com.amirhusseinsoori.data.source

import com.amirhusseinsoori.data.db.ArticleDao
import javax.inject.Inject

class Local @Inject constructor(val db: ArticleDao) {


}