package com.example.fuel.models

data class pumpcategory(
    var id: String = "",
    var title: String = "",
    var category: MutableMap<String, pump> = mutableMapOf()
)
