package com.example.newsapp.ui.newsArticlesList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.newsapp.databinding.FragmentNewsArticlesBinding
import com.example.newsapp.model.newsApi.dataModel.NewsArticlesModel
import com.example.newsapp.ui.NewsArticlesViewModel

class NewsArticlesFragment : Fragment() {

    var newsArticlesModel: NewsArticlesModel? = null
    private val viewModel: NewsArticlesViewModel by viewModels(ownerProducer = { requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        arguments?.let {
            newsArticlesModel = it.getParcelable(NEWS_ARTICLE_MODEL_IDENTIFIER)
        }
        val binding = FragmentNewsArticlesBinding.inflate(inflater)
        newsArticlesModel?.response?.let {
            val adapter = NewsArticleAdapter(requireContext(), it.articleList, viewModel)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.addItemDecoration(
                DividerItemDecoration(
                    binding.recyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        return binding.root
    }

    companion object {
        val NEWS_ARTICLE_MODEL_IDENTIFIER = "NewsArticleModel:ID"

        fun getInstance(
            context: Context,
            newsArticlesModel: NewsArticlesModel
        ): NewsArticlesFragment {
            val fragment = NewsArticlesFragment()
            val bundle = Bundle(1)
            bundle.putParcelable(NEWS_ARTICLE_MODEL_IDENTIFIER, newsArticlesModel)
            fragment.arguments = bundle
            return fragment
        }
    }
}