package com.isidroid.b21.clean.presentation.main

import androidx.databinding.ViewDataBinding
import com.bumptech.glide.load.engine.GlideException
import com.isidroid.b21.GlideApp
import com.isidroid.b21.R
import com.isidroid.b21.databinding.ItemPostBinding
import com.isidroid.b21.extensions.visible
import com.isidroid.b21.models.Post
import com.isidroid.b21.utils.views.adapters.CoreBindAdapter
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class PostsAdapter : CoreBindAdapter<Post>() {
    private val dateFormat by lazy { SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()) }
    override fun resource(viewType: Int) = R.layout.item_post

    override fun onBindHolder(binding: ViewDataBinding, position: Int) {
        (binding as? ItemPostBinding)?.apply {
            val post = items[position]

            titleView.text = post.text.orEmpty()
            usernameView.text = post.user?.name
            createdAtView.text = dateFormat.format(post.createdAt)
            likesView.text = post.likes.toString()
            viewsView.text = post.views.toString()
            shareView.text = post.shares.toString()


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