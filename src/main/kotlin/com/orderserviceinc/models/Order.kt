package com.orderserviceinc.models

data class Order(val orderId: String, val nApples: Int, val nOranges: Int, val orderTotal: Double) {
}