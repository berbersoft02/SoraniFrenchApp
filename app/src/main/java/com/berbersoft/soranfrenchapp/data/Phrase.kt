package com.berbersoft.soranfrenchapp.data

data class Phrase(
    val id: String,
    val french: String,
    val english: String,
    val kurdish: String,
    val pronunciation: String,
    val category: PhraseCategory
)

enum class PhraseCategory {
    GREETINGS,
    BASIC_EXPRESSIONS,
    DINING,
    SHOPPING,
    TRAVEL,
    EMERGENCY,
    SOCIAL,
    BUSINESS
} 