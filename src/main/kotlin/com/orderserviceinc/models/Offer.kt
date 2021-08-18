package com.orderserviceinc.models

interface Offer {
    enum class FRUIT {
        APPLE,
        ORANGE
    }
    val fruit: FRUIT
}