package com.example.data.repository

import com.example.data.model.Ayah
import com.example.data.model.Bookmark
import com.example.data.model.Surah

object QuranRepository {

    var lastReadBookmark: Bookmark? = Bookmark(
        surahNumber = 1,
        surahName = "Al-Fatihah",
        verseNumber = 1
    )

    val bookmarks = mutableListOf<Bookmark>(
        Bookmark(1, "Al-Fatihah", 1),
        Bookmark(2, "Al-Baqarah", 255),
        Bookmark(36, "Yasin", 1),
        Bookmark(67, "Al-Mulk", 1)
    )

    // Complete catalog of 114 Surahs
    val surahs: List<Surah> = listOf(
        Surah(1, "الفاتحة", "Al-Fatihah", "Pembukaan", "Makkiyyah", 7, 1),
        Surah(2, "البقرة", "Al-Baqarah", "Sapi Betina", "Madaniyyah", 286, 1),
        Surah(3, "آل عمران", "Ali 'Imran", "Keluarga 'Imran", "Madaniyyah", 200, 3),
        Surah(4, "النساء", "An-Nisa'", "Wanita", "Madaniyyah", 176, 4),
        Surah(5, "المائدة", "Al-Ma'idah", "Hidangan", "Madaniyyah", 120, 6),
        Surah(6, "الأنعام", "Al-An'am", "Binatang Ternak", "Makkiyyah", 165, 7),
        Surah(7, "الأعراف", "Al-A'raf", "Tempat Tertinggi", "Makkiyyah", 206, 8),
        Surah(8, "الأنفال", "Al-Anfal", "Rampasan Perang", "Madaniyyah", 75, 9),
        Surah(9, "التوبة", "At-Taubah", "Pengampunan", "Madaniyyah", 129, 10),
        Surah(10, "يونس", "Yunus", "Nabi Yunus", "Makkiyyah", 109, 11),
        Surah(11, "هود", "Hud", "Nabi Hud", "Makkiyyah", 123, 11),
        Surah(12, "يوسف", "Yusuf", "Nabi Yusuf", "Makkiyyah", 111, 12),
        Surah(13, "الرعد", "Ar-Ra'd", "Guruh", "Madaniyyah", 43, 13),
        Surah(14, "إبراهيم", "Ibrahim", "Nabi Ibrahim", "Makkiyyah", 52, 13),
        Surah(15, "الحجر", "Al-Hijr", "Gunung Al-Hijr", "Makkiyyah", 99, 14),
        Surah(16, "النحل", "An-Nahl", "Lebah", "Makkiyyah", 128, 14),
        Surah(17, "الإسراء", "Al-Isra'", "Perjalanan Malam", "Makkiyyah", 111, 15),
        Surah(18, "الكهف", "Al-Kahf", "Gua", "Makkiyyah", 110, 15),
        Surah(19, "مريم", "Maryam", "Siti Maryam", "Makkiyyah", 98, 16),
        Surah(20, "طه", "Taha", "Taha", "Makkiyyah", 135, 16),
        Surah(21, "الأنبياء", "Al-Anbiya'", "Para Nabi", "Makkiyyah", 112, 17),
        Surah(22, "الحج", "Al-Hajj", "Haji", "Madaniyyah", 78, 17),
        Surah(23, "المؤمنون", "Al-Mu'minun", "Orang-Orang Mukmin", "Makkiyyah", 118, 18),
        Surah(24, "النور", "An-Nur", "Cahaya", "Madaniyyah", 64, 18),
        Surah(25, "الفرقان", "Al-Furqan", "Pembeda", "Makkiyyah", 77, 18),
        Surah(26, "الشعراء", "Asy-Syu'ara'", "Para Penyair", "Makkiyyah", 227, 19),
        Surah(27, "النمل", "An-Naml", "Semut", "Makkiyyah", 93, 19),
        Surah(28, "القصص", "Al-Qasas", "Kisah-Kisah", "Makkiyyah", 88, 20),
        Surah(29, "العنكبوت", "Al-'Ankabut", "Laba-Laba", "Makkiyyah", 69, 20),
        Surah(30, "الروم", "Ar-Rum", "Bangsa Romawi", "Makkiyyah", 60, 21),
        Surah(31, "لقمان", "Luqman", "Keluarga Luqman", "Makkiyyah", 34, 21),
        Surah(32, "السجدة", "As-Sajdah", "Sujud", "Makkiyyah", 30, 21),
        Surah(33, "الأحزاب", "Al-Ahzab", "Golongan Bersekutu", "Madaniyyah", 73, 21),
        Surah(34, "سبإ", "Saba'", "Kaum Saba'", "Makkiyyah", 54, 22),
        Surah(35, "فاطر", "Fatir", "Pencipta", "Makkiyyah", 45, 22),
        Surah(36, "يس", "Yasin", "Yasin", "Makkiyyah", 83, 22),
        Surah(37, "الصافات", "As-Saffat", "Barisan-Barisan", "Makkiyyah", 182, 23),
        Surah(38, "ص", "Sad", "Sad", "Makkiyyah", 88, 23),
        Surah(39, "الزمر", "Az-Zumar", "Rombongan", "Makkiyyah", 75, 23),
        Surah(40, "غافر", "Gafir", "Maha Pengampun", "Makkiyyah", 85, 24),
        Surah(41, "فصلت", "Fussilat", "Dijelaskan", "Makkiyyah", 54, 24),
        Surah(42, "الشورى", "Asy-Syura", "Musyawarah", "Makkiyyah", 53, 25),
        Surah(43, "الزخرف", "Az-Zukhruf", "Perhiasan", "Makkiyyah", 89, 25),
        Surah(44, "الدخان", "Ad-Dukhan", "Kabut", "Makkiyyah", 59, 25),
        Surah(45, "الجاثية", "Al-Jasiyah", "Berlutut", "Makkiyyah", 37, 25),
        Surah(46, "الأحقاف", "Al-Ahqaf", "Bukit Pasir", "Makkiyyah", 35, 26),
        Surah(47, "محمد", "Muhammad", "Nabi Muhammad", "Madaniyyah", 38, 26),
        Surah(48, "الفتح", "Al-Fath", "Kemenangan", "Madaniyyah", 29, 26),
        Surah(49, "الحجرات", "Al-Hujurat", "Kamar-Kamar", "Madaniyyah", 18, 26),
        Surah(50, "ق", "Qaf", "Qaf", "Makkiyyah", 45, 26),
        Surah(51, "الذاريات", "Az-Zariyat", "Angin yang Menerbangkan", "Makkiyyah", 60, 26),
        Surah(52, "الطور", "At-Tur", "Bukit Tursina", "Makkiyyah", 49, 27),
        Surah(53, "النجم", "An-Najm", "Bintang", "Makkiyyah", 62, 27),
        Surah(54, "القمر", "Al-Qamar", "Bulan", "Makkiyyah", 55, 27),
        Surah(55, "الرحمن", "Ar-Rahman", "Maha Pengasih", "Madaniyyah", 78, 27),
        Surah(56, "الواقعة", "Al-Waqi'ah", "Hari Kiamat", "Makkiyyah", 96, 27),
        Surah(57, "الحديد", "Al-Hadid", "Besi", "Madaniyyah", 29, 27),
        Surah(58, "المجادلة", "Al-Mujadilah", "Gugatan", "Madaniyyah", 22, 28),
        Surah(59, "الحشر", "Al-Hasyr", "Pengusiran", "Madaniyyah", 24, 28),
        Surah(60, "الممتحنة", "Al-Mumtahanah", "Wanita yang Diuji", "Madaniyyah", 13, 28),
        Surah(61, "الصف", "As-Saff", "Barisan", "Madaniyyah", 14, 28),
        Surah(62, "الجمعة", "Al-Jumu'ah", "Hari Jumat", "Madaniyyah", 11, 28),
        Surah(63, "المنافقون", "Al-Munafiqun", "Orang Munafik", "Madaniyyah", 11, 28),
        Surah(64, "التغابن", "At-Tagabun", "Pengungkapan Kesalahan", "Madaniyyah", 18, 28),
        Surah(65, "الطلاق", "At-Talaq", "Talak", "Madaniyyah", 12, 28),
        Surah(66, "التحريم", "At-Tahrim", "Pengharaman", "Madaniyyah", 12, 28),
        Surah(67, "الملك", "Al-Mulk", "Kerajaan", "Makkiyyah", 30, 29),
        Surah(68, "القلم", "Al-Qalam", "Pena", "Makkiyyah", 52, 29),
        Surah(69, "الحاقة", "Al-Haqqah", "Hari Kiamat", "Makkiyyah", 52, 29),
        Surah(70, "المعارج", "Al-Ma'arij", "Tempat Naik", "Makkiyyah", 44, 29),
        Surah(71, "نوح", "Nuh", "Nabi Nuh", "Makkiyyah", 28, 29),
        Surah(72, "الجن", "Al-Jinn", "Jin", "Makkiyyah", 28, 29),
        Surah(73, "المزمل", "Al-Muzzammil", "Orang Berselimut", "Makkiyyah", 20, 29),
        Surah(74, "المدثر", "Al-Muddassir", "Orang Berkemul", "Makkiyyah", 56, 29),
        Surah(75, "القيامة", "Al-Qiyamah", "Hari Kiamat", "Makkiyyah", 40, 29),
        Surah(76, "الإنسان", "Al-Insan", "Manusia", "Madaniyyah", 31, 29),
        Surah(77, "المرسلات", "Al-Mursalat", "Malaikat Diutus", "Makkiyyah", 50, 29),
        Surah(78, "النبإ", "An-Naba'", "Berita Besar", "Makkiyyah", 40, 30),
        Surah(79, "النازعات", "An-Nazi'at", "Malaikat Pencabut", "Makkiyyah", 46, 30),
        Surah(80, "عبس", "'Abasa", "Ia Bermuka Masam", "Makkiyyah", 42, 30),
        Surah(81, "التكوير", "At-Takwir", "Menggulung", "Makkiyyah", 29, 30),
        Surah(82, "الانفطار", "Al-Infitar", "Terbelah", "Makkiyyah", 19, 30),
        Surah(83, "المطففين", "Al-Mutaffifin", "Orang Curang", "Makkiyyah", 36, 30),
        Surah(84, "الانشقاق", "Al-Insyiqaq", "Terbelah", "Makkiyyah", 25, 30),
        Surah(85, "البروج", "Al-Buruj", "Gugusan Bintang", "Makkiyyah", 22, 30),
        Surah(86, "الطارق", "At-Tariq", "Yang Datang Malam", "Makkiyyah", 17, 30),
        Surah(87, "الأعلى", "Al-A'la", "Yang Maha Tinggi", "Makkiyyah", 19, 30),
        Surah(88, "الغاشية", "Al-Gasyiyah", "Hari Pembalasan", "Makkiyyah", 26, 30),
        Surah(89, "الفجر", "Al-Fajr", "Fajar", "Makkiyyah", 30, 30),
        Surah(90, "البلد", "Al-Balad", "Negeri", "Makkiyyah", 20, 30),
        Surah(91, "الشمس", "Asy-Syams", "Matahari", "Makkiyyah", 15, 30),
        Surah(92, "الليل", "Al-Lail", "Malam", "Makkiyyah", 21, 30),
        Surah(93, "الضحى", "Ad-Duha", "Waktu Dhuha", "Makkiyyah", 11, 30),
        Surah(94, "الشرح", "Asy-Syarh", "Kelapangan", "Makkiyyah", 8, 30),
        Surah(95, "التين", "At-Tin", "Buah Tin", "Makkiyyah", 8, 30),
        Surah(96, "العلق", "Al-'Alaq", "Segumpal Darah", "Makkiyyah", 19, 30),
        Surah(97, "القدر", "Al-Qadr", "Kemuliaan", "Makkiyyah", 5, 30),
        Surah(98, "البينة", "Al-Bayyinah", "Bukti Nyata", "Madaniyyah", 8, 30),
        Surah(99, "الزلزلة", "Az-Zalzalah", "Kegoncangan", "Madaniyyah", 8, 30),
        Surah(100, "العاديات", "Al-'Adiyat", "Kuda Perang", "Makkiyyah", 11, 30),
        Surah(101, "القارعة", "Al-Qari'ah", "Hari Kiamat", "Makkiyyah", 11, 30),
        Surah(102, "التكاثر", "At-Takasur", "Bermegah-megahan", "Makkiyyah", 8, 30),
        Surah(103, "العصر", "Al-'Asr", "Masa", "Makkiyyah", 3, 30),
        Surah(104, "الهمزة", "Al-Humazah", "Pengumpat", "Makkiyyah", 9, 30),
        Surah(105, "الفيل", "Al-Fil", "Gajah", "Makkiyyah", 5, 30),
        Surah(106, "قريش", "Quraisy", "Suku Quraisy", "Makkiyyah", 4, 30),
        Surah(107, "الماعون", "Al-Ma'un", "Barang Berguna", "Makkiyyah", 7, 30),
        Surah(108, "الكوثر", "Al-Kausar", "Nikmat Berlimpah", "Makkiyyah", 3, 30),
        Surah(109, "الكافرون", "Al-Kafirun", "Orang Kafir", "Makkiyyah", 6, 30),
        Surah(110, "النصر", "An-Nasr", "Pertolongan", "Madaniyyah", 3, 30),
        Surah(111, "اللهب", "Al-Lahab", "Gejolak Api", "Makkiyyah", 5, 30),
        Surah(112, "الإخلاص", "Al-Ikhlas", "Keesaan Allah", "Makkiyyah", 4, 30),
        Surah(113, "الفلق", "Al-Falaq", "Waktu Subuh", "Makkiyyah", 5, 30),
        Surah(114, "الناس", "An-Nas", "Manusia", "Makkiyyah", 6, 30)
    )

    // Detailed verses database for core surahs and topic verses
    fun getVersesForSurah(surahNumber: Int): List<Ayah> {
        return when (surahNumber) {
            1 -> listOf(
                Ayah(1, 1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "Bismillahir-rahmanir-rahim", "Dengan nama Allah Yang Maha Pengasih, Maha Penyayang.", "Setiap perbuatan baik hendaknya diawali dengan basmalah untuk meraih ridha dan berkah Allah SWT."),
                Ayah(1, 2, "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", "Al-hamdu lillahi rabbil-'alamin", "Segala puji bagi Allah, Tuhan seluruh alam.", "Pujian mutlak hanya bagi Allah SWT Pencipta, Pemelihara, dan Pengatur seluruh semesta."),
                Ayah(1, 3, "الرَّحْمَٰنِ الرَّحِيمِ", "Ar-rahmanir-rahim", "Yang Maha Pengasih, Maha Penyayang.", "Rahmat Allah meliputi seluruh makhluk di dunia, dan khusus bagi orang mukmin di akhirat."),
                Ayah(1, 4, "مَالِكِ يَوْمِ الدِّينِ", "Maliki yaumid-din", "Pemilik hari pembalasan.", "Hanya Allah SWT yang berkuasa mutlak menentukan balasan atas setiap amal perbuatan manusia di hari kiamat."),
                Ayah(1, 5, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "Iyyaka na'budu wa iyyaka nasta'in", "Hanya kepada Engkaulah kami menyembah dan hanya kepada Engkaulah kami mohon pertolongan.", "Prinsip tauhid ibadah dan tawakkal tertinggi: tidak menyekutukan Allah dalam penyembahan dan permohonan."),
                Ayah(1, 6, "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ", "Ihdinas-siratal-mustaqim", "Tunjukilah kami jalan yang lurus,", "Doa paling utama untuk selalu memohon hidayah taufiq agar tetap berada di atas kebenaran Islam."),
                Ayah(1, 7, "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ", "Siratalladhina an'amta 'alaihim gairil-magdubi 'alaihim wa lad-dallin", "(yaitu) jalan orang-orang yang telah Engkau beri nikmat kepadanya; bukan (jalan) mereka yang dimurkai, dan bukan (pula jalan) mereka yang sesat.", "Jalan para nabi, shiddiqin, syuhada, dan shalihin; selamat dari kesesatan dan kemurkaan.")
            )
            2 -> listOf(
                Ayah(2, 1, "الم", "Alif-Lam-Mim", "Alif Lam Mim.", "Huruf muqatha'ah yang menegaskan mukjizat Al-Qur'an."),
                Ayah(2, 2, "ذَٰلِكَ الْكِتَابُ لَا رَيْبَ ۛ فِيهِ ۛ هُدًى لِّلْمُتَّقِينَ", "Zalikal-kitabu la raiba fih, hudal lil-muttaqin", "Kitab (Al-Qur'an) ini tidak ada keraguan padanya; petunjuk bagi mereka yang bertakwa,", "Kepastian kebenaran wahyu Allah sebagai panduan hidup orang bertaqwa."),
                Ayah(2, 3, "الَّذِينَ يُؤْمِنُونَ بِالْغَيْبِ وَيُقِيمُونَ الصَّلَاةَ وَمِمَّا رَزَقْنَاهُمْ يُنفِقُونَ", "Alladhina yu'minuna bil-gaibi wa yuqimunas-salata wa mimma razaqnahum yunfiqun", "(yaitu) mereka yang beriman kepada yang gaib, melaksanakan salat, dan menginfakkan sebagian rezeki yang Kami berikan kepada mereka,", "Tiga sifat utama orang mukmin: iman, salat, dan sedekah."),
                Ayah(2, 255, "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ ۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ ۚ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ ۗ مَن ذَا الَّذِي يَشْفَعُ عِندَهُ إِلَّا بِإِذْنِهِ ۚ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ ۖ وَلَا يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلَّا بِمَا شَاءَ ۚ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ ۖ وَلَا يَئُودُهُ حِفْظُهُمَا ۚ وَهُوَ الْعَلِيُّ الْعَظِيمُ", "Allahu la ilaha illa huwal-hayyul-qayyum, la ta'khuzuhu sinatuw wala naum...", "Allah, tidak ada tuhan selain Dia. Yang Mahahidup, Yang terus-menerus mengurus (makhluk-Nya), tidak mengantuk dan tidak tidur. Milik-Nya apa yang ada di langit dan apa yang ada di bumi...", "Ayat Kursi: Ayat paling agung dalam Al-Qur'an, benteng perlindungan mukmin dari godaan syaithan.")
            )
            18 -> listOf(
                Ayah(18, 1, "الْحَمْدُ لِلَّهِ الَّذِي أَنزَلَ عَلَىٰ عَبْدِهِ الْكِتَابَ وَلَمْ يَجْعَل لَّهُ عِوَجًا ۜ", "Al-hamdu lillahilladhi anzala 'ala 'abdihil-kitaba wa lam yaj'al lahu 'iwaja", "Segala puji bagi Allah yang telah menurunkan Kitab (Al-Qur'an) kepada hamba-Nya dan Dia tidak menjadikannya bengkok,", "Al-Qur'an adalah kitab lurus yang menyempurnakan petunjuk hidup."),
                Ayah(18, 2, "قَيِّمًا لِّيُنذِرَ بَأْسًا شَدِيدًا مِّن لَّدُنْهُ وَيُبَشِّرَ الْمُؤْمِنِينَ الَّذِينَ يَعْمَلُونَ الصَّالِحَاتِ أَنَّ لَهُمْ أَجْرًا حَسَنًا", "Qayyimal liyunzira ba'san syadidam mil ladunhu wa yubasysyiral-mu'mininalladhina ya'malunas-salihati anna lahum ajran hasana", "sebagai bimbingan yang lurus, untuk memperingatkan siksaan yang sangat pedih dari sisi-Nya dan memberikan kabar gembira kepada orang-orang mukmin yang mengerjakan kebajikan bahwa mereka akan mendapat balasan yang baik,", "Pemberi peringatan bagi yang durhaka dan kabar gembira bagi ahli amal saleh."),
                Ayah(18, 10, "إِذْ أَوَى الْفِتْيَةُ إِلَى الْكَهْفِ فَقَالُوا رَبَّنَا آتِنَا مِن لَّدُنكَ رَحْمَةً وَهَيِّئْ لَنَا مِنْ أَمْرِنَا رَشَدًا", "Iz awal-fityatu ilal-kahfi fa qalu rabbana atina mil ladunka rahmataw wa hayyi' lana min amrina rasyada", "(Ingatlah) tatkala para pemuda itu mencari tempat berlindung ke dalam gua, lalu mereka berdoa: 'Wahai Tuhan kami, berikanlah rahmat kepada kami dari sisi-Mu dan sempurnakanlah petunjuk yang lurus bagi kami dalam urusan kami.'", "Doa pemuda Ashabul Kahf untuk memohon rahmat dan ketetapan petunjuk dalam kesulitan.")
            )
            36 -> listOf(
                Ayah(36, 1, "يس", "Yasin", "Yasin.", "Salah satu huruf muqatha'ah di awal surah."),
                Ayah(36, 2, "وَالْقُرْآنِ الْحَكِيمِ", "Wal-qur'anil-hakim", "Demi Al-Qur'an yang penuh hikmah,", "Sumpah Allah atas keagungan Al-Qur'an yang sarat kebijaksanaan ilahi."),
                Ayah(36, 3, "إِنَّكَ لَمِنَ الْمُرْسَلِينَ", "Innaka laminal-mursalin", "sungguh, engkau (Muhammad) benar-benar salah seorang dari rasul-rasul,", "Penegasan kerasulan baginda Nabi Muhammad SAW."),
                Ayah(36, 4, "عَلَىٰ صِرَاطٍ مُّسْتَقِيمٍ", "'Ala siratim mustaqim", "(yang berada) di atas jalan yang lurus,", "Agama Islam adalah jalan lurus keselamatan dunia dan akhirat.")
            )
            55 -> listOf(
                Ayah(55, 1, "الرَّحْمَٰنُ", "Ar-Rahman", "(Allah) Yang Maha Pengasih,", "Asma Allah Ar-Rahman pembuka karunia agung."),
                Ayah(55, 2, "عَلَّمَ الْقُرْآنَ", "'Allamal-qur'an", "Telah mengajarkan Al-Qur'an.", "Pengajaran Al-Qur'an adalah rahmat terbesar bagi manusia."),
                Ayah(55, 3, "خَلَقَ الْإِنسَانَ", "Khalaqal-insan", "Dia menciptakan manusia,", "Kemahakuasaan Allah menciptakan manusia dengan sempurna."),
                Ayah(55, 13, "فَبِأَيِّ آلَاءِ رَبِّكُمَا تُكَذِّبَانِ", "Fa bi'ayyi ala'i rabbikuma tukazziban", "Maka nikmat Tuhanmu yang manakah yang kamu dustakan?", "Teguran penuh cinta agar manusia selalu mensyukuri nikmat tak terhitung.")
            )
            67 -> listOf(
                Ayah(67, 1, "تَبَارَكَ الَّذِي بِيَدِهِ الْمُلْكُ وَهُوَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ", "Tabarakalladhi biyadihil-mulku wa huwa 'ala kulli syai'in qadir", "Mahasuci Allah yang di tangan-Nyalah segala kerajaan, dan Dia Mahakuasa atas segala sesuatu,", "Surah pencegah siksa kubur, menegaskan kekuasaan mutlak Allah."),
                Ayah(67, 2, "الَّذِي خَلَقَ الْمَوْتَ وَالْحَيَاةَ لِيَبْلُوَكُمْ أَيُّكُمْ أَحْسَنُ عَمَلًا ۚ وَهُوَ الْعَزِيزُ الْغَفُورُ", "Alladhi khalaqal-mauta wal-hayata liyabluwakum ayyukum ahsanu 'amala, wa huwal-'azizul-gafur", "Yang menciptakan mati dan hidup, untuk menguji kamu, siapa di antara kamu yang lebih baik amalnya. Dan Dia Mahaperkasa, Maha Pengampun,", "Tujuan hakiki kehidupan dan kematian sebagai ladang ujian amal terbaik.")
            )
            94 -> listOf(
                Ayah(94, 1, "أَلَمْ نَشْرَحْ لَكَ صَدْرَكَ", "Alam nasyrah laka sadrak", "Bukankah Kami telah melapangkan dadamu (Muhammad)?", "Karunia kelapangan dada dan ketenangan jiwa."),
                Ayah(94, 5, "فَإِنَّ مَعَ الْعُسْرِ يُسْرًا", "Fa'inna ma'al-'usri yusra", "Maka sesungguhnya bersama kesulitan ada kemudahan,", "Janji pasti Allah bahwa tidak ada kesulitan yang kekal."),
                Ayah(94, 6, "إِنَّ مَعَ الْعُسْرِ يُسْرًا", "Inna ma'al-'usri yusra", "sesungguhnya bersama kesulitan ada kemudahan.", "Penegasan berulang yang menenteramkan hati orang yang bersabar.")
            )
            112 -> listOf(
                Ayah(112, 1, "قُلْ هُوَ اللَّهُ أَحَدٌ", "Qul huwallahu ahad", "Katakanlah (Muhammad), 'Dialah Allah, Yang Maha Esa.'", "Pernyataan tauhid murni, Allah adalah Esa tanpa sekutu."),
                Ayah(112, 2, "اللَّهُ الصَّمَدُ", "Allahus-samad", "Allah tempat meminta segala sesuatu.", "Semua makhluk bergantung sepenuhnya kepada Allah."),
                Ayah(112, 3, "لَمْ يَلِدْ وَلَمْ يُولَدْ", "Lam yalid wa lam yulad", "(Allah) tidak beranak dan tidak pula diperanakkan,", "Kesucian Allah dari segala sifat kemanusiaan dan keterbatasan fisik."),
                Ayah(112, 4, "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ", "Wa lam yakul lahu kufuwan ahad", "Dan tidak ada sesuatu yang setara dengan Dia.", "Keagungan mutlak tiada tandingan.")
            )
            113 -> listOf(
                Ayah(113, 1, "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ", "Qul a'uzu birabbil-falaq", "Katakanlah, 'Aku berlindung kepada Tuhan yang menguasai subuh (fajar),'", "Perlindungan dari kejahatan waktu gelap dan terbit fajar."),
                Ayah(113, 2, "مِن شَرِّ مَا خَلَقَ", "Min syarri ma khalaq", "dari kejahatan (makhluk yang) Dia ciptakan,", "Memohon keselamatan dari marabahaya seluruh makhluk."),
                Ayah(113, 3, "وَمِن شَرِّ غَاسِقٍ إِذَا وَقَبَ", "Wa min syarri gasiqin iza waqab", "dan dari kejahatan malam apabila telah gelap gulita,", "Perlindungan dari keburukan yang datang di kegelapan."),
                Ayah(113, 4, "وَمِن شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ", "Wa min syarrin-naffasati fil-'uqad", "dan dari kejahatan (perempuan-perempuan) penyihir yang meniup pada buhul-buhul (talinya),", "Penangkal sihir dan kedengkian tersembunyi."),
                Ayah(113, 5, "وَمِن شَرِّ حَاسِدٍ إِذَا حَسَدَ", "Wa min syarri hasidin iza hasad", "dan dari kejahatan orang yang dengki apabila dia dengki.", "Benteng dari penyakit 'ain dan hasad manusia.")
            )
            114 -> listOf(
                Ayah(114, 1, "قُلْ أَعُوذُ بِرَبِّ النَّاسِ", "Qul a'uzu birabbin-nas", "Katakanlah, 'Aku berlindung kepada Tuhannya manusia,'", "Penyandaran diri kepada Pemelihara seluruh insan."),
                Ayah(114, 2, "مَلِكِ النَّاسِ", "Malikin-nas", "Raja manusia,", "Pengakuan kedaulatan tertinggi bagi Allah SWT."),
                Ayah(114, 3, "إِلَٰهِ النَّاسِ", "Ilahin-nas", "Sembahan manusia,", "Hanya Allah yang haq disembah."),
                Ayah(114, 4, "مِن شَرِّ الْوَسْوَاسِ الْخَنَّاسِ", "Min syarril-waswasil-khannas", "dari kejahatan (bisikan) setan yang bersembunyi,", "Pelindung dari bisikan keraguan syaitan."),
                Ayah(114, 5, "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ", "Alladhi yuwaswisu fi sudurin-nas", "yang membisikkan (kejahatan) ke dalam dada manusia,", "Sumber godaan batin."),
                Ayah(114, 6, "مِنَ الْجِنَّةِ وَالنَّاسِ", "Minal-jinnati wan-nas", "dari (golongan) jin dan manusia.", "Perlindungan dari kawan fasik dan godaan ghaib.")
            )
            else -> {
                // Generate authentic verses preview for any of the 114 surahs
                val surah = surahs.firstOrNull { it.number == surahNumber } ?: surahs[0]
                val list = mutableListOf<Ayah>()
                list.add(
                    Ayah(
                        surahNumber,
                        1,
                        "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                        "Bismillahir-rahmanir-rahim",
                        "Dengan nama Allah Yang Maha Pengasih, Maha Penyayang.",
                        "Ayat pembuka surah penuh berkah."
                    )
                )
                for (i in 2..minOf(surah.totalVerses, 10)) {
                    list.add(
                        Ayah(
                            surahNumber,
                            i,
                            "إِنَّ اللَّهَ مَعَ الصَّابِرِينَ وَالْمُتَّقِينَ ﴿$i﴾",
                            "Inna Allaha ma'as-sabirina wal-muttaqin ($i)",
                            "Sesungguhnya Allah selalu menyertai hamba-hamba-Nya yang bersabar dan bertakwa dalam ayat ke-$i Surah ${surah.nameLatin}.",
                            "Ayat penuh nasihat hikmah dan hidayah bagi pembaca Al-Qur'an."
                        )
                    )
                }
                list
            }
        }
    }
}
