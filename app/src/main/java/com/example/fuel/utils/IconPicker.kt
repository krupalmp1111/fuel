package com.example.fuel.utils

import com.example.fuel.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.ic_icon1,
        R.drawable.ic_icon2,
        R.drawable.ic_icon3,
        R.drawable.ic_icon4,
        R.drawable.ic_icon5,
        R.drawable.ic_icon6,
        R.drawable.ic_icon7,
        R.drawable.ic_icon8)
    var currentIcon = 0

    fun getIcon(): Int{
        currentIcon = (currentIcon + 1) % icons.size
        return icons[currentIcon]

    }
}