package com.masoudjafari.instagram2.data

import retrofit2.Response

interface DataSource {
    suspend fun getPosts(instagramID :String, fields :String, token :String) : Response<Posts>?
}