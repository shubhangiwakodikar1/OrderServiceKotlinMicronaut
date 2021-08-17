package com.orderserviceinc.services

import javax.inject.Singleton

@Singleton
open class OrderService {
    companion object {
        //define all your constants here so they are available in this class' instance
        const val costOfApple = 0.35
        const val costOfOrange = 0.25
    }
    open fun getOrderTotal(nApples: Int, nOranges: Int): Double {
        return (nApples * costOfApple) + (nOranges * costOfOrange)
    }
}