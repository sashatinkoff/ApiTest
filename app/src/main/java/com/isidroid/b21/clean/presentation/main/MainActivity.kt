package com.isidroid.b21.clean.presentation.main

import android.os.Bundle
import android.widget.Toast
import com.isidroid.b21.R
import com.isidroid.b21.appComponent
import com.isidroid.b21.models.Post
import com.isidroid.b21.utils.BindActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BindActivity(layoutRes = R.layout.activity_main), IMainView {
    @Inject
    lateinit var presenter: MainPresenter
    private val adapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent().inject(this)
        super.onCreate(savedInstanceState)
        presenter.attach(viewState = this, lifecycle = lifecycle)

        createAdapters()

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
            adapter.clear()
            presenter.load(reset = true)
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

    override fun onError(t: Throwable) {
        swipeLayout.isRefreshing = false
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }
}