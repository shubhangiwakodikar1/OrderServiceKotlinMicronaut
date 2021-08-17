package com.orderserviceinc.controllers

import com.orderserviceinc.models.OrdersResponse
import com.orderserviceinc.services.OrderService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Put
import javax.inject.Inject
import javax.validation.constraints.NotEmpty

@Controller(OrderController.BASE_PATH)
open class OrderController(
    @Inject private val orderService: OrderService
) {
    companion object {
        //define all your constants here so they are available in this class' instance
        const val BASE_PATH = "/orders"
    }

    @Put("/apples/{nApples}/oranges/{nOranges}")
    open fun placeOrder(
        @NotEmpty nApples: Int,
        @NotEmpty nOranges: Int
    ): OrdersResponse {
        val totalCost = orderService.getOrderTotal(nApples, nOranges)
        return OrdersResponse(nApples, nOranges, totalCost)
    }
}