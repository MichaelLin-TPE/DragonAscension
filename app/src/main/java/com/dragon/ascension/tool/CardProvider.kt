package com.dragon.ascension.tool

import com.dragon.ascension.CardData

object CardProvider {

    fun getRandomCardList(): MutableList<CardData> {

        val dataList = mutableListOf<CardData>()
        dataList.add(CardData(1,2,2,2,2))
        dataList.add(CardData(1,2,2,2,2))
        dataList.add(CardData(1,2,2,2,2))
        dataList.add(CardData(1,2,2,2,2))
        dataList.add(CardData(1,2,2,2,2))
        dataList.add(CardData(1,2,2,2,2))

        return dataList
    }

}