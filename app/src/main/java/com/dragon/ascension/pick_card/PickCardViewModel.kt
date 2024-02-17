package com.dragon.ascension.pick_card

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dragon.ascension.CardData
import com.dragon.ascension.tool.CardProvider

class PickCardViewModel : ViewModel() {

    val addCardLiveData = MutableLiveData<MutableList<CardData>>()

    fun onCreate() {
        val randomCardList = CardProvider.getRandomCardList()

        addCardLiveData.value = randomCardList


    }

}