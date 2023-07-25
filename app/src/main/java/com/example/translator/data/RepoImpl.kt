package com.example.translator.data

import com.example.translator.entity.Person

class RepoImpl: BaseRepo {
    override fun getData(): Person = Person("Vasya")
}