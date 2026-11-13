package com.example.data.model

data class Surah(
    val number: Int,
    val nameArabic: String,
    val nameLatin: String,
    val translationId: String,
    val revelationType: String, // Makkiyyah or Madaniyyah
    val totalVerses: Int,
    val juzNumber: Int = 1
)

data class Ayah(
    val surahNumber: Int,
    val verseNumber: Int,
    val arabicText: String,
    val transliteration: String,
    val indonesianText: String,
    val tafsirBrief: String = "",
    val audioUrl: String = ""
)

data class Bookmark(
    val surahNumber: Int,
    val surahName: String,
    val verseNumber: Int,
    val timestamp: Long = System.currentTimeMillis()
)

data class CityLocation(
    val name: String,
    val province: String,
    val latitude: Double,
    val longitude: Double,
    val timezoneOffsetHours: Double = 7.0 // WIB = +7, WITA = +8, WIT = +9
)

data class PrayerSchedule(
    val dateFormatted: String,
    val hijriDate: String,
    val imsak: String,
    val subuh: String,
    val terbit: String,
    val dzuhur: String,
    val ashar: String,
    val maghrib: String,
    val isya: String,
    val nextPrayerName: String,
    val timeToNextPrayer: String
)

data class DoaItem(
    val id: String,
    val title: String,
    val category: String,
    val arabicText: String,
    val transliteration: String,
    val translation: String,
    val reference: String,
    val repetitionCount: Int = 1,
    val benefits: String = ""
)

data class PrayerGuide(
    val id: String,
    val name: String,
    val category: String, // Wajib or Sunnah
    val rakaatCount: Int,
    val executionTime: String,
    val niatArabic: String,
    val niatLatin: String,
    val niatTranslation: String,
    val virtues: String,
    val steps: List<PrayerStep>,
    val commonMistakes: List<String> = emptyList()
)

data class PrayerStep(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val arabicRecitation: String = "",
    val transliteration: String = "",
    val translation: String = ""
)

data class FastingRecord(
    val dateIso: String,
    val fastingType: String, // Senin-Kamis, Ayyamul Bidh, Ramadhan, Qadha, Sunnah Lain
    val completed: Boolean,
    val notes: String = ""
)

data class HabitTrackerState(
    val dateIso: String,
    val subuhDone: Boolean = false,
    val dzuhurDone: Boolean = false,
    val asharDone: Boolean = false,
    val maghribDone: Boolean = false,
    val isyaDone: Boolean = false,
    val tilawahPages: Int = 0,
    val zikirDone: Boolean = false,
    val sedekahDone: Boolean = false,
    val notes: String = ""
)

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class AcademyLesson(
    val id: String,
    val title: String,
    val category: String, // Tajwid, Fikih, Sirah, Aqidah
    val summary: String,
    val content: String,
    val exampleArabic: String = "",
    val exampleLatin: String = ""
)
