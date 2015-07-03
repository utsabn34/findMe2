package com.lftechnology.findMe

import com.lftechnology.findMe.auth.Auth


class User extends Auth{
    String name
    String email
    static constraints = {
        email:true
    }
}
