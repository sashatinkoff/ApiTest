package com.isidroid.b21.clean.presentation.main

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import com.isidroid.b21.R
import com.isidroid.b21.appComponent
import com.isidroid.b21.clean.presentation.detail.DetailActivity
import com.isidroid.b21.models.Post
import com.isidroid.b21.network.ApiPosts
import com.isidroid.b21.utils.BindActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_sorting.view.*
import javax.inject.Inject

class MainActivity : BindActivity(layoutRes = R.layout.activity_main), IMainView {
    @Inject
    lateinit var presenter: MainPresenter
    private val adapter = PostsAdapter(viewState = this).onLoadMore { presenter.load(false) }
    private val sortingPopup by lazy { createSortingPopup() }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent().inject(this)
        super.onCreate(savedInstanceState)
        presenter.attach(viewState = this, lifecycle = lifecycle)

        createAdapters()
        createToolbar()

        presenter.load(reset = true)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    // View initialization
    private fun createAdapters() {
        recyclerView.adapter = adapter
        swipeLayout.setOnRefreshListener {
            presenter.load(reset = true)
        }
    }

    private fun createToolbar() = toolbar.apply {
        menu.findItem(R.id.action_sort).actionView?.textView?.setOnClickListener { sortingPopup.show() }
    }

    private fun sortOption(id: Int) = ApiPosts.SortOptions.values()[id]
    private fun sortTitle(sort: ApiPosts.SortOptions) = when (sort) {
        ApiPosts.SortOptions.POPULAR -> R.string.sort_popular
        ApiPosts.SortOptions.CREATED_AT -> R.string.sort_created_at
        ApiPosts.SortOptions.COMMENTED -> R.string.sort_commented
    }.let { getString(it) }

    private fun createSortingPopup() = PopupMenu(this, toolbar, Gravity.END).apply {
        ApiPosts.SortOptions.values().forEachIndexed { index, sortOptions ->
            menu.add(0, index, 0, sortTitle(sortOptions))
        }

        setOnMenuItemClickListener {
            val sort = sortOption(it.itemId)
            toolbar.menu.findItem(R.id.action_sort).actionView?.textView?.text = sortTitle(sort)
            presenter.load(reset = true, sort = sort)

            true
        }
    }

    // IMainView
    override fun onPostsReady(
        posts: List<Post>?,
        hasNext: Boolean
    ) {
        swipeLayout.isRefreshing = false
        adapter.insert(posts.orEmpty(), hasNext)
    }

    override fun clearList() {
        swipeLayout.isRefreshing = true
        adapter.reset()
    }

    override fun open(post: Post) {
        DetailActivity.open(caller = this, post = post)
    }

    override fun onError(t: Throwable) {
        swipeLayout.isRefreshing = false
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }
}