package com.orderserviceinc.models

open abstract class OfferNForPriceOfM(nNumber: Int, mNumber: Int): Offer {
    //nNumber is greater than mNumber
    val nNumber: Int = nNumber
    val mNumber: Int = mNumber
}