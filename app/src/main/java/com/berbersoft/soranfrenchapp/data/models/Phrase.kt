data class Phrase(
    val id: String,
    val french: String,
    val english: String,
    val kurdish: String,
    val pronunciation: String,  // Prononciation du français
    val kurdishPronunciation: String? = null,  // Prononciation du kurde (optionnelle)
    val category: PhraseCategory
) 