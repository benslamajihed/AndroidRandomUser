package com.jbs.ttechnique.ui.userdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.jbs.ttechnique.data.model.Id
import com.jbs.ttechnique.data.model.User
import com.jbs.ttechnique.repository.UsersRepository
import com.jbs.ttechnique.utils.Resource

class UserDetailsViewModel @ViewModelInject constructor(private val repository: UsersRepository) : ViewModel() {

    private val _id = MutableLiveData<Id>()

    private val _user = _id.switchMap { id ->
        repository.getUser(id)
    }
    val user: LiveData<User> = _user


    fun start(namedId: String, valueId: String) {
        val id = Id()
        id.nameId = namedId
        id.value = valueId
        _id.value = id
    }

}