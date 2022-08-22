package com.example.hdeck.model

data class CardApi(
    val id: Int,
    val slug: String,
    val classId: Int,
    val cardTypeId: Int,
    val cardSetId: Int,
    val rarityId: Int,
    val artistName: String,
    val image: String,
    val name: String,
    val text: String,
    val flavorText: String,
    val childIds: List<Int>,
    val copyOfCardId: Int
)

//set
//class
//rarity
//type
//artistName
//childIds
//{
//    "id": 69875,
//    "collectible": 1,
//    "slug": "69875-ancient-of-war",
//    "classId": 2,
//    "multiClassIds": [],
//    "cardTypeId": 4,
//    "cardSetId": 1646,
//    "rarityId": 4,
//    "artistName": "Sean O’Daniels",
//    "health": 5,
//    "attack": 5,
//    "manaCost": 7,
//    "name": "Ancient of War",
//    "text": "<b>Choose One -</b> +5 Attack; or +5 Health and <b>Taunt</b>.",
//    "image": "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/fff7fa1d37cedf7a28b2542e637a522032ba89b80a8668ca76f2d231b4fe16d0.png",
//    "imageGold": "",
//    "flavorText": "Young Night Elves love to play \"Who can get the Ancient of War to Uproot?\" You lose if you get crushed to death.",
//    "cropImage": "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/b7721a0264ddc3d862432d48c20683332fe30acb2a91bb9ad6bf0e03c0052883.png",
//    "childIds": [
//    71501,
//    71502
//    ],
//    "keywordIds": [
//    1
//    ],
//    "copyOfCardId": 1035
//}