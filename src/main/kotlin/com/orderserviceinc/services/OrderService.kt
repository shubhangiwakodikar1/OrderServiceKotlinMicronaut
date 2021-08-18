package com.orderserviceinc.services

import com.orderserviceinc.models.Offer
import com.orderserviceinc.models.OfferBuy1Get1Free
import com.orderserviceinc.models.OfferNForPriceOfM
import com.orderserviceinc.models.OrdersRequest
import com.orderserviceinc.models.OrdersResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
open class OrderService {

    companion object {
        //define all your constants here so they are available in this class' instance
        const val costOfApple = 0.60
        const val costOfOrange = 0.25
        val LOG = LoggerFactory.getLogger(OrderService.javaClass)
    }

    open fun getOrderTotal(nApples: Int, nOranges: Int, ordersRequest: OrdersRequest, transactionId: String?): OrdersResponse {
        LOG.info("transactionId=${transactionId}")
        val totalCost = (nApples * costOfApple) + (nOranges * costOfOrange)
        return OrdersResponse(nApples, nOranges, totalCost, ordersRequest.message)
    }

    open fun getOrderTotalWithOffers(nApples: Int, nOranges: Int, ordersRequest: OrdersRequest, transactionId: String?, vararg offers: Offer): OrdersResponse {
        LOG.info("transactionId=${transactionId}")
        var totalCost: Double = (nApples * costOfApple) + (nOranges * costOfOrange) //whether offer or not, cost is same
        var message: String = ordersRequest.message
        for (offer in offers) {
            if (offer is OfferBuy1Get1Free) {
                if (offer.fruit == Offer.FRUIT.APPLE) {
                    message += ". You got ${nApples * 2} apples with our Buy1Get1FreeOffer :)"
                } else if (offer.fruit == Offer.FRUIT.ORANGE) {
                    message += ". You got ${nOranges * 2} orranges with our Buy1Get1FreeOffer :)"
                }
            }
            if (offer is OfferNForPriceOfM) {
                if (offer.fruit == Offer.FRUIT.APPLE) {
                    message += ". You got ${offer.nNumber} apples with our ${offer.nNumber}ForPriceOf${offer.mNumber}Offer :)"
                } else if (offer.fruit == Offer.FRUIT.ORANGE) {
                    message += ". You got ${offer.nNumber} oranges with our ${offer.nNumber}ForPriceOf${offer.mNumber}Offer :)"
                }
            }
        }
        return OrdersResponse(nApples, nOranges, totalCost, ordersRequest.message)
    }
}