package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.screens.*
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.SahabatMuslimTheme

sealed class Screen {
    object Home : Screen()
    object Quran : Screen()
    object Ibadah : Screen()
    object Journey : Screen()
    object Profile : Screen()

    // Sub-destinations
    data class QuranReader(val surahNumber: Int) : Screen()
    data class Tasbih(val presetTitle: String? = null, val target: Int? = null) : Screen()
    data class PrayerGuideDetail(val guideId: String) : Screen()
    object PrayerTimes : Screen()
    object Qibla : Screen()
    object Fasting : Screen()
    object DoaZikir : Screen()
    object PrayerGuideList : Screen()
    object Academy : Screen()
    object Zakat : Screen()
    object SahabatAi : Screen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemDark = isSystemInDarkTheme()
            var isDarkMode by remember { mutableStateOf(systemDark) }

            SahabatMuslimTheme(darkTheme = isDarkMode) {
                SahabatMuslimApp(
                    isDarkMode = isDarkMode,
                    onToggleDarkMode = { isDarkMode = !isDarkMode }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SahabatMuslimApp(
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit
) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    var selectedBottomTab by remember { mutableIntStateOf(0) }

    // Intercept hardware back button when in sub-screens
    if (currentScreen !is Screen.Home &&
        currentScreen !is Screen.Quran &&
        currentScreen !is Screen.Ibadah &&
        currentScreen !is Screen.Journey &&
        currentScreen !is Screen.Profile
    ) {
        BackHandler {
            // Return to previously active tab
            currentScreen = when (selectedBottomTab) {
                0 -> Screen.Home
                1 -> Screen.Quran
                2 -> Screen.Ibadah
                3 -> Screen.Journey
                4 -> Screen.Profile
                else -> Screen.Home
            }
        }
    }

    val isRootTab = currentScreen is Screen.Home ||
            currentScreen is Screen.Quran ||
            currentScreen is Screen.Ibadah ||
            currentScreen is Screen.Journey ||
            currentScreen is Screen.Profile

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            if (isRootTab) {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = "Sahabat Muslim",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = EmeraldPrimary
                            )
                            Text(
                                text = "Teman Baik dalam Setiap Langkah Ibadah",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { currentScreen = Screen.PrayerTimes },
                            modifier = Modifier.testTag("topbar_prayer_times_btn")
                        ) {
                            Icon(
                                Icons.Default.AccessTime,
                                contentDescription = "Jadwal Salat",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(
                            onClick = { currentScreen = Screen.SahabatAi },
                            modifier = Modifier.testTag("topbar_ai_btn")
                        ) {
                            Icon(
                                Icons.Default.AutoAwesome,
                                contentDescription = "Sahabat AI",
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        },
        bottomBar = {
            if (isRootTab) {
                NavigationBar(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 3.dp
                ) {
                    val navItems = listOf(
                        NavTabItem("Beranda", Icons.Filled.Home, Icons.Outlined.Home, "nav_home", 0) {
                            selectedBottomTab = 0
                            currentScreen = Screen.Home
                        },
                        NavTabItem("Al-Qur'an", Icons.Filled.MenuBook, Icons.Outlined.MenuBook, "nav_quran", 1) {
                            selectedBottomTab = 1
                            currentScreen = Screen.Quran
                        },
                        NavTabItem("Ibadah", Icons.Filled.Mosque, Icons.Outlined.Mosque, "nav_ibadah", 2) {
                            selectedBottomTab = 2
                            currentScreen = Screen.Ibadah
                        },
                        NavTabItem("Journey", Icons.Filled.TrackChanges, Icons.Outlined.TrackChanges, "nav_journey", 3) {
                            selectedBottomTab = 3
                            currentScreen = Screen.Journey
                        },
                        NavTabItem("Profil", Icons.Filled.Person, Icons.Outlined.Person, "nav_profile", 4) {
                            selectedBottomTab = 4
                            currentScreen = Screen.Profile
                        }
                    )

                    navItems.forEach { item ->
                        val selected = selectedBottomTab == item.index
                        NavigationBarItem(
                            selected = selected,
                            onClick = item.onClick,
                            icon = {
                                Icon(
                                    if (selected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label, fontSize = 11.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal) },
                            modifier = Modifier.testTag(item.testTag),
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedIconColor = EmeraldPrimary,
                                selectedTextColor = EmeraldPrimary
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val screen = currentScreen) {
                is Screen.Home -> {
                    HomeScreen(
                        onNavigateToQuran = {
                            selectedBottomTab = 1
                            currentScreen = Screen.Quran
                        },
                        onNavigateToSurah = { surahNum ->
                            currentScreen = Screen.QuranReader(surahNum)
                        },
                        onNavigateToDoa = { currentScreen = Screen.DoaZikir },
                        onNavigateToTasbih = { currentScreen = Screen.Tasbih() },
                        onNavigateToPrayerGuide = { currentScreen = Screen.PrayerGuideList },
                        onNavigateToPrayerTimes = { currentScreen = Screen.PrayerTimes },
                        onNavigateToQibla = { currentScreen = Screen.Qibla },
                        onNavigateToFasting = { currentScreen = Screen.Fasting },
                        onNavigateToJourney = {
                            selectedBottomTab = 3
                            currentScreen = Screen.Journey
                        },
                        onNavigateToAcademy = { currentScreen = Screen.Academy },
                        onNavigateToZakat = { currentScreen = Screen.Zakat },
                        onNavigateToSahabatAi = { currentScreen = Screen.SahabatAi }
                    )
                }

                is Screen.Quran -> {
                    QuranScreen(
                        onSurahSelected = { surahNum ->
                            currentScreen = Screen.QuranReader(surahNum)
                        }
                    )
                }

                is Screen.Ibadah -> {
                    IbadahHubScreen(
                        onNavigateToDoa = { currentScreen = Screen.DoaZikir },
                        onNavigateToTasbih = { currentScreen = Screen.Tasbih() },
                        onNavigateToPrayerGuide = { currentScreen = Screen.PrayerGuideList },
                        onNavigateToPrayerTimes = { currentScreen = Screen.PrayerTimes },
                        onNavigateToQibla = { currentScreen = Screen.Qibla },
                        onNavigateToFasting = { currentScreen = Screen.Fasting },
                        onNavigateToZakat = { currentScreen = Screen.Zakat },
                        onNavigateToAcademy = { currentScreen = Screen.Academy },
                        onNavigateToSahabatAi = { currentScreen = Screen.SahabatAi }
                    )
                }

                is Screen.Journey -> {
                    JourneyScreen(
                        onOpenQuran = {
                            selectedBottomTab = 1
                            currentScreen = Screen.Quran
                        }
                    )
                }

                is Screen.Profile -> {
                    ProfileSettingsScreen(
                        isDarkMode = isDarkMode,
                        onToggleDarkMode = onToggleDarkMode,
                        onOpenPrayerTimes = { currentScreen = Screen.PrayerTimes }
                    )
                }

                // Sub-screens
                is Screen.QuranReader -> {
                    QuranReaderScreen(
                        surahNumber = screen.surahNumber,
                        onBack = {
                            currentScreen = when (selectedBottomTab) {
                                1 -> Screen.Quran
                                else -> Screen.Home
                            }
                        }
                    )
                }

                is Screen.Tasbih -> {
                    TasbihScreen(
                        initialTitle = screen.presetTitle,
                        initialTarget = screen.target,
                        onBack = {
                            currentScreen = when (selectedBottomTab) {
                                2 -> Screen.Ibadah
                                else -> Screen.Home
                            }
                        }
                    )
                }

                is Screen.PrayerTimes -> {
                    PrayerTimesScreen(
                        onOpenQibla = { currentScreen = Screen.Qibla },
                        onBack = {
                            currentScreen = when (selectedBottomTab) {
                                2 -> Screen.Ibadah
                                else -> Screen.Home
                            }
                        }
                    )
                }

                is Screen.Qibla -> {
                    QiblaScreen(
                        onBack = {
                            currentScreen = when (selectedBottomTab) {
                                2 -> Screen.Ibadah
                                else -> Screen.Home
                            }
                        }
                    )
                }

                is Screen.DoaZikir -> {
                    DoaZikirScreen(
                        onOpenTasbih = { title, count ->
                            currentScreen = Screen.Tasbih(title, count)
                        }
                    )
                }

                is Screen.PrayerGuideList -> {
                    PrayerGuideListScreen(
                        onGuideSelected = { guideId ->
                            currentScreen = Screen.PrayerGuideDetail(guideId)
                        },
                        onBack = {
                            currentScreen = when (selectedBottomTab) {
                                2 -> Screen.Ibadah
                                else -> Screen.Home
                            }
                        }
                    )
                }

                is Screen.PrayerGuideDetail -> {
                    PrayerGuideDetailScreen(
                        guideId = screen.guideId,
                        onBack = {
                            currentScreen = Screen.PrayerGuideList
                        }
                    )
                }

                is Screen.Fasting -> {
                    FastingScreen(
                        onBack = {
                            currentScreen = when (selectedBottomTab) {
                                2 -> Screen.Ibadah
                                else -> Screen.Home
                            }
                        }
                    )
                }

                is Screen.Academy -> {
                    AcademyScreen(
                        onBack = {
                            currentScreen = when (selectedBottomTab) {
                                2 -> Screen.Ibadah
                                else -> Screen.Home
                            }
                        }
                    )
                }

                is Screen.Zakat -> {
                    ZakatCalculatorScreen(
                        onBack = {
                            currentScreen = when (selectedBottomTab) {
                                2 -> Screen.Ibadah
                                else -> Screen.Home
                            }
                        }
                    )
                }

                is Screen.SahabatAi -> {
                    SahabatAiScreen(
                        onBack = {
                            currentScreen = when (selectedBottomTab) {
                                2 -> Screen.Ibadah
                                else -> Screen.Home
                            }
                        }
                    )
                }
            }
        }
    }
}

data class NavTabItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String,
    val index: Int,
    val onClick: () -> Unit
)
