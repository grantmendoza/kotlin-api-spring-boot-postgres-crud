package com.api.selectAll.Config

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.sql.Connection
import java.sql.DriverManager


@Configuration
class dbcon {

    fun connect(): Connection {
        var message:String
// set the jdbcUrl
        Class.forName("org.postgresql.Driver")
        val url = "jdbc:postgresql://localhost:5432/GrantDB"
// set the username
        val username = "postgres"
// set the password
        val password = "password"

        val connection = DriverManager.getConnection(url, username, password)

        if(connection != null){
            message = "connection success"
        }else{
            message = "connection failed"
        }

        return connection
    }

}