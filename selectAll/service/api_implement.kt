package com.api.selectAll.service

import com.api.selectAll.model.API_uid
import com.api.selectAll.Config.dbcon
import com.api.selectAll.model.Message
import com.api.selectAll.model.Message1
import org.springframework.stereotype.Service

@Service
class api_implement:api_service{


    override fun getAllData(): MutableList<API_uid> {
        val data = mutableListOf<API_uid>()
        val con = dbcon().connect()
        val query =con.prepareStatement("SELECT * FROM login")
        val result =query.executeQuery()

        while(result.next()){
            val uid:Int = result.getInt("uid")
            var username = result.getString("username")
            var password = result.getString("password")
            data.add(API_uid(uid,"$username","$password"))
        }
        return data
    }

    override fun getoneUser(uid:Int): List<API_uid> {
        var data= listOf<API_uid>()
        val con = dbcon().connect()
        val query = con.prepareStatement("SELECT * from login WHERE uid = $uid")
        val result = query.executeQuery()

        while(result.next()){
            var uid = result.getInt("uid")
            var username = result.getString("username")
            var password = result.getString("password")
            data = listOf(API_uid(uid, "$username", "$password"))
        }
        if(data.isEmpty()) return listOf(API_uid(uid,"",""))

        return data
    }

    override fun createUser(username: String, password: String): Message {
        val con = dbcon().connect()
        val query = con?.prepareStatement("INSERT INTO login (username, password) VALUES ('$username', '$password')")
        if (query != null) {
            query.executeUpdate()
            return Message("Successfully Creation")
        }
        return Message("Not Successful")
    }

    override  fun deleteUser(uid:Int):Message{
        val con = dbcon().connect()
        val query = con?.prepareStatement("DELETE FROM login WHERE uid= $uid")

        if (query != null){
            query.executeQuery()
            return Message("Account Deleted")
        }
        return Message("Account Deletion Failed")
    }

    override fun updateUser(uid: Int, username: String, password: String): Message1 {

        val con = dbcon().connect()
//        var Iid:Int = uid.toInt()
        val query = con?.prepareStatement("Update login SET username = '" + username + "' , password = '"+ password +"' WHERE uid = '" + uid + "'")
        val result=  query.executeUpdate()
        if(result!= null){
            return Message1("Account Successfully Updated")
        }
        return Message1("Account Update Failed")
    }

}