package com.masoudjafari.instagram2.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.masoudjafari.instagram2.databinding.ActivityLoginBinding
import com.masoudjafari.instagram2.ui.posts.PostsActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var callbackManager: CallbackManager
    private lateinit var binding: ActivityLoginBinding
    private val permissions = listOf(
        "email", "instagram_basic",
        "pages_show_list",
        "instagram_manage_insights",
        "pages_manage_engagement"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initialize() {
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(application)

        callbackManager = CallbackManager.Factory.create()

        binding.toolbar.tvToolbarTitle.text = "ورود به حساب کاربری"

        val loginButton = binding.loginButton
        loginButton.setReadPermissions(permissions)

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onCancel() {
                Toast.makeText(applicationContext, "request canceled", Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: FacebookException) {
                Toast.makeText(applicationContext, "$exception", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(result: LoginResult?) {
                Toast.makeText(applicationContext, "ورود با موفقیت انجام شد", Toast.LENGTH_SHORT).show()
                startPostsActivity()
            }
        })

        if (checkLoginStatus() != "false")
            startPostsActivity()
    }

    private fun checkLoginStatus(): String {
        val accessToken = AccessToken.getCurrentAccessToken()
        return if (accessToken != null && !accessToken.isExpired)
            accessToken.token
        else
            "false"
    }

    private fun startPostsActivity() {
        startActivity(Intent(this, PostsActivity::class.java))
    }
}