package com.orderserviceinc.models

data class OfferNForPriceOfM(val nNumber: Int, val mNumber: Int, val fruit: Offer.FRUIT): Offer {
    //nNumber is greater than mNumber
}