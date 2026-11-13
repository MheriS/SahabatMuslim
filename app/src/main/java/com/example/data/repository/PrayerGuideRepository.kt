package com.example.data.repository

import com.example.data.model.PrayerGuide
import com.example.data.model.PrayerStep

object PrayerGuideRepository {

    val guides: List<PrayerGuide> = listOf(
        PrayerGuide(
            id = "salat_subuh",
            name = "Salat Subuh",
            category = "Wajib",
            rakaatCount = 2,
            executionTime = "Mulai terbit fajar shadiq hingga sesaat sebelum terbit matahari",
            niatArabic = "أُصَلِّي فَرْضَ الصُّبْحِ رَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ أَدَاءً لِلَّهِ تَعَالَى",
            niatLatin = "Usholli fardhash-shubhi rak'ataini mustaqbilal-qiblati adaa-an lillaahi Ta'aala.",
            niatTranslation = "Aku niat salat fardhu Subuh dua rakaat menghadap kiblat karena Allah Ta'ala.",
            virtues = "Dua rakaat fajar lebih baik daripada dunia dan seisinya (HR. Muslim). Dijamin dalam perlindungan Allah seharian.",
            steps = listOf(
                PrayerStep(1, "Niat & Takbiratul Ihram", "Berdiri tegak menghadap kiblat, hadirkan niat dalam hati, lalu angkat kedua tangan sejajar telinga/bahu sambil mengucap Takbir.", "اللَّهُ أَكْبَرُ", "Allaahu Akbar", "Allah Mahabesar."),
                PrayerStep(2, "Membaca Doa Iftitah", "Membaca doa pembuka dengan khusyuk setelah takbir.", "اللَّهُمَّ بَاعِدْ بَيْنِي وَبَيْنَ خَطَايَايَ كَمَا بَاعَدْتَ بَيْنَ الْمَشْرِقِ وَالْمَغْرِبِ...", "Allaahumma baa'id bainii wa baina khathaayaaya kamaa baa'adta bainal-masyriqi wal-maghrib...", "Ya Allah jauhkanlah antara diriku dan kesalahan-kesalahanku sebagaimana Engkau menjauhkan antara timur dan barat..."),
                PrayerStep(3, "Membaca Surah Al-Fatihah & Surah Pendek", "Membaca Al-Fatihah yang merupakan rukun salat, dilanjutkan membaca surah pendek dari Al-Qur'an.", "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ ...", "Bismillaahir-rahmaanir-rahiim ...", "Dengan nama Allah Yang Maha Pengasih, Maha Penyayang..."),
                PrayerStep(4, "Ruku' dengan Tuma'ninah", "Membungkukkan badan dengan punggung rata dan tangan memegang lutut.", "سُبْحَانَ رَبِّيَ الْعَظِيمِ وَبِحَمْدِهِ", "Subhaana Rabbiyal-'azhiimi wa bihamdih (3x)", "Mahasuci Tuhanku Yang Mahaagung dan dengan memuji-Nya."),
                PrayerStep(5, "I'tidal & Bangkit dari Ruku'", "Berdiri kembali tegak lurus dengan tuma'ninah.", "سَمِعَ اللَّهُ لِمَنْ حَمِدَهُ، رَبَّنَا وَلَكَ الْحَمْدُ", "Sami'allaahu liman hamidah, Rabbanaa wa lakal-hamd", "Allah mendengar orang yang memuji-Nya, Wahai Tuhan kami, bagi-Mulah segala puji."),
                PrayerStep(6, "Sujud Pertama & Kedua", "Meletakkan dahi, hidung, kedua telapak tangan, kedua lutut, dan ujung jari jemari kaki ke lantai.", "سُبْحَانَ رَبِّيَ الْأَعْلَى وَبِحَمْدِهِ", "Subhaana Rabbiyal-a'laa wa bihamdih (3x)", "Mahasuci Tuhanku Yang Mahatinggi dan dengan memuji-Nya."),
                PrayerStep(7, "Duduk Antara Dua Sujud", "Duduk bersimpuh (iftirasy) dengan tenang.", "رَبِّ اغْفِرْ لِي وَارْحَمْنِي وَاجْبُرْنِي وَارْفَعْنِي وَارْزُقْنِي وَاهْدِنِي وَعَافِنِي وَاعْفُ عَنِّي", "Rabbighfir lii warhamnii wajburnii warfa'nii warzuqnii wahdinii wa 'aafinii wa'fu 'annii", "Wahai Tuhanku ampunilah aku, sayangilah aku, cukupkanlah kekuranganku, tinggikanlah derajatku..."),
                PrayerStep(8, "Tasyahud Akhir & Salam", "Duduk tawarruk pada rakaat kedua, membaca tasyahud dan shalawat ibrahimiyah, lalu menoleh ke kanan dan kiri sambil mengucap salam.", "السَّلَامُ عَلَيْكُمْ وَرَحْمَةُ اللَّهِ", "Assalaamu 'alaikum wa rahmatullaah", "Semoga keselamatan dan rahmat Allah tercurah kepada kalian.")
            ),
            commonMistakes = listOf(
                "Tergesa-gesa tanpa tuma'ninah (tidak diam sejenak di setiap rukun).",
                "Mendahului imam dalam salat berjamaah.",
                "Tidak menempelkan hidung ke lantai saat sujud."
            )
        ),
        PrayerGuide(
            id = "salat_tahajud",
            name = "Salat Tahajud (Qiyamul Lail)",
            category = "Sunnah",
            rakaatCount = 2,
            executionTime = "Sepertiga malam terakhir (sekitar pukul 01.30 - menjelang Subuh) setelah tidur terlebih dahulu",
            niatArabic = "أُصَلِّي سُنَّةَ التَّهَجُّدِ رَكْعَتَيْنِ لِلَّهِ تَعَالَى",
            niatLatin = "Usholli sunnatat-tahajjudi rak'ataini lillaahi Ta'aala.",
            niatTranslation = "Aku niat salat sunnah Tahajud dua rakaat karena Allah Ta'ala.",
            virtues = "Ibadah malam yang mengangkat pelakunya ke tempat terpuji (Maqaman Mahmuda - QS. Al-Isra: 79) dan waktu doa paling mustajab.",
            steps = listOf(
                PrayerStep(1, "Niat dan Berdiri", "Niat dalam hati untuk bertahajud semata mengharap ridha Allah.", "أُصَلِّي سُنَّةَ التَّهَجُّدِ ...", "Usholli sunnatat-tahajjud...", "Niat salat sunnah tahajud."),
                PrayerStep(2, "Takbiratul Ihram & Rakaat Pertama", "Membaca Al-Fatihah dan surah yang dihafal secara tartil dan khusyuk.", "بِسْمِ اللَّهِ ...", "Bismillahir-rahmanir-rahim...", "Al-Fatihah dan surah pilihan."),
                PrayerStep(3, "Ruku' dan Sujud Panjang", "Memperpanjang ruku' dan sujud untuk memperbanyak doa dan pengagungan kepada Allah.", "سُبْحَانَ رَبِّيَ الْأَعْلَى ...", "Subhaana Rabbiyal A'laa...", "Tasbih dan munajat sujud."),
                PrayerStep(4, "Rakaat Kedua & Salam", "Mengulang gerakan rakaat pertama, membaca tasyahud akhir, diakhiri dengan salam.", "السَّلَامُ عَلَيْكُمْ وَرَحْمَةُ اللَّهِ", "Assalaamu 'alaikum...", "Salam penutup salat.")
            ),
            commonMistakes = listOf(
                "Memaksakan rakaat sangat banyak hingga kelelahan dan meninggalkan Subuh berjamaah.",
                "Tidak menutup rangkaian salat malam dengan witir."
            )
        ),
        PrayerGuide(
            id = "salat_dhuha",
            name = "Salat Dhuha",
            category = "Sunnah",
            rakaatCount = 2,
            executionTime = "Mulai matahari naik setinggi tombak (sekitar 20 menit setelah syuruq) hingga menjelang waktu Dzuhur",
            niatArabic = "أُصَلِّي سُنَّةَ الضُّحَى رَكْعَتَيْنِ لِلَّهِ تَعَالَى",
            niatLatin = "Usholli sunnatadh-dhuhaa rak'ataini lillaahi Ta'aala.",
            niatTranslation = "Aku niat salat sunnah Dhuha dua rakaat karena Allah Ta'ala.",
            virtues = "Pengganti sedekah bagi 360 persendian tubuh manusia, pembuka pintu kelapangan rezeki dan barakah (HR. Muslim).",
            steps = listOf(
                PrayerStep(1, "Niat & Takbiratul Ihram", "Mengawali salat dengan niat salat dhuha dua rakaat.", "اللَّهُ أَكْبَرُ", "Allaahu Akbar", "Allah Mahabesar."),
                PrayerStep(2, "Rakaat Pertama (Surah Asy-Syams / Al-Kafirun)", "Setelah Al-Fatihah, dianjurkan membaca Surah Asy-Syams atau surah lainnya.", "وَالشَّمْسِ وَضُحَاهَا ...", "Wasy-syamsi wa dhuhaahaa...", "Demi matahari dan sinarnya pada pagi hari..."),
                PrayerStep(3, "Rakaat Kedua (Surah Ad-Dhuha / Al-Ikhlas)", "Setelah Al-Fatihah, membaca Surah Ad-Dhuha atau surah lainnya.", "وَالضُّحَىٰ وَاللَّيْلِ إِذَا سَجَىٰ ...", "Wadh-dhuhaa wal-laili idzaa sajaa...", "Demi waktu dhuha dan demi malam apabila telah sunyi..."),
                PrayerStep(4, "Tasyahud & Doa Dhuha", "Setelah salam membaca doa khusus dhuha memohon kelapangan rezeki berkah.", "اللَّهُمَّ إِنَّ الضُّحَاءَ ضُحَاؤُكَ ...", "Allaahumma innadh-dhuhaa-a dhuhaa-uka...", "Ya Allah, sesungguhnya waktu dhuha adalah waktu dhuha-Mu...")
            ),
            commonMistakes = listOf(
                "Melakukan salat tepat saat matahari terbit (waktu terlarang salat sebelum syuruq sempurna)."
            )
        ),
        PrayerGuide(
            id = "salat_witir",
            name = "Salat Witir",
            category = "Sunnah",
            rakaatCount = 3,
            executionTime = "Setelah salat Isya hingga menjelang fajar Subuh",
            niatArabic = "أُصَلِّي سُنَّةَ الْوِتْرِ ثَلَاثَ رَكَعَاتٍ لِلَّهِ تَعَالَى",
            niatLatin = "Usholli sunnatal-witri tsalaatsa raka'aatin lillaahi Ta'aala.",
            niatTranslation = "Aku niat salat sunnah Witir tiga rakaat karena Allah Ta'ala.",
            virtues = "Penutup ibadah salat malam; sesungguhnya Allah itu Maha Ganjil (Esa) dan mencintai yang ganjil (HR. Abu Dawud).",
            steps = listOf(
                PrayerStep(1, "Niat Witir", "Bisa dilaksanakan 3 rakaat sekaligus dengan satu salam atau 2 rakaat salam lalu 1 rakaat.", "اللَّهُ أَكْبَرُ", "Allaahu Akbar", "Takbiratul Ihram."),
                PrayerStep(2, "Surah Rakaat (Al-A'la, Al-Kafirun, Al-Ikhlas)", "Rakaat 1: Surah Al-A'la. Rakaat 2: Surah Al-Kafirun. Rakaat 3: Surah Al-Ikhlas, Al-Falaq, An-Nas.", "سَبِّحِ اسْمَ رَبِّكَ الْأَعْلَى", "Sabbihisma rabbikal-a'laa", "Sucikanlah nama Tuhanmu Yang Mahatinggi."),
                PrayerStep(3, "Doa Setelah Witir", "Membaca tasbih 3 kali setelah salam.", "سُبْحَانَ الْمَلِكِ الْقُدُّوسِ (٣x)", "Subhaanal-Malikil-Qudduus (3x)", "Mahasuci Raja Yang Mahasuci.")
            ),
            commonMistakes = listOf(
                "Melakukan dua kali witir dalam satu malam."
            )
        )
    )
}
