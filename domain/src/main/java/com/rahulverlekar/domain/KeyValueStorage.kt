package com.rahulverlekar.domain

interface KeyValueStorage {

    var token: String?
    var lastOffset: Int
    var count: Int?

    fun deleteAll()
}