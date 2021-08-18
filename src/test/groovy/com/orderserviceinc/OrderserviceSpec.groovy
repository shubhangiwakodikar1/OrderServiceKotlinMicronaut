package com.orderserviceinc

import com.orderserviceinc.models.Offer
import com.orderserviceinc.models.OfferBuy1Get1Free
import com.orderserviceinc.models.OrdersRequest
import com.orderserviceinc.models.OrdersResponse
import com.orderserviceinc.services.OrderService
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import spock.lang.Unroll

import javax.inject.Inject

@MicronautTest
class OrderserviceSpec extends Specification {

    @Inject
    EmbeddedApplication<?> application
    OrderService orderService
    def transactionId
    def message
    OrdersRequest ordersRequest

    def setup() {
        orderService = new OrderService()
        transactionId = UUID.randomUUID().toString()
        message = "I love apples but not oranges"
        ordersRequest = new OrdersRequest(message, [].toSet())
    }

    @Unroll
    void 'test getOrderTotal works with #nApples and #nOranges'() {
        expect:
        application.running

        when:
        OrdersResponse ordersResponse = orderService.getOrderTotal(nApples, nOranges, ordersRequest, transactionId)

        then:
        ordersResponse.message == message
        ordersResponse.NApples == nApples
        ordersResponse.NOranges == nOranges
        ordersResponse.orderTotal == ((OrderService.costOfApple * nApples) + (OrderService.costOfOrange * nOranges))

        where:
        nApples     |  nOranges
        0           |  1
        1           | -1
    }

    @Unroll
    void 'test getOrderTotalWithOffers works with #nApples and #nOranges with offers'() {
        expect:
        application.running

        when:
        OrdersResponse ordersResponse = orderService.getOrderTotalWithOffers(nApples, nOranges, ordersRequest, transactionId)

        then:
        ordersResponse.message.contains("You got ${nApples * 2} apples with our Buy1Get1FreeOffer :)")
        ordersResponse.message.contains("You got 3 oranges with our 3ForPriceOf2Offer :)")
        ordersResponse.NApples == nApples
        ordersResponse.NOranges == nOranges
        ordersResponse.orderTotal == ((OrderService.costOfApple * nApples) + (OrderService.costOfOrange * nOranges))

        where:
        nApples     |  nOranges
        0           |  1
        1           | -1
    }

}
