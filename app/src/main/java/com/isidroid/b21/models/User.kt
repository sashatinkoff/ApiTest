package com.isidroid.b21.models

import java.io.Serializable

data class User(val id: String, val name: String, val image: String? = null): Serializable