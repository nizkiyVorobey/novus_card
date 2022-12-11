package com.senya.novuswidget.use_case

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.senya.novuswidget.*
import java.lang.reflect.Type

class GetCardsFromStorageUseCase() {
    operator fun invoke():List<ShopItem> {
        MainActivity.activityContext().getSharedPreferences("cards", Context.MODE_PRIVATE).apply {
            val type: Type = object : TypeToken<List<ShopItemSP?>?>() {}.type
            val savedCards = getString("items", "")

            val cardList: List<ShopItemSP> = if (savedCards?.isNotEmpty() == true) {
                gson.fromJson(savedCards, type)
            } else {
                emptyList()
            }
            return cardList.map {
                CardMapper.instance.shopItemSPToShopItem(it)
            }

        }
    }
}