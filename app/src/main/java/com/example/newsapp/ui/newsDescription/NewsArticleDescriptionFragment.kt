package com.example.newsapp.ui.newsDescription

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebSettings.LayoutAlgorithm
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.FragmentNewsDescriptionBinding
import com.example.newsapp.model.newsApi.dataModel.NewsArticle

class NewsArticleDescriptionFragment : Fragment() {

    var newsArticle: NewsArticle? = null
    var searchWebUrl: String? = null
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
        initProperties(binding.webView)
        newsArticle?.let {
            it.webUrl?.let { webUrl ->
                searchWebUrl = webUrl
                binding.webView.loadUrl(webUrl)
            }
        }

        binding.shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, searchWebUrl)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initProperties(webview: WebView) {
        val settings: WebSettings = webview.getSettings()
        settings.layoutAlgorithm = LayoutAlgorithm.TEXT_AUTOSIZING
        settings.builtInZoomControls = true
        settings.useWideViewPort = true
        settings.mediaPlaybackRequiresUserGesture = false
        settings.displayZoomControls = false
        settings.javaScriptEnabled = true
        settings.allowFileAccess = false
        webview.clearCache(true)
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