package com.example.hdeck.model

open class Language(
    val code: String,
){
    val apiCode = when(code) {
        "de" -> "de_DE"
        "en" -> "en_US"
        "es_ES" -> "es_ES"
        "es_MX" -> "es_MX"
        "fr" -> "fr_FR"
        "it" -> "it_IT"
        "ja" -> "ja_JP"
        "ko" -> "ko_KR"
        "pl" -> "pl_PL"
        "pt" -> "pt_BR"
        "ru" -> "ru_RU"
        "th" -> "th_TH"
        "zh" -> "zh_TW"
        else -> "en_US"
    }
    override fun equals(other: Any?): Boolean {
        val lang = other as Language
        return code == lang.code && apiCode == lang.apiCode
    }
}
class SupportedLanguages{
    object GERMAN : Language("de")
    object ENGLISH : Language("en")
    object SPANISH : Language("es_ES")
    object MEXICAN : Language("es_MX")
    object FRENCH : Language("fr")
    object ITALIAN : Language("it")
    object JAPANESE : Language("ja")
    object KOREAN : Language("ko")
    object POLISH : Language("pl")
    object PORTUGUESE : Language("pt")
    object RUSSIAN : Language("ru")
    object THAI : Language("th")
    object CHINESE : Language("zh")
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