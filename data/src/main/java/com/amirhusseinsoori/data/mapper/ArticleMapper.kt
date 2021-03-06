package com.amirhusseinsoori.data.mapper



import com.amirhusseinsoori.data.db.entity.ArticleEntity
import com.amirhusseinsoori.domain.entity.Article
import com.example.data.mapper.EntityMapper
import javax.inject.Inject

class ArticleMapper @Inject constructor(): EntityMapper<Article, ArticleEntity> {

    override fun mapFromEntity(entity: Article): ArticleEntity {
        return ArticleEntity(
            author = entity.author,
            content = entity.content,
            description = entity.description,
            publishedAt = entity.publishedAt,
            source = entity.source,
            title = entity.title,
            url = entity.url,
            urlToImage = entity.urlToImage,
            id = entity.id
        )
    }

    override fun mapToEntity(domainModel: ArticleEntity): Article {
        return Article(
            author = domainModel.author,
            content = domainModel.content,
            description = domainModel.description,
            publishedAt = domainModel.publishedAt,
            source = domainModel.source,
            title = domainModel.title,
            url = domainModel.url,
            urlToImage = domainModel.urlToImage,
            id = domainModel.id
        )
    }

    override fun mapFromEntityList(entities: List<Article>): List<ArticleEntity> {
       return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntityList(domains: List<ArticleEntity>): List<Article> {
        return domains.map { mapToEntity(it) }
    }
}