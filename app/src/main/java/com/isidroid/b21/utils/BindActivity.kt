package com.isidroid.b21.utils

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.isidroid.b21.utils.mvp.BasePresenter
import javax.inject.Inject

abstract class BindActivity(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(this, layoutRes)
    }
}