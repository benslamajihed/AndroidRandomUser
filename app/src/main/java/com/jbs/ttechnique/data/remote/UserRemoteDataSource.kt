package com.jbs.ttechnique.data.remote

import com.jbs.ttechnique.utils.RESULT_COUNT
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userService: UserService) :
    BaseDataSource() {

    suspend fun getUsers() = getResult {
        userService.getAllUsers(1, RESULT_COUNT, "abc")
    }

}