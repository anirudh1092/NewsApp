package com.example.newsapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.BuildConfig
import com.example.newsapp.model.newsApi.dataModel.NewsArticle
import com.example.newsapp.model.newsApi.dataModel.NewsArticlesModel
import com.example.newsapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsArticlesViewModel
@Inject constructor(
    private val repository: NewApiRepository
) : ViewModel() {

    private val cacheMap = mutableMapOf<String, NewsArticlesModel>()
    val dataState: MutableLiveData<DataState<NewsArticlesModel>> = MutableLiveData()
    val itemClickedDataState: MutableLiveData<DataState<NewsArticle>> = MutableLiveData()

    fun setState(action: NewsArticleViewModelAction) {
        viewModelScope.launch(Dispatchers.IO) {
            when (action) {
                is NewsArticleViewModelAction.SearchButtonClicked -> {
                    getApiData(action.query)
                }
                is NewsArticleViewModelAction.ApiResultSuccessful -> {
                    dataState.postValue(DataState.Success(action.newsArticlesModel))
                }
                is NewsArticleViewModelAction.ApiResultFailed -> {
                    dataState.postValue(DataState.Error(action.exception))
                }

                is NewsArticleViewModelAction.RecyclerViewItemClicked -> {
                    itemClickedDataState.postValue(DataState.Success(action.newsArticlesModel))
                }
            }
        }
    }

    private suspend fun getApiData(query: String) {
        val flowable =
            repository.getNewsArticleService(query, cacheMap)
        flowable.collect {
            when (it) {
                is DataState.Success -> {
                    cacheMap[query] = it.data
                    setState(NewsArticleViewModelAction.ApiResultSuccessful(it.data))
                }
                is DataState.Error -> {
                    setState(NewsArticleViewModelAction.ApiResultFailed(it.exception))
                }

                is DataState.Loading -> {
                    // Todo: Based on the expected Logic
                }
            }
        }
    }

    sealed class NewsArticleViewModelAction {
        data class SearchButtonClicked(val query: String) : NewsArticleViewModelAction()
        data class ApiResultSuccessful(val newsArticlesModel: NewsArticlesModel) :
            NewsArticleViewModelAction()

        data class ApiResultFailed(val exception: Exception) : NewsArticleViewModelAction()
        data class RecyclerViewItemClicked(val newsArticlesModel: NewsArticle) :
            NewsArticleViewModelAction()
    }
}