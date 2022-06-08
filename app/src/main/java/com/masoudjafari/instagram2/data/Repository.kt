package com.masoudjafari.instagram2.data

import com.masoudjafari.instagram2.data.remote.RetrofitService

class Repository constructor(private val retrofitService: RetrofitService) : DataSource  {

    override suspend fun getPosts(instagramID :String, fields :String, token :String) =
        retrofitService.getPosts(instagramID, fields, token)
}