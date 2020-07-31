package com.isidroid.b21.clean.presentation.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import com.isidroid.b21.Const
import com.isidroid.b21.GlideApp
import com.isidroid.b21.R
import com.isidroid.b21.appComponent
import com.isidroid.b21.models.Post
import com.isidroid.b21.utils.BindActivity
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DetailActivity : BindActivity(layoutRes = R.layout.activity_detail), IDetailView {
    @Inject
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent().inject(this)
        super.onCreate(savedInstanceState)

        toolbar.setNavigationOnClickListener { finishAfterTransition() }

        presenter.attach(viewState = this, lifecycle = lifecycle)
        presenter.create(intent.getSerializableExtra(Const.Arg.Post.name) as Post)
    }

    // IDetailView
    override fun onReady(post: Post) {
        collapsedToolbar.title = post.text?.take(25)
        textView.text = post.text
        usernameView.text = post.user?.name ?: "Anonymous"
        createdAtView.text =
            SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(post.createdAt)
        GlideApp.with(headerImageView).load(post.images?.firstOrNull()?.small).into(headerImageView)
        GlideApp.with(avatarView)
            .load(post.user?.image ?: "https://r0.mradx.net/img/EF/17888D.jpg").circleCrop()
            .into(avatarView)
    }

    override fun onError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
        finishAfterTransition()
    }

    companion object {
        fun open(caller: Activity, post: Post) {
            val intent = Intent(caller, DetailActivity::class.java)
                .putExtra(Const.Arg.Post.name, post)

            ActivityCompat.startActivity(caller, intent, null)
        }
    }
}