package com.goscale.assignment.view.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.goscale.assignment.R
import com.goscale.assignment.data.network.Result
import com.goscale.assignment.view.adapter.BookmarkListAdapter
import com.goscale.assignment.viewmodel.ShowSearchViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_search.*
import timber.log.Timber
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var showSearchViewModel: ShowSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        showSearchViewModel =
            ViewModelProvider(this, viewModelFactory).get(ShowSearchViewModel::class.java)

        val bookmarkListAdapter = BookmarkListAdapter(emptyList())
        recyclerBookmarks.adapter = bookmarkListAdapter

        val searchResultsAdapter = BookmarkListAdapter(emptyList(), BookmarkListAdapter.Type.SEARCH_RESULTS)
        recyclerSearchResults.adapter = searchResultsAdapter

        bookmarkListAdapter.bookmarkUpdate.observe(this, Observer { show ->
            showSearchViewModel.updateBookmarkStatus(show.title, show.type, show.isBookmarked)
        })

        searchResultsAdapter.bookmarkUpdate.observe(this, Observer { show ->
            showSearchViewModel.updateBookmarkStatus(show.title, show.type, show.isBookmarked)
        })

        showSearchViewModel.bookmarks.observe(this, Observer { shows ->
            bookmarkListAdapter.updateShowsData(shows)
        })

        showSearchViewModel.shows.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.let {
                        searchResultsAdapter.updateShowsData(it)
                    }
                }
                Result.Status.LOADING -> {
                }
                Result.Status.ERROR -> {
                    Snackbar.make(rootSearchView, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow
            Timber.d("Query -> $query")
            if (!query.isNullOrBlank()) {
                showSearchViewModel.updateShowTitle(query)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showSearchViewModel.cancelJobs()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }

}
