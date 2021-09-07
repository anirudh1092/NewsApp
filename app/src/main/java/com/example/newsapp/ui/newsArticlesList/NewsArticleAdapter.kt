package com.example.newsapp.ui.newsArticlesList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.CellNewsArticleBinding
import com.example.newsapp.model.newsApi.dataModel.NewsArticle
import com.example.newsapp.ui.NewsArticlesViewModel

class NewsArticleAdapter constructor(
    val context: Context,
    val data: List<NewsArticle>,
    val viewModel: NewsArticlesViewModel
) :
    RecyclerView.Adapter<NewsArticleAdapter.ViewHolder>() {


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        var imageView: ImageView
        var headline: TextView
        var description: TextView

        init {
            val binding = CellNewsArticleBinding.bind(item)
            imageView = binding.cellImageView
            headline = binding.cellHeadline
            description = binding.cellDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cell_news_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = data[position]
        holder.headline.text = currentData.headline.headlineMain
        holder.description.text = currentData.leadParagraph
        if (currentData.multimedia.isNotEmpty()) {
            currentData.multimedia[0].imageUrl?.let {
                val imageUrl = "https://www.nytimes.com/" + currentData.multimedia[0].imageUrl
                Glide.with(holder.imageView).asBitmap().load(imageUrl)
                    .into(holder.imageView)
            }
        }

        holder.itemView.setOnClickListener {
            onItemClickedListener(currentData)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    private fun onItemClickedListener(currentData: NewsArticle) {
        viewModel.setState(
            NewsArticlesViewModel.NewsArticleViewModelAction.RecyclerViewItemClicked(
                currentData
            )
        )
    }
}