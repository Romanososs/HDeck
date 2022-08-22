package com.example.hdeck.model.metadata

import com.google.gson.annotations.SerializedName

data class SetGroup(
    val slug: String,
    val year: Int,
    val svg: String,
    @SerializedName("cardSets")
    val cardSetsSlug: List<String>,
    val name: String
)

//{
//    "slug": "hydra",
//    "year": 2022,
//    "svg": "https://images.blz-contentstack.com/v3/assets/bltc965041283bac56c/blt63ce92e33c79ee7f/622902fe04503350d255bca6/YotH_SVG-01-01.svg",
//    "cardSets": [
//    "voyage-to-the-sunken-city"
//    ],
//    "name": "Year of the Hydra",
//    "standard": true,
//    "icon": "icon_cardset_yearofthehydra"
//}