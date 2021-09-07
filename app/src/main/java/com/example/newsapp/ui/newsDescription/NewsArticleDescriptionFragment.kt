package com.example.newsapp.ui.newsDescription

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.FragmentNewsDescriptionBinding
import com.example.newsapp.model.newsApi.dataModel.NewsArticle

class NewsArticleDescriptionFragment : Fragment() {

    var newsArticle: NewsArticle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentNewsDescriptionBinding.inflate(inflater)
        arguments?.let {
            newsArticle = it[NEWS_ARTICLE_MODEL_ARTICLE] as NewsArticle
        }
        newsArticle?.let {
            binding.descriptionHeadline.text = it.headline.headlineMain
            binding.descriptionScreenText.text = it.abstract
        }


        return binding.root
    }


    companion object {
        val NEWS_ARTICLE_MODEL_ARTICLE = "NewsArticleModel:NewsArticle "

        fun getInstance(
            context: Context,
            newsArticle: NewsArticle
        ): NewsArticleDescriptionFragment {
            val fragment = NewsArticleDescriptionFragment()
            val bundle = Bundle(1)
            bundle.putParcelable(NEWS_ARTICLE_MODEL_ARTICLE, newsArticle)
            fragment.arguments = bundle
            return fragment
        }
    }
}