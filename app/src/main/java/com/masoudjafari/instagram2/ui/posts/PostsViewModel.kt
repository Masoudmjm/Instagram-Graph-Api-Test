package com.masoudjafari.instagram2.ui.posts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.masoudjafari.instagram2.data.Posts
import com.masoudjafari.instagram2.data.Repository
import kotlinx.coroutines.*


class PostsViewModel constructor(private val repository: Repository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val posts = MutableLiveData<Posts>()

    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    private fun getPosts(instagramID :String, fields :String, token :String) {
        loading.postValue(true)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getPosts(instagramID, fields, token)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    posts.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun getToken(): String {
        val accessToken = AccessToken.getCurrentAccessToken()
        Log.i("myToken", accessToken?.token?: "false")
        return if (accessToken != null && !accessToken.isExpired)
            accessToken.token
        else
            ""
    }

    init {
        getPosts("65456135165212", "business_discovery.username(programmer.me){followers_count,media_count,media{comments_count,like_count,media_url}}", getToken())
    }

}
