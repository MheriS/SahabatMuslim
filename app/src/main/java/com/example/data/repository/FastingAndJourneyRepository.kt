package com.example.data.repository

import com.example.data.model.FastingRecord
import com.example.data.model.HabitTrackerState

object FastingAndJourneyRepository {

    var todayHabits = HabitTrackerState(
        dateIso = "2026-11-10",
        subuhDone = true,
        dzuhurDone = true,
        asharDone = false,
        maghribDone = false,
        isyaDone = false,
        tilawahPages = 4,
        zikirDone = true,
        sedekahDone = true,
        notes = "Alhamdulillah istiqomah di awal hari."
    )

    var qadhaTotalDebt: Int = 5
    var qadhaCompleted: Int = 2

    val fastingSchedule = listOf(
        FastingRecord("2026-11-09", "Senin-Kamis", true, "Puasa sunnah Senin"),
        FastingRecord("2026-11-12", "Senin-Kamis", false, "Puasa sunnah Kamis mendatang"),
        FastingRecord("2026-11-23", "Ayyamul Bidh", false, "13 Jumadil Ula 1448 H"),
        FastingRecord("2026-11-24", "Ayyamul Bidh", false, "14 Jumadil Ula 1448 H"),
        FastingRecord("2026-11-25", "Ayyamul Bidh", false, "15 Jumadil Ula 1448 H")
    )

    val sedekahLogs = mutableListOf(
        "Sedekah subuh kotak infaq masjid - Rp 20.000",
        "Berbagi sarapan untuk sesama - Rp 35.000"
    )

    fun togglePrayer(prayer: String) {
        todayHabits = when (prayer.lowercase()) {
            "subuh" -> todayHabits.copy(subuhDone = !todayHabits.subuhDone)
            "dzuhur" -> todayHabits.copy(dzuhurDone = !todayHabits.dzuhurDone)
            "ashar" -> todayHabits.copy(asharDone = !todayHabits.asharDone)
            "maghrib" -> todayHabits.copy(maghribDone = !todayHabits.maghribDone)
            "isya" -> todayHabits.copy(isyaDone = !todayHabits.isyaDone)
            else -> todayHabits
        }
    }

    fun addTilawahPages(delta: Int) {
        val newCount = (todayHabits.tilawahPages + delta).coerceAtLeast(0)
        todayHabits = todayHabits.copy(tilawahPages = newCount)
    }

    fun toggleZikir() {
        todayHabits = todayHabits.copy(zikirDone = !todayHabits.zikirDone)
    }

    fun toggleSedekah() {
        todayHabits = todayHabits.copy(sedekahDone = !todayHabits.sedekahDone)
    }
}
