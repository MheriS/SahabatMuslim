package com.example.data.repository

import com.example.data.model.CityLocation
import com.example.data.model.PrayerSchedule
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.*

object PrayerTimeRepository {

    val supportedCities = listOf(
        CityLocation("Jakarta", "DKI Jakarta", -6.2088, 106.8456, 7.0),
        CityLocation("Surabaya", "Jawa Timur", -7.2575, 112.7521, 7.0),
        CityLocation("Bandung", "Jawa Barat", -6.9175, 107.6191, 7.0),
        CityLocation("Medan", "Sumatera Utara", 3.5952, 98.6722, 7.0),
        CityLocation("Makassar", "Sulawesi Selatan", -5.1477, 119.4327, 8.0),
        CityLocation("Semarang", "Jawa Tengah", -6.9667, 110.4167, 7.0),
        CityLocation("Yogyakarta", "DI Yogyakarta", -7.7956, 110.3695, 7.0),
        CityLocation("Palembang", "Sumatera Selatan", -2.9761, 104.7754, 7.0),
        CityLocation("Banda Aceh", "Aceh", 5.5483, 95.3238, 7.0),
        CityLocation("Denpasar", "Bali", -8.6705, 115.2126, 8.0),
        CityLocation("Banjarmasin", "Kalimantan Selatan", -3.3194, 114.5908, 8.0),
        CityLocation("Samarinda", "Kalimantan Timur", -0.5022, 117.1536, 8.0),
        CityLocation("Pontianak", "Kalimantan Barat", -0.0263, 109.3425, 7.0),
        CityLocation("Manado", "Sulawesi Utara", 1.4748, 124.8428, 8.0),
        CityLocation("Jayapura", "Papua", -2.5916, 140.6690, 9.0),
        CityLocation("Kuala Lumpur", "Malaysia", 3.1390, 101.6869, 8.0),
        CityLocation("Makkah Al-Mukarramah", "Arab Saudi", 21.4225, 39.8262, 3.0),
        CityLocation("Madinah Al-Munawwarah", "Arab Saudi", 24.5247, 39.5692, 3.0)
    )

    var currentCity: CityLocation = supportedCities[0] // Default Jakarta

    /**
     * Accurate Astronomical Prayer Times calculation based on standard methods
     * Fajr angle: 20° (Kemenag RI)
     * Isha angle: 18° (Kemenag RI)
     * Asr: Shafi'i shadow factor (1)
     */
    fun calculatePrayerTimes(
        city: CityLocation = currentCity,
        calendar: Calendar = Calendar.getInstance()
    ): PrayerSchedule {
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val lat = city.latitude
        val lng = city.longitude
        val tz = city.timezoneOffsetHours

        // Solar Declination & Equation of Time approximations
        val b = 2.0 * Math.PI * (dayOfYear - 81) / 365.0
        val eot = 9.87 * sin(2 * b) - 7.53 * cos(b) - 1.5 * sin(b) // minutes
        val declination = 23.45 * sin(2.0 * Math.PI * (284 + dayOfYear) / 365.0) // degrees

        val latRad = Math.toRadians(lat)
        val decRad = Math.toRadians(declination)

        // Solar Noon in local time (hours)
        val solarNoon = 12.0 + (tz * 15.0 - lng) / 15.0 - (eot / 60.0)

        // Helper for hour angle at given altitude alpha
        fun getHourAngle(alphaDeg: Double): Double {
            val alphaRad = Math.toRadians(alphaDeg)
            val cosH = (sin(alphaRad) - sin(latRad) * sin(decRad)) / (cos(latRad) * cos(decRad))
            val clamped = cosH.coerceIn(-1.0, 1.0)
            return Math.toDegrees(acos(clamped)) / 15.0
        }

        // Subuh: alpha = -20 degrees (astronomical twilight)
        val subuhHA = getHourAngle(-20.0)
        val subuhHour = solarNoon - subuhHA

        // Sunrise (Terbit): alpha = -0.833 degrees (atmospheric refraction)
        val sunriseHA = getHourAngle(-0.833)
        val sunriseHour = solarNoon - sunriseHA

        // Dhuhr: solar noon + 2-3 minutes ihtiyat (safety margin)
        val dhuhrHour = solarNoon + (2.0 / 60.0)

        // Asr: shadow length = object + noon shadow -> tan(alpha) = 1 / (1 + tan(|lat - dec|))
        val delta = abs(lat - declination)
        val asrAltitude = Math.toDegrees(atan(1.0 / (1.0 + tan(Math.toRadians(delta)))))
        val asrHA = getHourAngle(asrAltitude)
        val asrHour = solarNoon + asrHA

        // Maghrib: sunset (alpha = -0.833) + 2 minutes ihtiyat
        val sunsetHA = getHourAngle(-0.833)
        val maghribHour = solarNoon + sunsetHA + (2.0 / 60.0)

        // Isha: alpha = -18 degrees
        val ishaHA = getHourAngle(-18.0)
        val ishaHour = solarNoon + ishaHA + (2.0 / 60.0)

        // Imsak: exactly 10 minutes before Subuh
        val imsakHour = subuhHour - (10.0 / 60.0)

        fun formatTime(hourDouble: Double): String {
            val normalized = (hourDouble % 24.0 + 24.0) % 24.0
            val h = normalized.toInt()
            val m = ((normalized - h) * 60).roundToInt().coerceIn(0, 59)
            return String.format(Locale.US, "%02d:%02d", h, m)
        }

        val subuhStr = formatTime(subuhHour)
        val terbitStr = formatTime(sunriseHour)
        val dzuhurStr = formatTime(dhuhrHour)
        val asharStr = formatTime(asrHour)
        val maghribStr = formatTime(maghribHour)
        val isyaStr = formatTime(ishaHour)
        val imsakStr = formatTime(imsakHour)

        // Current time analysis to determine next prayer and remaining time
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        val currentMinutesOfDay = currentHour * 60 + currentMinute

        fun timeStrToMinutes(timeStr: String): Int {
            val parts = timeStr.split(":")
            return parts[0].toInt() * 60 + parts[1].toInt()
        }

        val prayerTimesList = listOf(
            "Subuh" to timeStrToMinutes(subuhStr),
            "Terbit" to timeStrToMinutes(terbitStr),
            "Dzuhur" to timeStrToMinutes(dzuhurStr),
            "Ashar" to timeStrToMinutes(asharStr),
            "Maghrib" to timeStrToMinutes(maghribStr),
            "Isya" to timeStrToMinutes(isyaStr)
        )

        var nextPrayer = "Subuh"
        var minutesRemaining = 0

        val found = prayerTimesList.firstOrNull { it.second > currentMinutesOfDay }
        if (found != null) {
            nextPrayer = found.first
            minutesRemaining = found.second - currentMinutesOfDay
        } else {
            // After Isha, next prayer is Subuh tomorrow
            nextPrayer = "Subuh"
            val subuhMinutes = timeStrToMinutes(subuhStr)
            minutesRemaining = (24 * 60 - currentMinutesOfDay) + subuhMinutes
        }

        val remH = minutesRemaining / 60
        val remM = minutesRemaining % 60
        val remainingFormatted = if (remH > 0) "${remH}j ${remM}m lagi" else "${remM}m lagi"

        val sdf = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id", "ID"))
        val dateFormatted = sdf.format(calendar.time)
        val hijriFormatted = getHijriDate(calendar)

        return PrayerSchedule(
            dateFormatted = dateFormatted,
            hijriDate = hijriFormatted,
            imsak = imsakStr,
            subuh = subuhStr,
            terbit = terbitStr,
            dzuhur = dzuhurStr,
            ashar = asharStr,
            maghrib = maghribStr,
            isya = isyaStr,
            nextPrayerName = nextPrayer,
            timeToNextPrayer = remainingFormatted
        )
    }

    /**
     * Approximate Hijri date converter
     */
    fun getHijriDate(calendar: Calendar = Calendar.getInstance()): String {
        val hijriMonths = listOf(
            "Muharram", "Safar", "Rabi'ul Awwal", "Rabi'ul Akhir",
            "Jumadil Ula", "Jumadil Akhir", "Rajab", "Sya'ban",
            "Ramadhan", "Syawwal", "Dzulqa'dah", "Dzulhijjah"
        )
        // Approximated calculation for Umm al-Qura standard
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Julian Day Number
        var m = month + 1
        var y = year
        if (m < 3) {
            y -= 1
            m += 12
        }
        val a = floor(y / 100.0)
        val b = 2 - a + floor(a / 4.0)
        val jd = floor(365.25 * (y + 4716)) + floor(30.6001 * (m + 1)) + day + b - 1524.5

        val z = jd - 1948439.5
        val hYear = floor((30.0 * z + 10646.0) / 10631.0).toInt()
        val remain = z - floor((10631.0 * hYear - 10646.0) / 30.0)
        val hMonth = min(11, floor((11.0 * remain + 330.0) / 325.0).toInt())
        val hDay = (remain - floor((325.0 * hMonth - 320.0) / 11.0) + 1).toInt().coerceIn(1, 30)

        val monthName = hijriMonths.getOrElse(hMonth) { "Bulan Hijriah" }
        return "$hDay $monthName $hYear H"
    }

    /**
     * Calculate Qibla angle from given location coordinates
     * Ka'bah coordinates: Lat 21.4225 N, Lng 39.8262 E
     */
    fun calculateQiblaAngle(lat: Double, lng: Double): Double {
        val makkahLat = Math.toRadians(21.4225)
        val makkahLng = Math.toRadians(39.8262)
        val userLat = Math.toRadians(lat)
        val userLng = Math.toRadians(lng)

        val deltaLng = makkahLng - userLng
        val y = sin(deltaLng)
        val x = cos(userLat) * tan(makkahLat) - sin(userLat) * cos(deltaLng)

        var qiblaDeg = Math.toDegrees(atan2(y, x))
        qiblaDeg = (qiblaDeg + 360.0) % 360.0
        return (qiblaDeg * 10).roundToInt() / 10.0
    }
}
