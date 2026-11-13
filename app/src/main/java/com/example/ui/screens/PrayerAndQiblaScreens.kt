package com.example.ui.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CityLocation
import com.example.data.repository.PrayerTimeRepository
import com.example.ui.theme.*
import java.util.Calendar
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrayerTimesScreen(
    onOpenQibla: () -> Unit,
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    var selectedCity by remember { mutableStateOf(PrayerTimeRepository.currentCity) }
    var showCityPicker by remember { mutableStateOf(false) }

    val schedule = remember(selectedCity) {
        PrayerTimeRepository.calculatePrayerTimes(selectedCity)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jadwal Salat & Imsakiyah", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("prayer_times_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                actions = {
                    IconButton(onClick = onOpenQibla, modifier = Modifier.testTag("prayer_screen_qibla_btn")) {
                        Icon(Icons.Default.Explore, contentDescription = "Kompas Kiblat", tint = EmeraldPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // City Selector & Date Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, contentDescription = null, tint = EmeraldPrimary)
                                Spacer(modifier = Modifier.width(6.dp))
                                Column {
                                    Text(
                                        text = "${selectedCity.name}, ${selectedCity.province}",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "${selectedCity.latitude}°, ${selectedCity.longitude}°",
                                        fontSize = 11.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            FilledTonalButton(
                                onClick = { showCityPicker = true },
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text("Ganti Kota", fontSize = 12.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = schedule.dateFormatted,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = EmeraldContainer
                            ) {
                                Text(
                                    text = schedule.hijriDate,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldPrimary,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Next Prayer Spotlight
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldPrimary)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "WAKTU SALAT BERIKUTNYA",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldLight,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = schedule.nextPrayerName,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                            Text(
                                text = "Menuju azan dalam ${schedule.timeToNextPrayer}",
                                fontSize = 13.sp,
                                color = EmeraldContainer
                            )
                        }

                        Icon(
                            Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = GoldLight,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }

            // All Prayer Times Row
            item {
                Text(
                    text = "Jadwal Hari Ini",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            item {
                val times = listOf(
                    "Imsak" to schedule.imsak,
                    "Subuh" to schedule.subuh,
                    "Terbit" to schedule.terbit,
                    "Dzuhur" to schedule.dzuhur,
                    "Ashar" to schedule.ashar,
                    "Maghrib" to schedule.maghrib,
                    "Isya" to schedule.isya
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    times.forEach { (name, time) ->
                        val isNext = schedule.nextPrayerName.equals(name, ignoreCase = true)
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isNext) EmeraldContainer else MaterialTheme.colorScheme.surface
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        when (name) {
                                            "Imsak" -> Icons.Default.Nightlight
                                            "Subuh" -> Icons.Default.BrightnessLow
                                            "Terbit" -> Icons.Default.WbSunny
                                            "Dzuhur" -> Icons.Default.WbSunny
                                            "Ashar" -> Icons.Default.WbTwilight
                                            "Maghrib" -> Icons.Default.NightsStay
                                            else -> Icons.Default.DarkMode
                                        },
                                        contentDescription = null,
                                        tint = if (isNext) EmeraldPrimary else MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(14.dp))
                                    Text(
                                        text = name,
                                        fontSize = 16.sp,
                                        fontWeight = if (isNext) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isNext) EmeraldDark else MaterialTheme.colorScheme.onSurface
                                    )
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = time,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isNext) EmeraldPrimary else MaterialTheme.colorScheme.onSurface
                                    )
                                    if (isNext) {
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = EmeraldPrimary
                                        ) {
                                            Text(
                                                text = "BERIKUTNYA",
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.White,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Calculation Info Note
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SageLight.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Info, contentDescription = null, tint = EmeraldPrimary)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Dihitung menggunakan standar Kementerian Agama RI (Subuh 20°, Isya 18°, Ihtiyat 2 menit).",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }

    if (showCityPicker) {
        AlertDialog(
            onDismissRequest = { showCityPicker = false },
            title = { Text("Pilih Kota & Lokasi", fontWeight = FontWeight.Bold) },
            text = {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 350.dp)
                ) {
                    items(PrayerTimeRepository.supportedCities) { city ->
                        val isSelected = city.name == selectedCity.name
                        ListItem(
                            headlineContent = { Text(city.name, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                            supportingContent = { Text(city.province, fontSize = 12.sp) },
                            leadingContent = {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = {
                                        selectedCity = city
                                        PrayerTimeRepository.currentCity = city
                                        showCityPicker = false
                                    }
                                )
                            },
                            modifier = Modifier.clickable {
                                selectedCity = city
                                PrayerTimeRepository.currentCity = city
                                showCityPicker = false
                            }
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showCityPicker = false }) {
                    Text("Tutup")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QiblaScreen(
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    val context = LocalContext.current
    val haptic = LocalHapticFeedback.current
    val city = PrayerTimeRepository.currentCity
    val qiblaBearing = remember(city) {
        PrayerTimeRepository.calculateQiblaAngle(city.latitude, city.longitude)
    }

    var azimuth by remember { mutableFloatStateOf(0f) }
    var hasSensor by remember { mutableStateOf(false) }

    // Compass Sensor Listener
    DisposableEffect(Unit) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
        val sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event?.sensor?.type == Sensor.TYPE_ROTATION_VECTOR) {
                    val rotationMatrix = FloatArray(9)
                    SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
                    val orientation = FloatArray(3)
                    SensorManager.getOrientation(rotationMatrix, orientation)
                    var deg = Math.toDegrees(orientation[0].toDouble()).toFloat()
                    if (deg < 0) deg += 360f
                    azimuth = deg
                    hasSensor = true
                }
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        if (sensor != null) {
            sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
        }

        onDispose {
            sensorManager?.unregisterListener(listener)
        }
    }

    val diffToQibla = abs(azimuth - qiblaBearing.toFloat())
    val isAligned = diffToQibla <= 4f || diffToQibla >= 356f

    LaunchedEffect(isAligned) {
        if (isAligned) {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Arah Kiblat", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("qibla_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Location Header
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = EmeraldContainer
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "${city.name} (${city.province})",
                            fontWeight = FontWeight.Bold,
                            color = EmeraldPrimary,
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Arah Ka'bah: ${qiblaBearing}° Barat Laut",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "Jarak ke Makkah: ± 7.920 km",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Big Compass Dial Canvas
            Box(
                modifier = Modifier
                    .size(280.dp)
                    .testTag("qibla_compass_dial"),
                contentAlignment = Alignment.Center
            ) {
                val animatedRotation by animateFloatAsState(targetValue = -azimuth, label = "compass_rot")

                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .rotate(animatedRotation)
                ) {
                    val center = Offset(size.width / 2, size.height / 2)
                    val radius = size.width / 2 - 20.dp.toPx()

                    // Outer dial circle
                    drawCircle(
                        color = if (isAligned) EmeraldPrimary else Color.LightGray.copy(alpha = 0.5f),
                        radius = radius,
                        center = center,
                        style = Stroke(width = 4.dp.toPx())
                    )

                    // Cardinal tick marks
                    for (i in 0 until 360 step 30) {
                        val angleRad = Math.toRadians(i.toDouble() - 90)
                        val start = Offset(
                            (center.x + (radius - 12.dp.toPx()) * cos(angleRad)).toFloat(),
                            (center.y + (radius - 12.dp.toPx()) * sin(angleRad)).toFloat()
                        )
                        val end = Offset(
                            (center.x + radius * cos(angleRad)).toFloat(),
                            (center.y + radius * sin(angleRad)).toFloat()
                        )
                        drawLine(
                            color = if (i == 0) Color.Red else Color.Gray,
                            start = start,
                            end = end,
                            strokeWidth = if (i % 90 == 0) 3.dp.toPx() else 1.5.dp.toPx()
                        )
                    }

                    // Qibla Indicator Line on Dial
                    val qiblaRad = Math.toRadians(qiblaBearing - 90)
                    val qiblaEnd = Offset(
                        (center.x + (radius - 20.dp.toPx()) * cos(qiblaRad)).toFloat(),
                        (center.y + (radius - 20.dp.toPx()) * sin(qiblaRad)).toFloat()
                    )
                    drawLine(
                        color = GoldAccent,
                        start = center,
                        end = qiblaEnd,
                        strokeWidth = 5.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                }

                // Center Ka'bah Icon
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(if (isAligned) EmeraldPrimary else CharcoalDark, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Explore,
                            contentDescription = "Ka'bah",
                            tint = if (isAligned) Color.White else GoldAccent,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${azimuth.toInt()}°",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = if (isAligned) EmeraldPrimary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Direction Match Status Badge
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (isAligned) EmeraldContainer else SageLight.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            if (isAligned) Icons.Default.CheckCircle else Icons.Default.CompassCalibration,
                            contentDescription = null,
                            tint = if (isAligned) EmeraldPrimary else MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isAligned) "Tepat Mengarah ke Ka'bah!" else "Arahkan jarum emas ke atas",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = if (isAligned) EmeraldDark else MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (hasSensor) "Gerakkan perangkat membentuk angka 8 jika kompas membutuhkan kalibrasi."
                        else "Sensor kompas tidak aktif pada perangkat ini. Gunakan sudut ${qiblaBearing}° Barat Laut secara manual.",
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
