package com.example.data.repository

import com.example.data.model.DoaItem

object DoaZikirRepository {

    val categories = listOf(
        "Semua",
        "Harian",
        "Pagi & Petang",
        "Setelah Salat",
        "Pengampunan",
        "Perlindungan",
        "Orang Tua",
        "Al-Qur'an"
    )

    val doaList: List<DoaItem> = listOf(
        DoaItem(
            id = "doa_bangun_tidur",
            title = "Doa Bangun Tidur",
            category = "Harian",
            arabicText = "الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ",
            transliteration = "Alhamdulillaahil-ladzii ahyaanaa ba'da maa amaatanaa wa ilaihin-nusyuur.",
            translation = "Segala puji bagi Allah yang telah menghidupkan kami setelah mematikan kami (tidur), dan hanya kepada-Nya kami akan dibangkitkan.",
            reference = "HR. Bukhari no. 6312 dan Muslim no. 2711",
            repetitionCount = 1,
            benefits = "Mensyukuri nikmat kehidupan baru dan mengingat hari kebangkitan."
        ),
        DoaItem(
            id = "doa_sebelum_tidur",
            title = "Doa Sebelum Tidur",
            category = "Harian",
            arabicText = "بِاسْمِكَ اللَّهُمَّ أَحْيَا وَأَمُوتُ",
            transliteration = "Bismikallaahumma ahyaa wa amuwt.",
            translation = "Dengan nama-Mu ya Allah, aku hidup dan aku mati.",
            reference = "HR. Bukhari no. 6324 dan Muslim no. 2711",
            repetitionCount = 1,
            benefits = "Menyerahkan jiwa raga dalam perlindungan Allah selama istirahat malam."
        ),
        DoaItem(
            id = "doa_kedua_orang_tua",
            title = "Doa untuk Kedua Orang Tua",
            category = "Orang Tua",
            arabicText = "رَبِّ اغْفِرْ لِي وَلِوَالِدَيَّ وَارْحَمْهُمَا كَمَا رَبَّيَانِي صَغِيرًا",
            transliteration = "Rabbighfir lii wa liwaalidayya warhamhumaa kamaa rabbayaanii shaghiiraa.",
            translation = "Wahai Tuhanku, ampunilah aku dan kedua orang tuaku, serta sayangilah mereka berdua sebagaimana mereka telah mendidikku di waktu kecil.",
            reference = "QS. Al-Isra': 24",
            repetitionCount = 3,
            benefits = "Bentuk bakti tertinggi (birrul walidain) yang mengalirkan rahmat bagi orang tua."
        ),
        DoaItem(
            id = "doa_kebaikan_dunia_akhirat",
            title = "Doa Sapu Jagat (Kebaikan Dunia & Akhirat)",
            category = "Al-Qur'an",
            arabicText = "رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الْآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّارِ",
            transliteration = "Rabbanaa aatinaa fid-dunyaa hasanah wa fil-aakhirati hasanah wa qinaa 'adzaaban-naar.",
            translation = "Wahai Tuhan kami, berikanlah kepada kami kebaikan di dunia dan kebaikan di akhirat, dan lindungilah kami dari siksa neraka.",
            reference = "QS. Al-Baqarah: 201 & HR. Bukhari",
            repetitionCount = 3,
            benefits = "Doa yang paling sering dibaca oleh Rasulullah SAW karena mencakup seluruh kebaikan hidup."
        ),
        DoaItem(
            id = "sayyidul_istighfar",
            title = "Sayyidul Istighfar (Penghulu Istighfar)",
            category = "Pengampunan",
            arabicText = "اللَّهُمَّ أَنْتَ رَبِّي لَا إِلَهَ إِلَّا أَنْتَ خَلَقْتَنِي وَأَنَا عَبْدُكَ وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ، أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ، أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ وَأَبُوءُ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ",
            transliteration = "Allaahumma anta Rabbii laa ilaaha illaa anta khalaqtanii wa anaa 'abduka wa anaa 'alaa 'ahdika wa wa'dika mastatha'tu...",
            translation = "Ya Allah, Engkaulah Tuhanku, tiada tuhan selain Engkau. Engkau yang menciptakanku dan aku adalah hamba-Mu. Aku senantiasa setia pada perjanjian-Mu dan janji-Mu semampuku. Aku berlindung kepada-Mu dari keburukan yang telah kuperbuat...",
            reference = "HR. Bukhari no. 6306",
            repetitionCount = 1,
            benefits = "Siapa yang membacanya di sore hari lalu meninggal malam itu, ia masuk surga, begitupula saat pagi hari."
        ),
        DoaItem(
            id = "doa_keluar_rumah",
            title = "Doa Keluar Rumah",
            category = "Harian",
            arabicText = "بِسْمِ اللَّهِ تَوَكَّلْتُ عَلَى اللَّهِ، لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ",
            transliteration = "Bismillaahi tawakkaltu 'alallaahi, laa hawla wa laa quwwata illaa billaah.",
            translation = "Dengan nama Allah, aku bertawakal kepada Allah. Tiada daya dan kekuatan kecuali dengan pertolongan Allah.",
            reference = "HR. Abu Dawud no. 5095 dan Tirmidzi no. 3426",
            repetitionCount = 1,
            benefits = "Malaikat akan berkata: 'Engkau telah dicukupi, dilindungi, dan diberi petunjuk', serta setan menjauh."
        ),
        DoaItem(
            id = "doa_masuk_rumah",
            title = "Doa Masuk Rumah",
            category = "Harian",
            arabicText = "بِسْمِ اللَّهِ وَلَجْنَا، وَبِسْمِ اللَّهِ خَرَجْنَا، وَعَلَى رَبِّنَا تَوَكَّلْنَا",
            transliteration = "Bismillaahi walajnaa, wa bismillaahi kharajnaa, wa 'alaa Rabbinaa tawakkalnaa.",
            translation = "Dengan nama Allah kami masuk, dan dengan nama Allah kami keluar, dan kepada Tuhan kami, kami bertawakal.",
            reference = "HR. Abu Dawud no. 5096",
            repetitionCount = 1,
            benefits = "Menjaga rumah dari gangguan setan yang ingin ikut bermalam."
        ),
        DoaItem(
            id = "doa_menghadapi_kesulitan",
            title = "Doa Menghadapi Kesulitan & Kegalauan",
            category = "Perlindungan",
            arabicText = "اللَّهُمَّ لَا سَهْلَ إِلَّا مَا جَعَلْتَهُ سَهْلًا، وَأَنْتَ تَجْعَلُ الْحَزْنَ إِذَا شِئْتَ سَهْلًا",
            transliteration = "Allaahumma laa sahla illaa maa ja'altahu sahlaa, wa anta taj'alul-hazna idzaa syi'ta sahlaa.",
            translation = "Ya Allah, tidak ada kemudahan kecuali apa yang Engkau jadikan mudah. Dan Engkaulah yang menjadikan kesedihan/kesulitan, jika Engkau kehendaki, menjadi mudah.",
            reference = "HR. Ibnu Hibban no. 327 (Shahih)",
            repetitionCount = 3,
            benefits = "Menenangkan batin dan membuka jalan keluar bagi ujian yang berat."
        ),
        DoaItem(
            id = "zikir_pagi_perlindungan",
            title = "Zikir Perlindungan Pagi & Petang",
            category = "Pagi & Petang",
            arabicText = "بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ",
            transliteration = "Bismillaahil-ladzii laa yadhurru ma'asmihii syai-un fil-ardhi wa laa fis-samaa-i wa huwas-samii'ul-'aliim.",
            translation = "Dengan nama Allah yang bersama nama-Nya tidak ada sesuatu pun di bumi maupun di langit yang dapat membahayakan, dan Dia Maha Mendengar lagi Maha Mengetahui.",
            reference = "HR. Abu Dawud no. 5088 dan Tirmidzi no. 3388",
            repetitionCount = 3,
            benefits = "Siapa yang membacanya 3 kali pagi dan petang, tidak akan ada bahaya yang memudharatkannya."
        ),
        DoaItem(
            id = "zikir_setelah_salat_istighfar",
            title = "Istighfar Setelah Salat Fardhu",
            category = "Setelah Salat",
            arabicText = "أَسْتَغْفِرُ اللَّهَ، أَسْتَغْفِرُ اللَّهَ، أَسْتَغْفِرُ اللَّهَ. اللَّهُمَّ أَنْتَ السَّلَامُ وَمِنْكَ السَّلَامُ تَبَارَكْتَ يَا ذَا الْجَلَالِ وَالْإِكْرَامِ",
            transliteration = "Astaghfirullaah (3x). Allaahumma antas-salaamu wa minkas-salaamu tabaarakta yaa dzal-jalaali wal-ikraam.",
            translation = "Aku memohon ampun kepada Allah (3x). Ya Allah, Engkaulah Dzat pemberi keselamatan, dan dari-Mulah keselamatan itu berasal. Mahaberkah Engkau wahai Dzat Pemilik Keagungan dan Kemuliaan.",
            reference = "HR. Muslim no. 591",
            repetitionCount = 3,
            benefits = "Sunnah muakkadah yang selalu diamalkan Rasulullah SAW sesaat setelah salam salat."
        ),
        DoaItem(
            id = "zikir_tasbih_salat",
            title = "Tasbih, Tahmid, Takbir Ba'da Salat",
            category = "Setelah Salat",
            arabicText = "سُبْحَانَ اللَّهِ (٣٣x) الْحَمْدُ لِلَّهِ (٣٣x) اللَّهُ أَكْبَرُ (٣٣x)",
            transliteration = "Subhaanallaah (33x), Alhamdulillaah (33x), Allaahu Akbar (33x).",
            translation = "Mahasuci Allah (33x), Segala puji bagi Allah (33x), Allah Mahabesar (33x).",
            reference = "HR. Muslim no. 597",
            repetitionCount = 33,
            benefits = "Diampuni dosa-dosanya meskipun sebanyak buih di lautan."
        )
    )

    // Preset options for Digital Tasbih
    val tasbihPresets = listOf(
        "Subhanallah" to 33,
        "Alhamdulillah" to 33,
        "Allahu Akbar" to 33,
        "Astaghfirullah" to 100,
        "Laa Ilaha Illallah" to 100,
        "Sholawat Nabi" to 100
    )
}
