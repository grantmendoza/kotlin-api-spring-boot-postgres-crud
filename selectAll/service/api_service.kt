package com.api.selectAll.service

import com.api.selectAll.model.API_uid
import com.api.selectAll.model.Message
import com.api.selectAll.model.Message1

interface api_service {

    fun getAllData(): MutableList<API_uid>

    fun getoneUser(uid:Int):List<API_uid>

    fun createUser(username: String, password: String) : Message

    fun deleteUser(uid:Int): Message

    fun updateUser(uid:Int, username:String, password:String): Message1
}