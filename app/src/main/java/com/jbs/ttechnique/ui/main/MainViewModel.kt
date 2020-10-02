package com.jbs.ttechnique.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbs.ttechnique.data.model.User
import com.jbs.ttechnique.repository.UsersRepository
import com.jbs.ttechnique.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val repository: UsersRepository) :
    ViewModel() {

    var users = repository.getUsers()

    fun reloadData() {
        users = repository.reloadUser()
    }
}