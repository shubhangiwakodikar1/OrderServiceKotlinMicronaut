package com.orderserviceinc.services

import com.orderserviceinc.models.OrdersRequest
import com.orderserviceinc.models.OrdersResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
open class OrderService {
    companion object {
        //define all your constants here so they are available in this class' instance
        const val costOfApple = 0.35
        const val costOfOrange = 0.25
        val LOG = LoggerFactory.getLogger(OrderService.javaClass)
    }
    open fun getOrderTotal(nApples: Int, nOranges: Int, ordersRequest: OrdersRequest, transactionId: String?): OrdersResponse {
        LOG.info("transactionId=${transactionId}")
        val totalCost = (nApples * costOfApple) + (nOranges * costOfOrange)
        return OrdersResponse(nApples, nOranges, totalCost, ordersRequest.message)
    }
}