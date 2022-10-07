package com.api.selectAll.controller

import com.api.selectAll.model.API_model
import com.api.selectAll.model.API_uid
import org.springframework.web.bind.annotation.*
import com.api.selectAll.service.api_service

@RestController
class api_controller (var userdata:api_service){


    @GetMapping("/user")
    fun getUsers(): MutableList<API_uid>? {
        return userdata.getAllData()
    }

    @GetMapping("/user/{uid}")
    fun getoneUser(@PathVariable uid:Int):List<API_uid>?{
        return userdata.getoneUser(uid)
    }

//Path Variable
//    @PostMapping("/create/{username}&{password}")
//    fun createUser(@PathVariable username: String, @PathVariable password: String) {
//        userdata.createUser(username, password)
//    }

    @PostMapping("/create")
    fun createUser(@RequestBody UserDetails: API_model){
        val(username, password) = UserDetails
        userdata.createUser(username, password)
    }
    @PostMapping("/delete/{uid}")
    fun deleteUser(@PathVariable uid:Int){
        userdata.deleteUser(uid)
    }

    @PutMapping("/update/{uid}")
    fun updateUser (@PathVariable uid:Int, @RequestBody UserDetails: API_model){
        val (username, password) = UserDetails
        userdata.updateUser(uid, username, password)
    }
}