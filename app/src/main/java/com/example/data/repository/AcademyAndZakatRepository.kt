package com.example.data.repository

import com.example.data.model.AcademyLesson
import com.example.data.model.QuizQuestion

object AcademyAndZakatRepository {

    val academyLessons = listOf(
        AcademyLesson(
            id = "tajwid_nun_mati_idzhar",
            title = "Idzhar Halqi (إظهار حلقي)",
            category = "Tajwid",
            summary = "Membaca huruf nun sukun atau tanwin dengan jelas dan tegas tanpa dengung saat bertemu salah satu dari 6 huruf tenggorokan (halq).",
            content = "Secara bahasa, Idzhar artinya jelas atau terang. Huruf Idzhar Halqi ada 6: Hamzah (ء), Ha (هـ), 'Ain (ع), Haa (ح), Ghain (غ), Kha (خ). Ketika nun sukun (نْ) atau tanwin bertemu salah satu huruf ini, wajib dibaca jelas dari makhrajnya tanpa dipanjangkan atau didengungkan.",
            exampleArabic = "مَنْ ءَامَنَ - أَنْعَمْتَ - كُفُوًا أَحَدٌ",
            exampleLatin = "Man aamana - An'amta - Kufuwan ahad"
        ),
        AcademyLesson(
            id = "tajwid_idgham_bighunnah",
            title = "Idgham Bighunnah (إدغام بغنة)",
            category = "Tajwid",
            summary = "Memasukkan bunyi nun sukun atau tanwin ke huruf berikutnya disertai dengungan (ghunnah) 2 harakat.",
            content = "Idgham artinya memasukkan, sedangkan Bighunnah artinya disertai dengung. Hurufnya ada 4 yang terangkum dalam kata يَنْمُو (Yanmu): Ya (ي), Nun (ن), Mim (م), Waw (و). Syaratnya pertemuan terjadi pada dua kata terpisah.",
            exampleArabic = "مَن يَقُولُ - مِن نَّعْمَةٍ - هُدًى وَرَحْمَةً",
            exampleLatin = "May yaquulu - Min na'mah - Hudaw wa rahmah"
        ),
        AcademyLesson(
            id = "tajwid_iqlab",
            title = "Iqlab (إقلاب)",
            category = "Tajwid",
            summary = "Mengganti bunyi nun sukun atau tanwin menjadi suara mim (م) yang samar disertai dengungan saat bertemu huruf Ba (ب).",
            content = "Iqlab artinya mengubah atau membalik. Huruf Iqlab hanya satu, yaitu Ba (ب). Saat nun mati atau tanwin bertemu Ba, bibir dirapatkan perlahan menyuarakan mim samar dengan ghunnah 2 harakat.",
            exampleArabic = "مِن بَعْدِ - أَنۢبِئْهُم - عَلِيمٌ بِذَاتِ الصُّدُورِ",
            exampleLatin = "Mim ba'di - Ambi'hum - 'Aliimum bidzaatish-shuduur"
        ),
        AcademyLesson(
            id = "tajwid_ikhfa_haqiqi",
            title = "Ikhfa Haqiqi (إخفاء حقيقي)",
            category = "Tajwid",
            summary = "Menyamarkan suara nun sukun atau tanwin antara idzhar dan idgham disertai dengung saat bertemu 15 huruf hijaiyah.",
            content = "Ikhfa artinya menyamarkan atau menyembunyikan. Hurufnya berjumlah 15: ت، ث، ج، د، ذ، ز، س، ش، ص، ض، ط، ظ، ف، ق، ك. Bunyi nun disamarkan menuju makhraj huruf yang ada di depannya.",
            exampleArabic = "مِن قَبْلُ - كُنتُمْ - عَذَابًا قَرِيبًا",
            exampleLatin = "Ming qablu - Kuntum - 'Adzaaban qariiba"
        ),
        AcademyLesson(
            id = "fikih_syarat_sah_salat",
            title = "Syarat Sah Salat & Rukun Salat",
            category = "Fikih",
            summary = "Hal mendasar yang wajib dipenuhi sebelum dan selama melaksanakan ibadah salat agar sah di sisi syariat.",
            content = "Syarat Sah: (1) Suci dari hadas kecil dan besar, (2) Suci badan, pakaian, dan tempat dari najis, (3) Menutup aurat, (4) Masuk waktu salat, (5) Menghadap kiblat. Rukun Salat meliputi niat, takbiratul ihram, berdiri bagi yang mampu, membaca Al-Fatihah, ruku' tuma'ninah, i'tidal tuma'ninah, sujud dua kali tuma'ninah, duduk antara dua sujud, tasyahud akhir, shalawat, salam, dan tertib.",
            exampleArabic = "إِنَّ الصَّلَاةَ كَانَتْ عَلَى الْمُؤْمِنِينَ كِتَابًا مَّوْقُوتًا",
            exampleLatin = "Innash-shalaata kaanat 'alal-mu'miniina kitaabam mawquutaa (QS. An-Nisa: 103)"
        ),
        AcademyLesson(
            id = "sirah_nabi_muhammad",
            title = "Karakter Agung Rasulullah SAW",
            category = "Sirah",
            summary = "Meneladani empat sifat utama Nabi Muhammad: As-Siddiq, Al-Amanah, At-Tabligh, dan Al-Fathanah.",
            content = "Rasulullah SAW diutus menyempurnakan akhlak mulia. Sebelum diangkat menjadi Nabi, beliau digelari Al-Amin (orang yang terpercaya) oleh penduduk Makkah karena kejujuran dan kemuliaan budi pekertinya.",
            exampleArabic = "وَإِنَّكَ لَعَلَىٰ خُلُقٍ عَظِيمٍ",
            exampleLatin = "Wa innaka la'alaa khuluqin 'azhiim (QS. Al-Qalam: 4)"
        )
    )

    val quizQuestions = listOf(
        QuizQuestion(
            id = 1,
            question = "Berapakah jumlah surah yang ada di dalam kitab suci Al-Qur'an?",
            options = listOf("112 Surah", "114 Surah", "116 Surah", "120 Surah"),
            correctIndex = 1,
            explanation = "Al-Qur'anul Karim terdiri dari 114 surah, terbagi menjadi 30 juz, diawali dari Surah Al-Fatihah dan diakhiri Surah An-Nas."
        ),
        QuizQuestion(
            id = 2,
            question = "Ketika nun sukun (نْ) bertemu dengan huruf Ba (ب), hukum bacaan tajwidnya adalah...",
            options = listOf("Idzhar Halqi", "Idgham Bighunnah", "Iqlab", "Ikhfa Haqiqi"),
            correctIndex = 2,
            explanation = "Hukum bacaan ketika nun mati atau tanwin bertemu huruf Ba adalah Iqlab, dibaca dengan mengubah bunyi nun menjadi mim samar disertai ghunnah."
        ),
        QuizQuestion(
            id = 3,
            question = "Berapakah nisab zakat maal (harta simpanan emas) yang telah haul 1 tahun?",
            options = listOf("50 gram emas", "75 gram emas", "85 gram emas", "100 gram emas"),
            correctIndex = 2,
            explanation = "Berdasarkan hadis Rasulullah SAW, nisab zakat emas adalah 20 dinar yang setara dengan 85 gram emas murni, dengan tarif kewajiban 2.5%."
        ),
        QuizQuestion(
            id = 4,
            question = "Salat sunnah dua rakaat yang dilaksanakan setelah terbit fajar hingga menjelang terbit matahari sebelum salat subuh disebut...",
            options = listOf("Salat Tahajud", "Salat Fajar (Qabliyah Subuh)", "Salat Dhuha", "Salat Witir"),
            correctIndex = 1,
            explanation = "Salat dua rakaat fajar (qabliyah subuh) memiliki keutamaan luar biasa: 'lebih baik daripada dunia dan segala isinya' (HR. Muslim)."
        ),
        QuizQuestion(
            id = 5,
            question = "Puasa sunnah yang dilaksanakan pada tanggal 13, 14, dan 15 setiap bulan Hijriah dikenal sebagai puasa...",
            options = listOf("Puasa Daud", "Puasa Syawal", "Puasa Ayyamul Bidh", "Puasa Arafah"),
            correctIndex = 2,
            explanation = "Ayyamul Bidh (hari-hari putih ketika bulan purnama) disunnahkan untuk berpuasa 3 hari setiap bulan, bernilai seperti puasa sepanjang masa."
        )
    )

    // Pre-calculated Sahabat AI authentic knowledge responses
    val aiKnowledgeTopics = listOf(
        "Keutamaan Salat Tahajud" to """Salat Tahajud adalah salat sunnah yang sangat mulia dilaksanakan di sepertiga malam terakhir.
Dalil Al-Qur'an:
"Dan pada sebagian malam, lakukanlah salat tahajud (sebagai suatu ibadah) tambahan bagimu; mudah-mudahan Tuhanmu mengangkatmu ke tempat yang terpuji." (QS. Al-Isra': 79).
Keutamaan:
1. Merupakan salat paling afdhal setelah salat fardhu (HR. Muslim).
2. Waktu ijabah doa ketika Allah turun ke langit dunia menawarkan ampunan dan pengabulan hajat (HR. Bukhari & Muslim).
3. Memberikan ketenangan batin dan cahaya di wajah.""",

        "Doa Saat Hati Sedang Gelisah" to """Ketika hati merasa cemas atau sedih, Rasulullah SAW mengajarkan doa:
"اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْهَمِّ وَالْحَزَنِ، وَالْعَجْزِ وَالْكَسَلِ، وَالْبُخْلِ وَالْجُبْنِ، وَضَلَعِ الدَّيْنِ، وَغَلَبَةِ الرِّجَالِ"
Artinya:
"Ya Allah, sesungguhnya aku berlindung kepada-Mu dari rasa gelisah dan sedih, dari kelemahan dan kemalasan, dari sifat kikir dan pengecut, dari lilitan hutang dan penindasan orang." (HR. Bukhari no. 2893).
Juga perbanyak membaca: "Hasbunallaahu wa ni'mal wakiil" dan istighfar.""",

        "Hukum dan Tata Cara Mengqadha Puasa" to """Bagi yang meninggalkan puasa Ramadhan karena uzur syar'i (sakit, safar, haid, melahirkan), wajib mengqadhanya di hari lain.
Dalil:
"Maka barangsiapa di antara kamu sakit atau dalam perjalanan (lalu tidak berpuasa), maka (wajib mengganti) sebanyak hari yang ditinggalkannya pada hari-hari yang lain." (QS. Al-Baqarah: 184).
Tata Cara:
1. Niat qadha di malam hari sebelum fajar: "Nawaitu shauma ghadin 'an qadha'i fardhi syahri Ramadhaana lillaahi ta'ala".
2. Boleh dicicil terpisah atau berurutan.
3. Dianjurkan disegerakan sebelum datang Ramadhan berikutnya.""",

        "Adab Membaca Al-Qur'an" to """Adab membaca Al-Qur'an yang dianjurkan para ulama:
1. Dalam keadaan suci dari hadas (berwudhu).
2. Menghadap kiblat di tempat yang bersih dan sopan.
3. Mengawali dengan ta'awwudz dan basmalah.
4. Membaca dengan tartil (tidak terburu-buru) sesuai kaidah tajwid.
5. Menghayati (tadabbur) makna ayat-ayat yang dibaca.
6. Memperindah suara tanpa mengubah makhraj huruf (HR. Abu Dawud)."""
    )
}
