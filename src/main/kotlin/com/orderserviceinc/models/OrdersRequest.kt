package com.orderserviceinc.models

//default values for both parameters
data class OrdersRequest(val message: String = "I love apples", var offers: Set<Offer> = setOf<Offer>()) {
}