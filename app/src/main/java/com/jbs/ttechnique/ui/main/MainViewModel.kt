package com.jbs.ttechnique.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbs.ttechnique.data.model.User
import com.jbs.ttechnique.data.remote.PaginationHandler
import com.jbs.ttechnique.repository.UsersRepository
import com.jbs.ttechnique.utils.RESULT_COUNT
import com.jbs.ttechnique.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val repository: UsersRepository) :
    ViewModel() {

    var paginationHandler = PaginationHandler()

    var users = repository.getUsers()

    fun loadUsers(paginationHandler: PaginationHandler) {
        users = repository.getUsers(paginationHandler.continuationPage, RESULT_COUNT, "abc")
    }

    fun loadMoreUser() {
        if (paginationHandler.continuationPage >= 10)
            return
        paginationHandler.incContinuationPage()
        loadUsers(paginationHandler)
    }

    fun reloadData() {
        paginationHandler = PaginationHandler()
        users = repository.reloadUser()
    }
}