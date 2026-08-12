package com.berbersoft.soranfrenchapp.data

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TextToSpeechManager private constructor(context: Context) {
    private var frenchTTS: TextToSpeech? = null
    private var kurdishTTS: TextToSpeech? = null
    
    private val _isFrenchReady = MutableStateFlow(false)
    val isFrenchReady = _isFrenchReady.asStateFlow()
    
    private val _isKurdishReady = MutableStateFlow(false)
    val isKurdishReady = _isKurdishReady.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    
    init {
        // Initialize French TTS
        frenchTTS = TextToSpeech(context) { status ->
            when (status) {
                TextToSpeech.SUCCESS -> {
                    val result = frenchTTS?.setLanguage(Locale.FRENCH)
                    when (result) {
                        TextToSpeech.LANG_MISSING_DATA -> _error.value = "French language data is missing"
                        TextToSpeech.LANG_NOT_SUPPORTED -> _error.value = "French language is not supported"
                        else -> _isFrenchReady.value = true
                    }
                }
                else -> _error.value = "Failed to initialize French TTS"
            }
        }
        
        // Initialize Kurdish TTS
        kurdishTTS = TextToSpeech(context) { status ->
            when (status) {
                TextToSpeech.SUCCESS -> {
                    // For Kurdish (Sorani), we'll try multiple locale codes
                    val locales = listOf(
                        Locale("ckb"), // Central Kurdish
                        Locale("ku"), // Kurdish
                        Locale("ar_IQ") // Iraqi Arabic as fallback
                    )
                    
                    var languageSet = false
                    for (locale in locales) {
                        val result = kurdishTTS?.setLanguage(locale)
                        when (result) {
                            TextToSpeech.LANG_AVAILABLE -> {
                                _isKurdishReady.value = true
                                languageSet = true
                                break
                            }
                            TextToSpeech.LANG_MISSING_DATA -> {
                                _error.value = "Kurdish language data is missing for ${locale.language}"
                                continue
                            }
                            TextToSpeech.LANG_NOT_SUPPORTED -> {
                                _error.value = "Kurdish language is not supported for ${locale.language}"
                                continue
                            }
                        }
                    }
                    
                    if (!languageSet) {
                        _error.value = "Could not set Kurdish language. Using system default."
                        kurdishTTS?.language = Locale.getDefault()
                    }
                }
                else -> _error.value = "Failed to initialize Kurdish TTS"
            }
        }
    }
    
    fun speakFrench(text: String) {
        frenchTTS?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
    
    fun speakKurdish(text: String) {
        kurdishTTS?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
    
    fun shutdown() {
        frenchTTS?.shutdown()
        kurdishTTS?.shutdown()
    }
    
    companion object {
        @Volatile
        private var INSTANCE: TextToSpeechManager? = null
        
        fun getInstance(context: Context): TextToSpeechManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: TextToSpeechManager(context).also { INSTANCE = it }
            }
        }
    }
}
