package com.isidroid.b21.clean.presentation.main

import androidx.databinding.ViewDataBinding
import com.isidroid.b21.GlideApp
import com.isidroid.b21.R
import com.isidroid.b21.databinding.ItemPostBinding
import com.isidroid.b21.extensions.visible
import com.isidroid.b21.models.Post
import com.isidroid.b21.utils.views.adapters.CoreBindAdapter
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class PostsAdapter(private val viewState: IMainView) : CoreBindAdapter<Post>() {

    private val dateFormat by lazy { SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()) }
    override fun resource(viewType: Int) = R.layout.item_post

    override fun onBindHolder(binding: ViewDataBinding, position: Int) {
        (binding as? ItemPostBinding)?.apply {
            val post = items[position]
            root.setOnClickListener { viewState.open(post = post) }

            titleView.text = post.text.orEmpty()
            usernameView.text = post.user?.name ?: "Anonymous"
            createdAtView.text = dateFormat.format(post.createdAt)
            likesView.text = NumberFormat.getInstance().format(post.likes)
            viewsView.text = NumberFormat.getInstance().format(post.views)
            shareView.text = NumberFormat.getInstance().format(post.shares)

            GlideApp.with(avatarView)
                .load(post.user?.image ?: "https://r0.mradx.net/img/EF/17888D.jpg").circleCrop()
                .into(avatarView)
            post.images?.firstOrNull()
                ?.let {
                    imageView.visible(true)
                    GlideApp.with(imageView).load(it.small).into(imageView)
                }
                ?: run { imageView.visible(false) }
        }

    }
}