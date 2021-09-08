package com.example.newsapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.model.newsApi.dataModel.NewsArticle
import com.example.newsapp.model.newsApi.dataModel.NewsArticlesModel
import com.example.newsapp.ui.newsArticlesList.NewsArticlesFragment
import com.example.newsapp.ui.newsDescription.NewsArticleDescriptionFragment
import com.example.newsapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: NewsArticlesViewModel by viewModels()
    private lateinit var searchEditText: EditText
    val TAG = "FragmentTag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var searchQuery: String? = null
        val binding = ActivityMainBinding.inflate(layoutInflater)
        searchEditText = binding.searchEditText

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.button.isEnabled = s.toString().trim { it <= ' ' }.isNotEmpty()
                searchQuery = s.toString().trim()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })

        binding.button.setOnClickListener {
            try {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0);
            } catch (e: Exception) {
                // TODO: handle exception
            }
            searchQuery?.let {
                viewModel.setState(
                    NewsArticlesViewModel.NewsArticleViewModelAction.SearchButtonClicked(
                        it
                    )
                )
            }

        }

        viewModel.dataState.observe(this, Observer {
            when (it) {
                is DataState.Success -> {
                    showNewsArticles(it.data)
                }
                is DataState.Error -> {
                    Log.e("MainActivity", it.exception.localizedMessage)
                }
            }
        })

        viewModel.itemClickedDataState.observe(this, Observer {
            when (it) {
                is DataState.Success -> {
                    showDescription(it.data)
                }
                is DataState.Error -> {
                    Log.e("MainActivity", "Illegal State Excption Unknow error ")
                }
            }
        })

        setContentView(binding.root)
    }

    private fun showNewsArticles(newsArticlesModel: NewsArticlesModel) {
        val fragment = NewsArticlesFragment.getInstance(this, newsArticlesModel)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(TAG)
        transaction.commit()
    }

    private fun showDescription(newsArticle: NewsArticle) {
        searchEditText.visibility = View.GONE
        val fragment = NewsArticleDescriptionFragment.getInstance(this, newsArticle)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment==null) {
            super.onBackPressed()
            //additional code
        } else {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(fragment)
            transaction.commit()
            searchEditText.visibility = View.VISIBLE
        }
    }
}