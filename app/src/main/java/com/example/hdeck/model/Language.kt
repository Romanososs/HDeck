package com.example.hdeck.model

class Language(
    val code: String
){
    val apiCode = when(code) {
        "en" -> "en_US"
        "ru" -> "ru_RU"
        else -> "en_US"
    }
}
//de_DE: "Erzschurke Rafaam",
//en_US: "Arch-Villain Rafaam",
//es_ES: "Archivillano Rafaam",
//es_MX: "Archivillano Rafaam",
//fr_FR: "Prince du mal Rafaam",
//it_IT: "Arcicattivo Rafaam",
//ja_JP: "大悪党ラファーム",
//ko_KR: "대악당 라팜",
//pl_PL: "Arcyzłoczyńca Rafaam",
//pt_BR: "Arquivilão Rafaam",
//ru_RU: "Суперзлодей Рафаам",
//th_TH: "สุดยอดวายร้ายราฟาม",
//zh_TW: "極盜天王拉法姆"