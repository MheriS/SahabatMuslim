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
import androidx.compose.material.icons.automirrored.filled.Send
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
import com.example.data.repository.AcademyAndZakatRepository
import com.example.ui.theme.*
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcademyScreen(
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Materi, 1: Kuis
    val tabs = listOf("Materi & Tajwid", "Kuis Interaktif")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Islamic Academy", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("academy_back_btn")) {
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
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 1.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                TabRow(
                    selectedTabIndex = selectedTab,
                    contentColor = EmeraldPrimary
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title, fontWeight = FontWeight.Bold) }
                        )
                    }
                }
            }

            if (selectedTab == 0) {
                // Materi & Tajwid Lessons
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(AcademyAndZakatRepository.academyLessons) { lesson ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = EmeraldContainer
                                ) {
                                    Text(
                                        text = lesson.category,
                                        fontSize = 11.sp,
                                        color = EmeraldPrimary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = lesson.title,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = lesson.content,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 19.sp
                                )

                                if (lesson.exampleArabic.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = SageLight.copy(alpha = 0.5f),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Text(
                                                text = "Contoh Penerapan:",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = EmeraldPrimary
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = lesson.exampleArabic,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.primary,
                                                fontFamily = FontFamily.Serif
                                            )
                                            Text(
                                                text = lesson.exampleLatin,
                                                fontSize = 12.sp,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                // Interactive Islamic Quiz
                InteractiveQuizSection()
            }
        }
    }
}

@Composable
fun InteractiveQuizSection() {
    val questions = AcademyAndZakatRepository.quizQuestions
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var isSubmitted by remember { mutableStateOf(false) }
    var isQuizCompleted by remember { mutableStateOf(false) }

    val currentQ = questions.getOrNull(currentIndex)

    if (isQuizCompleted || currentQ == null) {
        // Result Screen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .background(EmeraldContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(40.dp))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Hasil Kuis Islam", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        "Skor Anda: $score / ${questions.size}",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = EmeraldPrimary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (score >= 4) "Masya Allah, pemahaman keislaman Anda sangat baik!"
                        else "Terus semangat belajar memperdalam ilmu agama.",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            currentIndex = 0
                            selectedOption = null
                            score = 0
                            isSubmitted = false
                            isQuizCompleted = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Ulangi Kuis")
                    }
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Soal ${currentIndex + 1} dari ${questions.size}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPrimary
                    )
                    Text("Skor: $score", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        text = currentQ.question,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(18.dp),
                        lineHeight = 22.sp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                currentQ.options.forEachIndexed { index, option ->
                    val isChosen = selectedOption == index
                    val isCorrect = index == currentQ.correctIndex

                    val cardColor = when {
                        !isSubmitted -> if (isChosen) EmeraldContainer else MaterialTheme.colorScheme.surface
                        isCorrect -> EmeraldContainer
                        isChosen && !isCorrect -> Color(0xFFFFEBEE)
                        else -> MaterialTheme.colorScheme.surface
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable(enabled = !isSubmitted) {
                                selectedOption = index
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = cardColor),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isChosen,
                                onClick = { if (!isSubmitted) selectedOption = index }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = option,
                                fontSize = 14.sp,
                                fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }

                if (isSubmitted) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = SageLight.copy(alpha = 0.5f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Penjelasan:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = EmeraldPrimary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = currentQ.explanation,
                                fontSize = 12.sp,
                                lineHeight = 17.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (!isSubmitted) {
                    Button(
                        onClick = {
                            if (selectedOption != null) {
                                isSubmitted = true
                                if (selectedOption == currentQ.correctIndex) {
                                    score += 1
                                }
                            }
                        },
                        enabled = selectedOption != null,
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary)
                    ) {
                        Text("Periksa Jawaban")
                    }
                } else {
                    Button(
                        onClick = {
                            if (currentIndex < questions.size - 1) {
                                currentIndex += 1
                                selectedOption = null
                                isSubmitted = false
                            } else {
                                isQuizCompleted = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary)
                    ) {
                        Text(if (currentIndex < questions.size - 1) "Soal Berikutnya" else "Lihat Hasil")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZakatCalculatorScreen(
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    val formatRupiah = remember {
        NumberFormat.getCurrencyInstance(Locale("id", "ID")).apply {
            maximumFractionDigits = 0
        }
    }

    var selectedZakatTab by remember { mutableIntStateOf(0) } // 0: Maal, 1: Penghasilan, 2: Fitrah
    val zakatTabs = listOf("Zakat Maal", "Penghasilan", "Zakat Fitrah")

    // Input States
    var tabunganInput by remember { mutableStateOf("150000000") }
    var emasGramInput by remember { mutableStateOf("0") }
    var hargaEmasInput by remember { mutableStateOf("1400000") }

    var gajiBulananInput by remember { mutableStateOf("12000000") }
    var bonusBulananInput by remember { mutableStateOf("0") }

    var jumlahJiwaInput by remember { mutableStateOf("4") }
    var hargaBerasPerJiwaInput by remember { mutableStateOf("45000") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kalkulator Zakat", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("zakat_back_btn")) {
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
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 1.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                TabRow(selectedTabIndex = selectedZakatTab, contentColor = EmeraldPrimary) {
                    zakatTabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedZakatTab == index,
                            onClick = { selectedZakatTab = index },
                            text = { Text(title, fontWeight = FontWeight.Bold, fontSize = 12.sp) }
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (selectedZakatTab) {
                    0 -> {
                        // Zakat Maal (Tabungan & Emas)
                        item {
                            val hargaEmas = hargaEmasInput.toDoubleOrNull() ?: 1400000.0
                            val nisabMaal = 85.0 * hargaEmas // 85 gr emas
                            val tabungan = tabunganInput.toDoubleOrNull() ?: 0.0
                            val emasGram = emasGramInput.toDoubleOrNull() ?: 0.0
                            val totalHarta = tabungan + (emasGram * hargaEmas)
                            val wajibZakat = totalHarta >= nisabMaal
                            val zakatNominal = if (wajibZakat) totalHarta * 0.025 else 0.0

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = EmeraldPrimary)
                            ) {
                                Column(modifier = Modifier.padding(18.dp)) {
                                    Text("ZAKAT MAAL WAJIB DITUNAIKAN", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GoldLight)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = formatRupiah.format(zakatNominal),
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = if (wajibZakat) "Harta Anda melebihi nisab 85 gr emas (${formatRupiah.format(nisabMaal)})."
                                        else "Belum mencapai nisab 85 gr emas (${formatRupiah.format(nisabMaal)}).",
                                        fontSize = 12.sp,
                                        color = EmeraldContainer
                                    )
                                }
                            }
                        }

                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Parameter Harta Tersimpan (Haul 1 Tahun)", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    OutlinedTextField(
                                        value = tabunganInput,
                                        onValueChange = { tabunganInput = it },
                                        label = { Text("Total Tabungan/Deposito/Uang Tunai (Rp)") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    OutlinedTextField(
                                        value = emasGramInput,
                                        onValueChange = { emasGramInput = it },
                                        label = { Text("Emas Tersimpan (gram)") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    OutlinedTextField(
                                        value = hargaEmasInput,
                                        onValueChange = { hargaEmasInput = it },
                                        label = { Text("Harga Emas Acuan per Gram (Rp)") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                    1 -> {
                        // Zakat Penghasilan
                        item {
                            val gaji = gajiBulananInput.toDoubleOrNull() ?: 0.0
                            val bonus = bonusBulananInput.toDoubleOrNull() ?: 0.0
                            val totalPendapatan = gaji + bonus
                            val hargaEmas = 1400000.0
                            val nisabBulanan = (85.0 * hargaEmas) / 12.0 // Rp ~9.916.667
                            val wajibZakat = totalPendapatan >= nisabBulanan
                            val zakatBulanan = if (wajibZakat) totalPendapatan * 0.025 else 0.0

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = EmeraldPrimary)
                            ) {
                                Column(modifier = Modifier.padding(18.dp)) {
                                    Text("ZAKAT PENGHASILAN PER BULAN", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GoldLight)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = formatRupiah.format(zakatBulanan),
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = if (wajibZakat) "Pendapatan memenuhi nisab bulanan (${formatRupiah.format(nisabBulanan)})."
                                        else "Pendapatan di bawah nisab bulanan (${formatRupiah.format(nisabBulanan)}).",
                                        fontSize = 12.sp,
                                        color = EmeraldContainer
                                    )
                                }
                            }
                        }

                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Pendapatan & Pemasukan", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    OutlinedTextField(
                                        value = gajiBulananInput,
                                        onValueChange = { gajiBulananInput = it },
                                        label = { Text("Gaji Pokok & Tunjangan Bulanan (Rp)") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    OutlinedTextField(
                                        value = bonusBulananInput,
                                        onValueChange = { bonusBulananInput = it },
                                        label = { Text("Pendapatan Tambahan / Bonus (Rp)") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                    2 -> {
                        // Zakat Fitrah
                        item {
                            val jiwa = jumlahJiwaInput.toIntOrNull() ?: 1
                            val hargaPerJiwa = hargaBerasPerJiwaInput.toDoubleOrNull() ?: 45000.0
                            val totalZakatUang = jiwa * hargaPerJiwa
                            val totalBerasKg = jiwa * 2.5

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = EmeraldPrimary)
                            ) {
                                Column(modifier = Modifier.padding(18.dp)) {
                                    Text("TOTAL ZAKAT FITRAH ($jiwa JIWA)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GoldLight)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = formatRupiah.format(totalZakatUang),
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Atau setara dengan $totalBerasKg kg beras (2.5 kg per jiwa).",
                                        fontSize = 12.sp,
                                        color = EmeraldContainer
                                    )
                                }
                            }
                        }

                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Parameter Zakat Fitrah", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    OutlinedTextField(
                                        value = jumlahJiwaInput,
                                        onValueChange = { jumlahJiwaInput = it },
                                        label = { Text("Jumlah Anggota Keluarga (Jiwa)") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    OutlinedTextField(
                                        value = hargaBerasPerJiwaInput,
                                        onValueChange = { hargaBerasPerJiwaInput = it },
                                        label = { Text("Nilai Konversi Beras per Jiwa (Rp)") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SahabatAiScreen(
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    val quickTopics = AcademyAndZakatRepository.aiKnowledgeTopics
    var selectedTopicContent by remember { mutableStateOf(quickTopics[0].second) }
    var currentTopicTitle by remember { mutableStateOf(quickTopics[0].first) }
    var userInputQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Sahabat AI", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Asisten Ilmu & Wawasan Islami", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("ai_back_btn")) {
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
        ) {
            // Preset Topic Pills
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 1.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                    Text("Topik Tanya Jawab Populer:", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(6.dp))
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 140.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(quickTopics) { (title, answer) ->
                            val isSelected = currentTopicTitle == title
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isSelected) EmeraldContainer else SageLight.copy(alpha = 0.5f),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentTopicTitle = title
                                        selectedTopicContent = answer
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        Icons.Default.HelpOutline,
                                        contentDescription = null,
                                        tint = if (isSelected) EmeraldPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = title,
                                        fontSize = 13.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) EmeraldDark else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Answer Content View
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
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
                                        .size(36.dp)
                                        .background(EmeraldContainer, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(20.dp))
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(currentTopicTitle, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    Text("Referensi Al-Qur'an & Sunnah Shahih", fontSize = 11.sp, color = EmeraldPrimary)
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))
                            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = selectedTopicContent,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 22.sp
                            )
                        }
                    }
                }

                // Disclaimer note
                item {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = GoldLight.copy(alpha = 0.4f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = OnGold, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Catatan: Sahabat AI menyajikan referensi nash Al-Qur'an dan Hadis terverifikasi untuk tujuan pembelajaran. Untuk masalah hukum fikih kasuistik, disarankan meminta fatwa langsung kepada ulama/asatidz kompeten.",
                                fontSize = 11.sp,
                                color = OnGold,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }

            // Bottom Prompt Input Bar
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = userInputQuery,
                        onValueChange = { userInputQuery = it },
                        placeholder = { Text("Tanyakan konsep ibadah...", fontSize = 13.sp) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = {
                            if (userInputQuery.isNotBlank()) {
                                currentTopicTitle = "Pertanyaan: $userInputQuery"
                                selectedTopicContent = """Berdasarkan prinsip syariat Islam terkait "$userInputQuery":
1. Landasan Utama:
"Bertanyalah kepada orang yang mempunyai pengetahuan jika kamu tidak mengetahui." (QS. An-Nahl: 43).
2. Tuntunan:
Segala urusan ibadah hendaknya dilandasi niat ikhlas semata mengharap ridha Allah SWT dan ittiba' (mengikuti) petunjuk Rasulullah SAW.
3. Rujukan Tambahan:
Dianjurkan membaca kitab Riyadhus Shalihin dan Fiqhus Sunnah untuk pendalaman materi ini."""
                                userInputQuery = ""
                            }
                        },
                        modifier = Modifier
                            .background(EmeraldPrimary, CircleShape)
                            .size(44.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Kirim", tint = Color.White)
                    }
                }
            }
        }
    }
}
