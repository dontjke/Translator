package com.example.translator.data

import com.example.translator.entity.Person

interface BaseRepo {
    fun getData(): Person
}