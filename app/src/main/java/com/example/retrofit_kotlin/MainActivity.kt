package com.example.retrofit_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_kotlin.adapter.MyAdapter
import com.example.retrofit_kotlin.databinding.ActivityMainBinding
import com.example.retrofit_kotlin.model.Post
import com.example.retrofit_kotlin.repository.Repository
import com.example.retrofit_kotlin.view_model.ViewModelClass
import com.example.retrofit_kotlin.view_model.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModelClass
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModelClass::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val recyclerviewAdapter = MyAdapter()
        binding.recyclerview.adapter = recyclerviewAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        binding.postButton.setOnClickListener {

            if (binding.editText.text.toString().isBlank()) {
                Toast.makeText(
                    this,
                    "this field must be not blank",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            viewModel.addPost(
                Post(
                    Integer.parseInt(binding.editText.text.toString()),
                    1,
                    "Youssef Amr",
                    "Android Developer"
                )
            )

//            viewModel.addPost(
//                "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ5b3Vzc2VmYW1yNDRAZ21haWwuY29tIiwiaWF0IjoxNjgxODAzMTk5LCJleHAiOjE3MTMzMzkxOTl9.-XTdK34LQ9Tro8MWjoZk2Qo-A-ZqNSsw0cBTC-O0DrdDPSPoAiDNALkmHY-HjCJW",
//                Integer.parseInt(binding.editText.text.toString()),
//                "Joe",
//                "Android Developer"
//            )
        }

        binding.getButton.setOnClickListener {

            val options: HashMap<String, String> = HashMap()
            options["_sort"] = "id"
            options["_order"] = "desc"

            if (binding.editText.text.toString().isBlank()) {
                Toast.makeText(
                    this,
                    "this field must be not blank",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            viewModel.getPost(Integer.parseInt(binding.editText.text.toString()))
//            viewModel.getPost()
//            viewModel.getPostsByUserId(Integer.parseInt(binding.editText.text.toString()))
            viewModel.getSortedPostsByUserId(
                Integer.parseInt(binding.editText.text.toString()),
                options
            )
        }

        viewModel.getLiveData.observe(this) {
            Log.d("Debug message", it.toString())
        }

        viewModel.postLiveData.observe(this) {
            Log.d("Debug message", it.body().toString())
            Log.d("Debug message", it.headers().toString())
            Toast.makeText(this, it.body().toString(), Toast.LENGTH_LONG).show()
        }

        viewModel.getLiveDataList.observe(this) {
            Log.d("Debug message", it.body().toString())

            if (it.isSuccessful) {
                recyclerviewAdapter.setData(it.body()!!)
            }

        }

    }

}