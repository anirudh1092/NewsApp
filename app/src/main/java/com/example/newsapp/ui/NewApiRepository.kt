package com.example.newsapp.ui

import com.example.newsapp.BuildConfig
import com.example.newsapp.api.NewsApiService
import com.example.newsapp.database.NewsArticlesDao
import com.example.newsapp.model.newsApi.dataModel.NewsArticlesModel
import com.example.newsapp.util.DataState
import com.example.newsapp.util.NewsArticleDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewApiRepository @Inject constructor(
    private val service: NewsApiService,
    private val newsArticleDao: NewsArticlesDao
) {

    suspend fun getNewsArticleService(
        query: String,
        cacheMap: Map<String, NewsArticlesModel>
    ): Flow<DataState<NewsArticlesModel>> =
        flow {
            try {
                if (cacheMap.containsKey(query)) {
                    cacheMap[query]?.let {
                        emit(DataState.Success(it))
                    }
                } else {
                    try {
                        val databaseEntry = newsArticleDao.getBusinessDataForId(query)
                        emit(
                            DataState.Success(
                                NewsArticleDataMapper.databaseEntryToNewsArticlesModel(
                                    databaseEntry
                                )
                            )
                        )
                    } catch (e: Exception) {
                        emit(DataState.Error(e))
                    } finally {
                        val apiResponse = service.getNewsArticles(query, BuildConfig.API_KEY)
                        // Generate Model from API
                        val newsArticlesModel = NewsArticleDataMapper.apiToNetworkModel(apiResponse)
                        // Generate Entity from Model
                        val newsArticleEntity = NewsArticleDataMapper.apiToDataBaseEntity(
                            query = query,
                            newsArticlesModel = newsArticlesModel
                        )
                        // Save Entity in Database
                        newsArticleDao.saveNewsArticlesData(newsArticleEntity)

                        // Fetch Entity from Database
                        val savedEntity = newsArticleDao.getBusinessDataForId(query)

                        // Convert Entity to Model To Return
                        val convertedModel =
                            NewsArticleDataMapper.databaseEntryToNewsArticlesModel(savedEntity)
                        emit(DataState.Success(convertedModel))
                    }
                }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
}