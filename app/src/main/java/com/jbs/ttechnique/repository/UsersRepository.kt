package com.jbs.ttechnique.repository

import com.jbs.ttechnique.data.local.UserDao
import com.jbs.ttechnique.data.model.Id
import com.jbs.ttechnique.data.remote.UserRemoteDataSource
import com.jbs.ttechnique.utils.performGetOperation
import javax.inject.Inject


class UsersRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserDao
) {

    fun getUser(id: Id) = localDataSource.getUser(id.nameId, id.value)

    fun getUsers() = performGetOperation(
        databaseQuery = { localDataSource.getAllUsers() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )

    fun getUsers(page: Int, count: Int, seed: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllUsers() },
        networkCall = { remoteDataSource.getUsers(page, count, seed) },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )

    fun reloadUser() = performGetOperation(
        databaseQuery = { localDataSource.getAllUsers() },
        networkCall = {
            localDataSource.nukeTable()
            remoteDataSource.getUsers()
        },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}