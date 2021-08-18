package com.orderserviceinc.controllers

import com.orderserviceinc.models.Offer
import com.orderserviceinc.models.OfferBuy1Get1Free
import com.orderserviceinc.models.OfferNForPriceOfM
import com.orderserviceinc.models.OrdersRequest
import com.orderserviceinc.models.OrdersResponse
import com.orderserviceinc.services.OrderService
import io.micronaut.core.version.annotation.Version
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Put
import javax.annotation.Nullable
import javax.inject.Inject
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Version("v1.1.1") //you can version your api
@Controller(OrderController.BASE_PATH)
open class OrderController(
    @Inject private val orderService: OrderService
) {
    companion object {
        //define all your constants here so they are available in this class' instance
        const val BASE_PATH = "/orders"
        const val logIdentifier = "log-identifier"
    }

    @Put("/apples/{nApples}/oranges/{nOranges}")
    open fun placeOrder(
        @NotEmpty nApples: Int,
        @NotEmpty nOranges: Int,
        @NotNull @Body ordersRequest: OrdersRequest, //demonstrating data class used as request/response
        @Nullable @Header(logIdentifier) transactionId: String? //demonstrating log traceability of requests
    ): OrdersResponse {
        val ordersResponse = orderService.getOrderTotal(nApples, nOranges, ordersRequest, transactionId)
        return ordersResponse
    }

    @Put("/apples/{nApples}/oranges/{nOranges}/withOffers")
    open fun placeOrderWithOffers(
        @NotEmpty nApples: Int,
        @NotEmpty nOranges: Int,
        @NotNull @Body ordersRequest: OrdersRequest, //demonstrating data class used as request/response
        @Nullable @Header(logIdentifier) transactionId: String? //demonstrating log traceability of requests
    ): OrdersResponse {
        val offerBuy1Get1Free: Offer = OfferBuy1Get1Free(Offer.FRUIT.APPLE)
        val offerNForPriceOfM: Offer = OfferNForPriceOfM(3, 2, Offer.FRUIT.ORANGE) //3/2 are hardcoded here which I am going to permeate in the test. Code can be improved here to use const val
        ordersRequest.offers = setOf<Offer>(offerBuy1Get1Free, offerNForPriceOfM)

        val ordersResponse = orderService.getOrderTotal(nApples, nOranges, ordersRequest, transactionId)
        return ordersResponse
    }
}