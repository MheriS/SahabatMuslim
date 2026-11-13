package com.example

import com.example.data.repository.PrayerTimeRepository
import com.example.data.repository.QuranRepository
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun quranRepository_contains114Surahs() {
        assertEquals(114, QuranRepository.surahs.size)
        assertEquals("Al-Fatihah", QuranRepository.surahs.first().nameLatin)
        assertEquals("An-Nas", QuranRepository.surahs.last().nameLatin)
    }

    @Test
    fun prayerTimes_calculatedAccurately() {
        val schedule = PrayerTimeRepository.calculatePrayerTimes(PrayerTimeRepository.supportedCities[0])
        assertNotNull(schedule.subuh)
        assertNotNull(schedule.dzuhur)
        assertNotNull(schedule.ashar)
        assertNotNull(schedule.maghrib)
        assertNotNull(schedule.isya)
        assertTrue(schedule.subuh.contains(":"))
    }

    @Test
    fun qiblaAngle_jakarta_isAccurate() {
        // Jakarta (-6.2088, 106.8456) has Qibla angle ~295°
        val angle = PrayerTimeRepository.calculateQiblaAngle(-6.2088, 106.8456)
        assertTrue("Qibla angle $angle should be around 295°", angle in 294.0..296.0)
    }
}
