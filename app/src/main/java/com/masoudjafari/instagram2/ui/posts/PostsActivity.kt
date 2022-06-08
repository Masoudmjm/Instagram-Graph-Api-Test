package com.masoudjafari.instagram2.ui.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.masoudjafari.instagram2.data.DataItem
import com.masoudjafari.instagram2.data.Repository
import com.masoudjafari.instagram2.data.remote.RetrofitService
import com.masoudjafari.instagram2.databinding.ActivityPostsBinding

class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding
    private lateinit var viewModel: PostsViewModel
    private val postsAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = Repository(retrofitService)
        viewModel = ViewModelProvider(this, PostsViewModelFactory(mainRepository))[PostsViewModel::class.java]

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = postsAdapter
        }

        binding.toolbar.tvToolbarTitle.text = "حساب کاربری"

        setObservers()
    }

    private fun setObservers() {
        viewModel.posts.observe(this) {
            postsAdapter.setList(it.businessDiscovery?.media?.data as List<DataItem>)
            binding.tvFollowers.text = "%,d".format(it.businessDiscovery.followersCount)
            binding.tvMediaCount.text = "%,d".format(it.businessDiscovery.mediaCount)
            binding.tvAccount.text = "programmer.me"
        }

        viewModel.errorMessage.observe(this) {
            showMessage(viewModel.errorMessage.value!!)
        }

        viewModel.loading.observe(this) {
            if (it)
                binding.progressDialog.visibility = View.VISIBLE
            else
                binding.progressDialog.visibility = View.GONE
        }
    }

    private fun showMessage(title: String) {
        Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
    }
}