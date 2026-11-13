package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

data class IbadahMenuItem(
    val id: String,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val badge: String? = null,
    val onClick: () -> Unit
)

@Composable
fun IbadahHubScreen(
    onNavigateToDoa: () -> Unit,
    onNavigateToTasbih: () -> Unit,
    onNavigateToPrayerGuide: () -> Unit,
    onNavigateToPrayerTimes: () -> Unit,
    onNavigateToQibla: () -> Unit,
    onNavigateToFasting: () -> Unit,
    onNavigateToZakat: () -> Unit,
    onNavigateToAcademy: () -> Unit,
    onNavigateToSahabatAi: () -> Unit
) {
    val items = listOf(
        IbadahMenuItem(
            id = "jadwal_salat",
            title = "Jadwal Salat & Imsakiyah",
            description = "Waktu Subuh, Terbit, Dzuhur, Ashar, Maghrib, Isya berbasis astronomi",
            icon = Icons.Default.AccessTime,
            badge = "Akurat",
            onClick = onNavigateToPrayerTimes
        ),
        IbadahMenuItem(
            id = "kompas_kiblat",
            title = "Arah Kiblat",
            description = "Kompas Ka'bah real-time dengan sudut presisi & getaran haptik",
            icon = Icons.Default.Explore,
            badge = null,
            onClick = onNavigateToQibla
        ),
        IbadahMenuItem(
            id = "doa_zikir",
            title = "Kumpulan Doa & Zikir",
            description = "Doa harian, zikir pagi petang, dan ba'da salat dengan dalil hadis shahih",
            icon = Icons.Default.Favorite,
            badge = "Lengkap",
            onClick = onNavigateToDoa
        ),
        IbadahMenuItem(
            id = "tasbih_digital",
            title = "Tasbih Digital",
            description = "Penghitung zikir taktil dengan umpan balik getar dan putaran",
            icon = Icons.Default.TouchApp,
            badge = "Taktil",
            onClick = onNavigateToTasbih
        ),
        IbadahMenuItem(
            id = "panduan_salat",
            title = "Panduan Salat Wajib & Sunnah",
            description = "Tata cara langkah demi langkah, niat, bacaan Arab, dan kesalahan umum",
            icon = Icons.Default.Mosque,
            badge = "Bergambar",
            onClick = onNavigateToPrayerGuide
        ),
        IbadahMenuItem(
            id = "puasa_imsak",
            title = "Puasa & Kalender Sunnah",
            description = "Pelacak puasa Senin-Kamis, Ayyamul Bidh, dan hitung hutang qadha",
            icon = Icons.Default.CalendarMonth,
            badge = null,
            onClick = onNavigateToFasting
        ),
        IbadahMenuItem(
            id = "kalkulator_zakat",
            title = "Kalkulator Zakat",
            description = "Hitung zakat maal, zakat penghasilan, dan fitrah sesuai nisab resmi",
            icon = Icons.Default.Calculate,
            badge = "Nisab 85g",
            onClick = onNavigateToZakat
        ),
        IbadahMenuItem(
            id = "islamic_academy",
            title = "Islamic Academy & Tajwid",
            description = "Materi hukum bacaan Al-Qur'an dan kuis interaktif berhadiah skor",
            icon = Icons.Default.School,
            badge = "Kuis",
            onClick = onNavigateToAcademy
        ),
        IbadahMenuItem(
            id = "sahabat_ai",
            title = "Sahabat AI (Tanya Jawab Islam)",
            description = "Asisten wawasan keislaman terverifikasi dengan rujukan nash Al-Qur'an",
            icon = Icons.Default.AutoAwesome,
            badge = "AI Cerdas",
            onClick = onNavigateToSahabatAi
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Pusat Panduan & Layanan Ibadah",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Seluruh sarana ibadah digital praktis dalam genggaman Anda.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        items(items, key = { it.id }) { item ->
            Card(
                onClick = item.onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("ibadah_menu_${item.id}"),
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .background(SageLight, RoundedCornerShape(14.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                item.icon,
                                contentDescription = item.title,
                                tint = EmeraldPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = item.title,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                if (item.badge != null) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = if (item.id == "sahabat_ai") GoldLight else EmeraldContainer
                                    ) {
                                        Text(
                                            text = item.badge,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (item.id == "sahabat_ai") OnGold else EmeraldPrimary,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = item.description,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Icon(
                        Icons.Default.ChevronRight,
                        contentDescription = "Buka",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
