package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.PrayerTimeRepository
import com.example.data.repository.QuranRepository
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    onNavigateToQuran: () -> Unit,
    onNavigateToSurah: (Int) -> Unit,
    onNavigateToDoa: () -> Unit,
    onNavigateToTasbih: () -> Unit,
    onNavigateToPrayerGuide: () -> Unit,
    onNavigateToPrayerTimes: () -> Unit,
    onNavigateToQibla: () -> Unit,
    onNavigateToFasting: () -> Unit,
    onNavigateToJourney: () -> Unit,
    onNavigateToAcademy: () -> Unit,
    onNavigateToZakat: () -> Unit,
    onNavigateToSahabatAi: () -> Unit
) {
    val context = LocalContext.current
    val city = PrayerTimeRepository.currentCity
    val schedule = remember(city) { PrayerTimeRepository.calculatePrayerTimes(city) }
    val lastRead = QuranRepository.lastReadBookmark

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Top Banner with Emerald Gradient
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(EmeraldDark, EmeraldPrimary)
                        ),
                        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Assalamu'alaikum,",
                                fontSize = 13.sp,
                                color = GoldLight
                            )
                            Text(
                                text = "Sahabat Muslim",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        // City Location Chip
                        Surface(
                            onClick = onNavigateToPrayerTimes,
                            shape = RoundedCornerShape(20.dp),
                            color = Color.White.copy(alpha = 0.2f)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.LocationOn,
                                    contentDescription = null,
                                    tint = GoldLight,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = city.name,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Next Prayer Spotlight Box
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.12f)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = schedule.hijriDate,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = GoldLight
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Menuju ${schedule.nextPrayerName}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "Dalam ${schedule.timeToNextPrayer}",
                                    fontSize = 13.sp,
                                    color = EmeraldContainer
                                )
                            }

                            FilledTonalButton(
                                onClick = onNavigateToPrayerTimes,
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = GoldAccent,
                                    contentColor = OnGold
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text("Jadwal", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Horizontal Prayer Times Quick Bar
        item {
            val prayerTimes = listOf(
                "Subuh" to schedule.subuh,
                "Terbit" to schedule.terbit,
                "Dzuhur" to schedule.dzuhur,
                "Ashar" to schedule.ashar,
                "Maghrib" to schedule.maghrib,
                "Isya" to schedule.isya
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(prayerTimes) { (name, time) ->
                    val isNext = schedule.nextPrayerName.equals(name, ignoreCase = true)
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = if (isNext) EmeraldContainer else MaterialTheme.colorScheme.surface,
                        tonalElevation = 1.dp,
                        modifier = Modifier.width(90.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = name,
                                fontSize = 12.sp,
                                fontWeight = if (isNext) FontWeight.Bold else FontWeight.Normal,
                                color = if (isNext) EmeraldDark else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = time,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isNext) EmeraldPrimary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Continue Reading Qur'an Card
        item {
            PaddingBox {
                Card(
                    onClick = {
                        val surahNum = lastRead?.surahNumber ?: 1
                        onNavigateToSurah(surahNum)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("home_last_read_card"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(EmeraldContainer, RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = EmeraldPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Terakhir Dibaca",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EmeraldPrimary
                                )
                                Text(
                                    text = "${lastRead?.surahName ?: "Al-Fatihah"} : Ayat ${lastRead?.verseNumber ?: 1}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Lanjutkan",
                            tint = EmeraldPrimary
                        )
                    }
                }
            }
        }

        // Core Features Grid
        item {
            PaddingBox {
                Text(
                    text = "Menu Ibadah Utama",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(12.dp))

                val menuGrid = listOf(
                    MenuGridItem("Al-Qur'an", Icons.Default.MenuBook, EmeraldPrimary) { onNavigateToQuran() },
                    MenuGridItem("Doa & Zikir", Icons.Default.Favorite, EmeraldPrimary) { onNavigateToDoa() },
                    MenuGridItem("Tasbih Digital", Icons.Default.TouchApp, EmeraldPrimary) { onNavigateToTasbih() },
                    MenuGridItem("Panduan Salat", Icons.Default.Mosque, EmeraldPrimary) { onNavigateToPrayerGuide() },
                    MenuGridItem("Jadwal Salat", Icons.Default.AccessTime, EmeraldPrimary) { onNavigateToPrayerTimes() },
                    MenuGridItem("Arah Kiblat", Icons.Default.Explore, EmeraldPrimary) { onNavigateToQibla() },
                    MenuGridItem("Puasa & Qadha", Icons.Default.CalendarMonth, EmeraldPrimary) { onNavigateToFasting() },
                    MenuGridItem("Journey Ibadah", Icons.Default.TrackChanges, EmeraldPrimary) { onNavigateToJourney() },
                    MenuGridItem("Islamic Academy", Icons.Default.School, EmeraldPrimary) { onNavigateToAcademy() },
                    MenuGridItem("Kalkulator Zakat", Icons.Default.Calculate, EmeraldPrimary) { onNavigateToZakat() },
                    MenuGridItem("Sahabat AI", Icons.Default.AutoAwesome, GoldAccent) { onNavigateToSahabatAi() }
                )

                // Render in rows of 4
                menuGrid.chunked(4).forEach { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        rowItems.forEach { item ->
                            Column(
                                modifier = Modifier
                                    .width(76.dp)
                                    .clickable { item.onClick() }
                                    .testTag("menu_${item.title.lowercase().replace(" ", "_")}"),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(52.dp)
                                        .background(SageLight, RoundedCornerShape(16.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        item.icon,
                                        contentDescription = item.title,
                                        tint = item.tint,
                                        modifier = Modifier.size(26.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = item.title,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 14.sp
                                )
                            }
                        }
                        // Fill empty spacers if row < 4
                        for (i in 0 until (4 - rowItems.size)) {
                            Spacer(modifier = Modifier.width(76.dp))
                        }
                    }
                }
            }
        }

        // Ayat of the Day Card
        item {
            PaddingBox {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.MenuBook, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Ayat Hari Ini • QS. Al-Baqarah: 152",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "فَاذْكُرُونِي أَذْكُرْكُمْ وَاشْكُرُوا لِي وَلَا تَكْفُرُونِ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.End,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.fillMaxWidth(),
                            lineHeight = 32.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "\"Maka ingatlah kepada-Ku, niscaya Aku ingat (pula) kepadamu. Bersyukurlah kepada-Ku, dan janganlah kamu mengingkari (nikmat)-Ku.\"",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        // Daily Hadith / Wisdom Card
        item {
            PaddingBox {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GoldLight.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.FormatQuote, contentDescription = null, tint = OnGold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Mutiara Hadis Hari Ini",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnGold
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "\"Sebaik-baik manusia adalah yang paling bermanfaat bagi orang lain.\"",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnGold
                        )
                        Text(
                            text = "(HR. Ahmad & Thabrani - Hasan)",
                            fontSize = 11.sp,
                            color = OnGold
                        )
                    }
                }
            }
        }

        // HarmonyMan Developer Footnote
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sahabat Muslim",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Dikembangkan oleh HarmonyMan",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun ProfileSettingsScreen(
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit,
    onOpenPrayerTimes: () -> Unit
) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("Hamba Allah") }
    var showEditNameDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // User Profile Header Card
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(GoldAccent, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = userName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Kota: ${PrayerTimeRepository.currentCity.name}",
                            fontSize = 13.sp,
                            color = EmeraldContainer
                        )
                    }
                    IconButton(onClick = { showEditNameDialog = true }) {
                        Icon(Icons.Default.Edit, contentDescription = "Ubah Nama", tint = Color.White)
                    }
                }
            }
        }

        // Settings Section
        item {
            Text("Pengaturan Aplikasi", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column {
                    ListItem(
                        headlineContent = { Text("Tema Gelap (Dark Mode)") },
                        supportingContent = { Text("Sesuaikan kenyamanan mata saat malam") },
                        leadingContent = {
                            Icon(Icons.Default.DarkMode, contentDescription = null, tint = EmeraldPrimary)
                        },
                        trailingContent = {
                            Switch(
                                checked = isDarkMode,
                                onCheckedChange = { onToggleDarkMode() }
                            )
                        }
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                    ListItem(
                        headlineContent = { Text("Lokasi Perhitungan Salat") },
                        supportingContent = { Text("${PrayerTimeRepository.currentCity.name}, ${PrayerTimeRepository.currentCity.province}") },
                        leadingContent = {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = EmeraldPrimary)
                        },
                        trailingContent = {
                            Icon(Icons.Default.ChevronRight, contentDescription = null)
                        },
                        modifier = Modifier.clickable { onOpenPrayerTimes() }
                    )
                }
            }
        }

        // About Sahabat Muslim & HarmonyMan Developer Card
        item {
            Text("Tentang Aplikasi", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(EmeraldPrimary, RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Mosque, contentDescription = null, tint = GoldLight, modifier = Modifier.size(28.dp))
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text("Sahabat Muslim", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text("Versi 1.0.0 (Build Android)", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Tagline: \"Teman Baik dalam Setiap Langkah Ibadah\"",
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPrimary,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Sahabat Muslim adalah aplikasi pendamping ibadah digital yang membantu umat Islam menjalani kehidupan sehari-hari dengan lebih dekat kepada Allah melalui Al-Qur'an, doa, zikir, panduan ibadah, jadwal salat, puasa, dan pembelajaran Islam.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = SageLight,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Identitas Pengembang",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldPrimary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Dikembangkan oleh HarmonyMan",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = EmeraldDark
                            )
                            Text(
                                text = "Dedikasi untuk ekosistem ibadah digital yang bersih, akurat, ringan, dan ramah pengguna.",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }

    if (showEditNameDialog) {
        var tempName by remember { mutableStateOf(userName) }
        AlertDialog(
            onDismissRequest = { showEditNameDialog = false },
            title = { Text("Ubah Nama Panggilan") },
            text = {
                OutlinedTextField(
                    value = tempName,
                    onValueChange = { tempName = it },
                    label = { Text("Nama Anda") },
                    singleLine = true
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        userName = tempName.ifBlank { "Hamba Allah" }
                        showEditNameDialog = false
                        Toast.makeText(context, "Nama disimpan", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary)
                ) {
                    Text("Simpan")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditNameDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }
}

@Composable
fun PaddingBox(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        content = content
    )
}

data class MenuGridItem(
    val title: String,
    val icon: ImageVector,
    val tint: Color,
    val onClick: () -> Unit
)
