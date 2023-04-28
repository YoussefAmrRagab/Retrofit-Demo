package com.example.retrofit_kotlin.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_kotlin.model.Post
import com.example.retrofit_kotlin.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelClass(private val repository: Repository) : ViewModel() {

    private val getViewModel: MutableLiveData<Response<Post>> = MutableLiveData()
    private val postViewModel: MutableLiveData<Response<Post>> = MutableLiveData()
    private val getViewModelList: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    val getLiveData = getViewModel
    val postLiveData = postViewModel
    val getLiveDataList = getViewModelList

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            getViewModel.value = response
        }
    }

    fun getPost(number: Int) {
        viewModelScope.launch {
            val response = repository.getPost(number = number)
            getViewModel.value = response
        }
    }

    fun getPostsByUserId(id: Int) {
        viewModelScope.launch {
            val response = repository.getPostsByUserId(id)
            getViewModelList.value = response
        }
    }

    fun getSortedPostsByUserId(id: Int, option: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getPostsByUserId(id, option)
            getViewModelList.value = response
        }
    }

    fun addPost(post: Post) {
        viewModelScope.launch {
            val response = repository.addPost(post)
            postViewModel.value = response
        }
    }

    fun addPost(auth: String, id: Int, title: String, body: String) {
        viewModelScope.launch {
            val response = repository.addPost(auth, id, title, body)
            postViewModel.value = response
        }
    }
}