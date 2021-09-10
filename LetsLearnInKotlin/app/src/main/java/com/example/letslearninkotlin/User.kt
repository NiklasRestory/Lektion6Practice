package com.example.letslearninkotlin

class User {


    var id:Int?=null
    var username:String?=null
    var password:String?=null


    constructor(){}

    constructor(id:Int = 0, username:String, password:String ) {
        this.id = id
        this.username = username
        this.password = password
    }
}