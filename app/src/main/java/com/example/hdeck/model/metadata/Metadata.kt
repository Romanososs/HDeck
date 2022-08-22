package com.example.hdeck.model.metadata

data class Metadata(
    val sets: List<CardSet>,
    val setGroups: List<SetGroup>,
    //gameModes
    val types: List<CardType>,
    val rarities: List<CardRarity>,
    val classes: List<HeroClass>,
    val minionTypes: List<MinionType>,
    val spellSchools: List<SpellSchool>,
)
