package com.example.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.FastingAndJourneyRepository
import com.example.data.repository.PrayerTimeRepository
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FastingScreen(
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    val context = LocalContext.current
    val schedule = remember { PrayerTimeRepository.calculatePrayerTimes() }
    var qadhaTotal by remember { mutableIntStateOf(FastingAndJourneyRepository.qadhaTotalDebt) }
    var qadhaDone by remember { mutableIntStateOf(FastingAndJourneyRepository.qadhaCompleted) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Puasa & Imsakiyah", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("fasting_back_btn")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
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
            // Imsak & Iftar Banner
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = EmeraldPrimary)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "JADWAL PUASA HARI INI",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldLight,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Waktu Imsak", fontSize = 12.sp, color = EmeraldContainer)
                                Text(schedule.imsak, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                                Text("Awal Menahan Diri", fontSize = 10.sp, color = GoldLight)
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text("Waktu Berbuka (Maghrib)", fontSize = 12.sp, color = EmeraldContainer)
                                Text(schedule.maghrib, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                                Text("Saat Berbuka Puasa", fontSize = 10.sp, color = GoldLight)
                            }
                        }
                    }
                }
            }

            // Qadha Debt Tracker Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.BookmarkBorder, contentDescription = null, tint = EmeraldPrimary)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Pelacak Hutang Puasa Qadha", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        val remaining = (qadhaTotal - qadhaDone).coerceAtLeast(0)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Total Hutang", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text("$qadhaTotal hari", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Row {
                                    IconButton(
                                        onClick = { if (qadhaTotal > 0) qadhaTotal -= 1 },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(Icons.Default.Remove, contentDescription = null)
                                    }
                                    IconButton(
                                        onClick = { qadhaTotal += 1 },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(Icons.Default.Add, contentDescription = null)
                                    }
                                }
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Terbayar", fontSize = 11.sp, color = EmeraldPrimary)
                                Text("$qadhaDone hari", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
                                Row {
                                    IconButton(
                                        onClick = { if (qadhaDone > 0) qadhaDone -= 1 },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(Icons.Default.Remove, contentDescription = null, tint = EmeraldPrimary)
                                    }
                                    IconButton(
                                        onClick = {
                                            if (qadhaDone < qadhaTotal) {
                                                qadhaDone += 1
                                                Toast.makeText(context, "Alhamdulillah! 1 hari qadha terbayar", Toast.LENGTH_SHORT).show()
                                            }
                                        },
                                        modifier = Modifier.size(28.dp)
                                    ) {
                                        Icon(Icons.Default.Add, contentDescription = null, tint = EmeraldPrimary)
                                    }
                                }
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Sisa", fontSize = 11.sp, color = SunsetMaghrib)
                                Text("$remaining hari", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = SunsetMaghrib)
                            }
                        }
                    }
                }
            }

            // Upcoming Sunnah Fasts
            item {
                Text("Jadwal Puasa Sunnah Mendatang", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            items(FastingAndJourneyRepository.fastingSchedule) { record ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(if (record.completed) EmeraldContainer else SageLight, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    if (record.completed) Icons.Default.Check else Icons.Default.CalendarToday,
                                    contentDescription = null,
                                    tint = if (record.completed) EmeraldPrimary else MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(record.notes, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text(
                                    text = "${record.fastingType} • ${record.dateIso}",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        if (record.completed) {
                            Surface(color = EmeraldContainer, shape = RoundedCornerShape(8.dp)) {
                                Text("Terlaksana", color = EmeraldPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                            }
                        }
                    }
                }
            }

            // Niat Niat Puasa
            item {
                Text("Bacaan Niat Puasa", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Niat Puasa Sunnah Senin / Kamis", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "نَوَيْتُ صَوْمَ يَوْمِ الِاثْنَيْنِ / الْخَمِيسِ سُنَّةً لِلَّهِ تَعَالَى",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Nawaitu shauma yaumil-itsnaini / yaumil-khamisi sunnatan lillaahi Ta'aala.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Aku niat puasa sunnah hari Senin / Kamis karena Allah Ta'ala.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun JourneyScreen(
    onOpenQuran: () -> Unit
) {
    val context = LocalContext.current
    var habitState by remember { mutableStateOf(FastingAndJourneyRepository.todayHabits) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Motivation Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = EmeraldPrimary)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "PERJALANAN IBADAH • ISTIQOMAH",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldLight,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Amalan yang paling dicintai Allah adalah amalan yang konsisten meskipun sedikit.",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "(HR. Bukhari no. 6464 & Muslim no. 782)",
                        fontSize = 11.sp,
                        color = EmeraldContainer
                    )
                }
            }
        }

        // Daily 5 Prayers Check-off
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Salat 5 Waktu Hari Ini",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        val completedCount = listOf(
                            habitState.subuhDone,
                            habitState.dzuhurDone,
                            habitState.asharDone,
                            habitState.maghribDone,
                            habitState.isyaDone
                        ).count { it }
                        Text(
                            text = "$completedCount / 5 Selesai",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = EmeraldPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    val prayers = listOf(
                        "Subuh" to habitState.subuhDone,
                        "Dzuhur" to habitState.dzuhurDone,
                        "Ashar" to habitState.asharDone,
                        "Maghrib" to habitState.maghribDone,
                        "Isya" to habitState.isyaDone
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        prayers.forEach { (name, isDone) ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .background(
                                            if (isDone) EmeraldPrimary else SageLight,
                                            CircleShape
                                        )
                                        .clickable {
                                            FastingAndJourneyRepository.togglePrayer(name)
                                            habitState = FastingAndJourneyRepository.todayHabits
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        if (isDone) Icons.Default.Check else Icons.Default.RadioButtonUnchecked,
                                        contentDescription = name,
                                        tint = if (isDone) Color.White else MaterialTheme.colorScheme.outline
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = name,
                                    fontSize = 11.sp,
                                    fontWeight = if (isDone) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isDone) EmeraldPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        // Tilawah Qur'an Card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
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
                            Icon(Icons.Default.MenuBook, contentDescription = null, tint = EmeraldPrimary)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Tilawah Al-Qur'an", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text("${habitState.tilawahPages} Halaman hari ini", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                FastingAndJourneyRepository.addTilawahPages(-1)
                                habitState = FastingAndJourneyRepository.todayHabits
                            }
                        ) {
                            Icon(Icons.Default.RemoveCircleOutline, contentDescription = "Kurang")
                        }
                        Text(
                            text = "${habitState.tilawahPages}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                        IconButton(
                            onClick = {
                                FastingAndJourneyRepository.addTilawahPages(1)
                                habitState = FastingAndJourneyRepository.todayHabits
                                Toast.makeText(context, "+1 Halaman Tilawah dicatat", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Icon(Icons.Default.AddCircle, contentDescription = "Tambah", tint = EmeraldPrimary)
                        }
                    }
                }
            }
        }

        // Zikir & Sedekah Toggles
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Zikir Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            FastingAndJourneyRepository.toggleZikir()
                            habitState = FastingAndJourneyRepository.todayHabits
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (habitState.zikirDone) EmeraldContainer else MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(
                            if (habitState.zikirDone) Icons.Default.CheckCircle else Icons.Default.Circle,
                            contentDescription = null,
                            tint = if (habitState.zikirDone) EmeraldPrimary else MaterialTheme.colorScheme.outline
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Zikir Harian", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(
                            if (habitState.zikirDone) "Terlaksana" else "Belum tercatat",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Sedekah Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            FastingAndJourneyRepository.toggleSedekah()
                            habitState = FastingAndJourneyRepository.todayHabits
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (habitState.sedekahDone) GoldLight else MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(
                            if (habitState.sedekahDone) Icons.Default.CheckCircle else Icons.Default.Circle,
                            contentDescription = null,
                            tint = if (habitState.sedekahDone) OnGold else MaterialTheme.colorScheme.outline
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Sedekah Harian", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(
                            if (habitState.sedekahDone) "Terlaksana" else "Belum dicatat",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
