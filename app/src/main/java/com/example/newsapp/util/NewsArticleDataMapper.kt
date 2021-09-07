package com.example.newsapp.util

import android.app.DownloadManager
import com.example.newsapp.database.NewsArticleEntity
import com.example.newsapp.model.newsApi.dataModel.NewsArticle
import com.example.newsapp.model.newsApi.dataModel.NewsArticlesModel
import com.example.newsapp.model.newsApi.dataModel.NewsDocsModel
import com.example.newsapp.model.newsApi.dataModel.NewsHeadlineModel
import com.example.newsapp.model.newsApi.dataModel.NewsMultimediaModel
import com.example.newsapp.model.newsApi.networkModel.NewsArticleNetworkModel
import com.example.newsapp.model.newsApi.networkModel.NewsArticlesNetworkModel

class NewsArticleDataMapper {

    companion object {

        fun apiToNetworkModel(apiNetworkModel: NewsArticlesNetworkModel): NewsArticlesModel {
            val documentList =
                apiNetworkModel.response.documentsList
            val newsArticles = mutableListOf<NewsArticle>()
            documentList.forEach {
                newsArticles.add(newsArticleApiToNewsArticle(it))
            }
            val newsDocsModel = NewsDocsModel(newsArticles)
            return NewsArticlesModel(newsDocsModel)
        }

        private fun newsArticleApiToNewsArticle(newsArticleNetworkModel: NewsArticleNetworkModel): NewsArticle {
            val headlineModel = NewsHeadlineModel(
                headlineMain = newsArticleNetworkModel.headline.headlineMain,
                headlineName = newsArticleNetworkModel.headline.headlineName,
                printHeadline = newsArticleNetworkModel.headline.printHeadline
            )
            val newsMultimediaModel = mutableListOf<NewsMultimediaModel>()
            newsArticleNetworkModel.multimedia.forEach {
                val data = NewsMultimediaModel(
                    it.caption,
                    it.imageUrl
                )
                newsMultimediaModel.add(data)
            }
            return NewsArticle(
                abstract = newsArticleNetworkModel.abstract,
                headline = headlineModel,
                webUrl = newsArticleNetworkModel.webUrl,
                snippet = newsArticleNetworkModel.snippet,
                leadParagraph = newsArticleNetworkModel.leadParagraph,
                multimedia = newsMultimediaModel
            )
        }

        fun databaseEntryToNewsArticlesModel(newsArticleEntity: NewsArticleEntity): NewsArticlesModel {
            return newsArticleEntity.newsArticlesModel
        }

        fun apiToDataBaseEntity(
            query: String,
            newsArticlesModel: NewsArticlesModel
        ): NewsArticleEntity {
            return NewsArticleEntity(query = query, newsArticlesModel = newsArticlesModel)
        }
    }
}