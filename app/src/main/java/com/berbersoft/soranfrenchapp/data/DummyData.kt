package com.berbersoft.soranfrenchapp.data

import com.berbersoft.soranfrenchapp.R

object DummyData {
    val recentLessons = listOf(
        Lesson(
            id = "1",
            title = "Basic Greetings",
            description = "This lesson covers a wide range of basic greetings in French, including formal and informal ways to say hello, goodbye, and ask how someone is doing. You will learn essential phrases such as 'Bonjour', 'Salut', 'Bonsoir', and 'Au revoir', along with their proper pronunciation and usage in different social contexts. By mastering these greetings, you will be able to confidently start conversations and make a positive first impression in French-speaking environments. The lesson also includes polite expressions like 'S'il vous plaît' and 'Merci', which are crucial for respectful communication.",
            imageResId = R.drawable.lesson_basic_greetings,
            progress = 0.25f,
            difficulty = LessonDifficulty.BEGINNER
        ),
        Lesson(
            id = "2",
            title = "Numbers & Days",
            description = "In this lesson, you will learn how to count from one to twenty in French, as well as the names of the days of the week. Understanding numbers is fundamental for everyday activities such as shopping, telling time, and making appointments. The lesson provides clear pronunciation guides and examples for each number and day, helping you to memorize and use them effectively. You will also practice common phrases involving numbers and dates, enabling you to communicate about schedules, prices, and quantities with ease.",
            imageResId = R.drawable.lesson_numbers,
            progress = 0.40f,
            difficulty = LessonDifficulty.BEGINNER
        ),
        Lesson(
            id = "3",
            title = "Common Phrases",
            description = "This lesson introduces essential French phrases that are commonly used in daily conversations. You will learn how to ask for help, make requests, express needs, and navigate social situations with confidence. Phrases such as 'Je ne comprends pas', 'Pouvez-vous répéter?', and 'Où sont les toilettes?' are covered in detail, along with their pronunciation and cultural context. By mastering these phrases, you will enhance your ability to interact effectively in French-speaking environments, whether traveling, shopping, or socializing.",
            imageResId = R.drawable.lesson_common_phrases,
            progress = 0.15f,
            difficulty = LessonDifficulty.BEGINNER
        )
    )
    
    private val vocabulary = listOf(
        // Lesson 1: Basic Greetings (30+ words/phrases)
        VocabularyItem(
            id = "v1",
            french = "Bonjour",
            english = "Hello",
            kurdish = "سڵاو / ڕۆژباش (sa-law / rozh-bash)",
            pronunciation = "bohn-ZHOOR",
            example = "Bonjour, comment ça va?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v2",
            french = "Salut",
            english = "Hi (informal)",
            kurdish = "سڵاو (se-lav)",
            pronunciation = "sah-LU",
            example = "Salut, ça va?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v3",
            french = "Bonsoir",
            english = "Good evening",
            kurdish = "ئێوارە باش (é-wa-ra bash)",
            pronunciation = "bohn-SWAHR",
            example = "Bonsoir, comment allez-vous ce soir?",
            lessonId = "1"
        ),
        // Added new basic greetings
        VocabularyItem(
            id = "v118",
            french = "Salut tout le monde",
            english = "Hello everyone",
            kurdish = "سڵاو هەموو کەس (salaw hamu kas)",
            pronunciation = "sah-LU toot luh mond",
            example = "Salut tout le monde, comment ça va?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v119",
            french = "Bienvenue chez nous",
            english = "Welcome to our home",
            kurdish = "بەخێربێیت بۆ ماڵەکەمان (bekherbeit bo malakeman)",
            pronunciation = "bee-YEN-vuh-new shay noo",
            example = "Bienvenue chez nous, faites comme chez vous.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v120",
            french = "Bonne nuit et fais de beaux rêves",
            english = "Good night and sweet dreams",
            kurdish = "شەو باش و خەونەکانت خۆش بێت (shew bash w khwenakant khosh bet)",
            pronunciation = "bun NWEE ay fay duh boh rev",
            example = "Bonne nuit et fais de beaux rêves, à demain!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v121",
            french = "À bientôt, prends soin de toi",
            english = "See you soon, take care",
            kurdish = "بە زوویی دەتبینمەوە، خۆت باش پارێزە (be zouyi detbinmewa, khot bash pareza)",
            pronunciation = "ah bee-YEN-toh, prahn swan duh twa",
            example = "À bientôt, prends soin de toi!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v122",
            french = "Comment ça va aujourd'hui?",
            english = "How are you today?",
            kurdish = "چۆنیت ئەمشەو؟ (chonit amshw?)",
            pronunciation = "koh-mohn sah vah oh-zhoor-dwee",
            example = "Bonjour! Comment ça va aujourd'hui?",
            lessonId = "1"
        ),

        VocabularyItem(
            id = "v3",
            french = "Bonsoir",
            english = "Good evening",
            kurdish = "ئێوارە باش (é-wa-ra bash)",
            pronunciation = "bohn-SWAHR",
            example = "Bonsoir, comment allez-vous ce soir?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v4",
            french = "Au revoir",
            english = "Goodbye",
            kurdish = "خواحافیز (khwa-ha-fiz)",
            pronunciation = "oh ruh-VWAHR",
            example = "Au revoir, à demain!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v5",
            french = "Comment ça va?",
            english = "How are you?",
            kurdish = "چۆنیت؟ (cho-nit)",
            pronunciation = "koh-mohn sah VAH",
            example = "Bonjour! Comment ça va aujourd'hui?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v6",
            french = "Ça va bien, merci",
            english = "I'm fine, thank you",
            kurdish = "باشم، سوپاس (ba-shm, sou-pas)",
            pronunciation = "sah vah bee-YEN, mehr-SEE",
            example = "Comment ça va? Ça va bien, merci.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v7",
            french = "Comment vous appelez-vous?",
            english = "What is your name? (formal)",
            kurdish = "ناوت چییە؟ (na-out tchi-yé?)",
            pronunciation = "koh-mohn voo zah-play voo",
            example = "Bonjour, comment vous appelez-vous?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v8",
            french = "Je m'appelle...",
            english = "My name is...",
            kurdish = "ناوم...ـە (na-wm...é)",
            pronunciation = "zhuh mah-PELL",
            example = "Je m'appelle Marie.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v9",
            french = "Enchanté(e)",
            english = "Nice to meet you",
            kurdish = "خۆشحاڵم بە ناسینت (khoch-hal-m bé na-si-nit)",
            pronunciation = "ahn-shahn-TAY",
            example = "Enchanté de faire votre connaissance.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v10",
            french = "Comment allez-vous?",
            english = "How are you? (formal)",
            kurdish = "چۆنیت؟ (cho-nit)",
            pronunciation = "koh-mohn tah-lay voo",
            example = "Bonjour Monsieur, comment allez-vous?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v11",
            french = "Très bien, merci",
            english = "Very well, thank you",
            kurdish = "زۆر باشم، سوپاس (zor ba-shm, sou-pas)",
            pronunciation = "tray bee-YEN, mehr-SEE",
            example = "Comment allez-vous? Très bien, merci.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v12",
            french = "Bonne journée",
            english = "Have a good day",
            kurdish = "ڕۆژێکی باشت هەبێت (ro-zhé-ki ba-chit hé-bét)",
            pronunciation = "bun zhoor-NAY",
            example = "Au revoir et bonne journée!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v13",
            french = "Bonne soirée",
            english = "Have a good evening",
            kurdish = "ئێوارەیەکی باشت هەبێت (é-wa-ra-ye-ki ba-chit hé-bét)",
            pronunciation = "bun swah-RAY",
            example = "Bonne soirée, à demain!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v14",
            french = "À bientôt",
            english = "See you soon",
            kurdish = "بە زوویی دەتبینمەوە (bé zou-yi dét-bi-ni-mé-wé)",
            pronunciation = "ah bee-YEN-toh",
            example = "Au revoir, à bientôt!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v15",
            french = "À demain",
            english = "See you tomorrow",
            kurdish = "بەیانی دەتبینمەوە (bé-ya-ni dét-bi-ni-mé-wé)",
            pronunciation = "ah duh-MAN",
            example = "Bonne nuit, à demain!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v16",
            french = "Bonne nuit",
            english = "Good night",
            kurdish = "شەو باش (shew bash)",
            pronunciation = "bun NWEE",
            example = "Bonne nuit, dormez bien.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v17",
            french = "S'il vous plaît",
            english = "Please (formal)",
            kurdish = "تکایە (ti-ka-yé)",
            pronunciation = "seel voo PLEH",
            example = "Un café, s'il vous plaît.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v18",
            french = "Merci beaucoup",
            english = "Thank you very much",
            kurdish = "زۆر سوپاس (zor sou-pas)",
            pronunciation = "mehr-SEE boh-KOO",
            example = "Merci beaucoup pour votre aide.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v19",
            french = "De rien",
            english = "You're welcome",
            kurdish = "شایانی نییە (cha-ya-ni ni-yé)",
            pronunciation = "duh ree-YEN",
            example = "Merci! De rien, c'est un plaisir.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v20",
            french = "Je vous en prie",
            english = "You're welcome (formal)",
            kurdish = "شایانی نییە (cha-ya-ni ni-yé)",
            pronunciation = "zhuh voo zahn PREE",
            example = "Merci beaucoup! Je vous en prie.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v21",
            french = "Excusez-moi",
            english = "Excuse me (formal)",
            kurdish = "ببوورە (فەرمی)",
            pronunciation = "ehk-skew-ZAY mwah",
            example = "Excusez-moi, où est la gare?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v22",
            french = "Pardon",
            english = "Sorry/Excuse me",
            kurdish = "ببوورە (bi-bou-ré)",
            pronunciation = "par-DON",
            example = "Pardon, je ne comprends pas.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v23",
            french = "Comment t'appelles-tu?",
            english = "What's your name? (informal)",
            kurdish = "ناوت چییە؟ (na-wit chi-yé)",
            pronunciation = "koh-mohn tah-PELL too",
            example = "Salut! Comment t'appelles-tu?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v24",
            french = "Ravi(e) de vous rencontrer",
            english = "Delighted to meet you",
            kurdish = "زۆر دڵخۆشم بە ناسینت (zor dil-khoch-im bé na-si-nit)",
            pronunciation = "rah-VEE duh voo rahn-kon-TRAY",
            example = "Ravi de vous rencontrer, Monsieur.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v25",
            french = "Bienvenue",
            english = "Welcome",
            kurdish = "بەخێربێیت (bé-khér-bé-yit)",
            pronunciation = "bee-YEN-vuh-new",
            example = "Bienvenue à Paris!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v26",
            french = "Ça va mal",
            english = "I'm not doing well",
            kurdish = "باش نیم (bash nim)",
            pronunciation = "sah vah MAHL",
            example = "Comment ça va? Ça va mal aujourd'hui.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v27",
            french = "Pas mal",
            english = "Not bad",
            kurdish = "خراپ نیم (kha-rap nim)",
            pronunciation = "pah MAHL",
            example = "Comment ça va? Pas mal, et toi?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v28",
            french = "À plus tard",
            english = "See you later",
            kurdish = "دواتر دەتبینمەوە (dwa-tir dét-bi-ni-mé-wé)",
            pronunciation = "ah plew TAHR",
            example = "Je dois partir, à plus tard!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v29",
            french = "À la prochaine",
            english = "Until next time",
            kurdish = "تا جاری داهاتوو (ta ja-ri da-ha-too)",
            pronunciation = "ah lah pro-SHEN",
            example = "Au revoir, à la prochaine!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v30",
            french = "Bon après-midi",
            english = "Good afternoon",
            kurdish = "پاش نیوەڕۆ باش (pash ni-wé-ro bash)",
            pronunciation = "bon ah-pray-mee-DEE",
            example = "Bon après-midi, comment allez-vous?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v31",
            french = "Heureux/Heureuse de vous connaître",
            english = "Happy to know you",
            kurdish = "خۆشحاڵم بە ناسینت (khoch-hal-im bé na-si-nit)",
            pronunciation = "uh-RUH/uh-RUHZ duh voo ko-NET-ruh",
            example = "Heureux de vous connaître, Madame.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v32",
            french = "À tout à l'heure",
            english = "See you in a little while",
            kurdish = "کەمێکی تر دەتبینمەوە (ké-mé-ki tir dét-bi-ni-mé-wé)",
            pronunciation = "ah too tah LUHR",
            example = "À tout à l'heure, je reviens vite.",
            lessonId = "1"
        ),
        
        // Lesson 2: Numbers & Days (20 words)
        VocabularyItem(
            id = "v33",
            french = "Un",
            english = "One",
            kurdish = "یەک (yek)",
            pronunciation = "uh(n)",
            example = "J'ai un frère.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v34",
            french = "Deux",
            english = "Two",
            kurdish = "دوو (dou)",
            pronunciation = "duh",
            example = "J'ai deux sœurs.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v35",
            french = "Trois",
            english = "Three",
            kurdish = "سێ (sé)",
            pronunciation = "twah",
            example = "Il y a trois livres sur la table.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v36",
            french = "Quatre",
            english = "Four",
            kurdish = "چوار (chwar)",
            pronunciation = "katr",
            example = "Nous avons quatre chats.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v37",
            french = "Cinq",
            english = "Five",
            kurdish = "پێنج (pénj)",
            pronunciation = "sank",
            example = "J'ai cinq euros.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v38",
            french = "Six",
            english = "Six",
            kurdish = "شەش (chech)",
            pronunciation = "sees",
            example = "Il y a six personnes dans la salle.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v39",
            french = "Sept",
            english = "Seven",
            kurdish = "حەوت (hewt)",
            pronunciation = "set",
            example = "Le magasin ouvre à sept heures.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v40",
            french = "Huit",
            english = "Eight",
            kurdish = "هەشت (hecht)",
            pronunciation = "weet",
            example = "J'ai huit ans.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v41",
            french = "Neuf",
            english = "Nine",
            kurdish = "نۆ (no)",
            pronunciation = "nuhf",
            example = "Il est neuf heures.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v42",
            french = "Dix",
            english = "Ten",
            kurdish = "دە (de)",
            pronunciation = "dees",
            example = "J'ai dix doigts.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v43",
            french = "Onze",
            english = "Eleven",
            kurdish = "یازدە (yazde)",
            pronunciation = "onz",
            example = "Le match commence à onze heures.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v44",
            french = "Douze",
            english = "Twelve",
            kurdish = "دوازدە (dwazde)",
            pronunciation = "dooz",
            example = "Il y a douze mois dans une année.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v45",
            french = "Treize",
            english = "Thirteen",
            kurdish = "سێزدە (sézde)",
            pronunciation = "trez",
            example = "Treize est un nombre porte-bonheur pour certains.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v46",
            french = "Quatorze",
            english = "Fourteen",
            kurdish = "چواردە (chwar-de)",
            pronunciation = "kah-torz",
            example = "Le quatorze juillet est la fête nationale française.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v47",
            french = "Quinze",
            english = "Fifteen",
            kurdish = "پازدە (paz-de)",
            pronunciation = "kanz",
            example = "J'ai quinze ans.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v48",
            french = "Seize",
            english = "Sixteen",
            kurdish = "شازدە (chaz-de)",
            pronunciation = "sez",
            example = "Elle a seize ans.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v49",
            french = "Dix-sept",
            english = "Seventeen",
            kurdish = "حەڤدە (hev-de)",
            pronunciation = "dee-set",
            example = "Mon frère a dix-sept ans.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v50",
            french = "Dix-huit",
            english = "Eighteen",
            kurdish = "هەژدە (hezh-de)",
            pronunciation = "deez-weet",
            example = "Elle aura dix-huit ans demain.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v51",
            french = "Dix-neuf",
            english = "Nineteen",
            kurdish = "نۆزدە (noz-de)",
            pronunciation = "deez-nuhf",
            example = "Il a dix-neuf ans.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v52",
            french = "Vingt",
            english = "Twenty",
            kurdish = "بیست (bist)",
            pronunciation = "van",
            example = "Il y a vingt étudiants dans la classe.",
            lessonId = "2"
        ),
        
        // Lesson 3: Common Phrases (20+ phrases)
        VocabularyItem(
            id = "v53",
            french = "Je ne comprends pas",
            english = "I don't understand",
            kurdish = "تێناگەم (té-na-gem)",
            pronunciation = "zhuh nuh kom-prahn pah",
            example = "Pardon, je ne comprends pas. Pouvez-vous répéter?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v54",
            french = "Pouvez-vous répéter?",
            english = "Can you repeat?",
            kurdish = "دەکرێت دووبارەی بکەیتەوە؟ (de-krét dou-ba-rey bi-key-te-we?)",
            pronunciation = "poo-vay voo ray-pay-tay",
            example = "Pouvez-vous répéter plus lentement, s'il vous plaît?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v55",
            french = "Parlez-vous anglais?",
            english = "Do you speak English?",
            kurdish = "ئینگلیزی دەزانیت؟ (ing-li-zi de-za-nit?)",
            pronunciation = "par-lay voo ahn-GLAY",
            example = "Excusez-moi, parlez-vous anglais?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v56",
            french = "Je parle un peu français",
            english = "I speak a little French",
            kurdish = "من کەمێک فەڕەنسی دەزانم (min ke-mék fe-ren-si de-za-nim)",
            pronunciation = "zhuh parl uh puh frahn-SAY",
            example = "Je parle un peu français, mais pas beaucoup.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v57",
            french = "Où sont les toilettes?",
            english = "Where is the bathroom?",
            kurdish = "ئاودەست لە کوێیە؟ (aw-dest le ku-yé?)",
            pronunciation = "oo son lay twah-let",
            example = "Excusez-moi, où sont les toilettes, s'il vous plaît?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v58",
            french = "Combien ça coûte?",
            english = "How much does it cost?",
            kurdish = "نرخی چەندە؟ (nir-khi chen-de)",
            pronunciation = "kom-bee-YEN sah koot",
            example = "Combien ça coûte, ce livre?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v59",
            french = "Je voudrais...",
            english = "I would like...",
            kurdish = "...دەمەوێت (...de-me-wét)",
            pronunciation = "zhuh voo-dreh",
            example = "Je voudrais un café, s'il vous plaît.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v60",
            french = "À quelle heure...?",
            english = "At what time...?",
            kurdish = "لە چ کاتێکدا...؟ (le ch ka-tékda...?)",
            pronunciation = "ah kell UHR",
            example = "À quelle heure ouvre le musée?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v61",
            french = "Je suis perdu(e)",
            english = "I am lost",
            kurdish = "ون بووم (win boum)",
            pronunciation = "zhuh swee per-doo/doo",
            example = "Excusez-moi, je suis perdu.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v62",
            french = "Pouvez-vous m'aider?",
            english = "Can you help me?",
            kurdish = "دەتوانیت یارمەتیم بدەیت؟ (de-twa-nit yar-me-tim bi-dey-t?)",
            pronunciation = "poo-vay voo may-DAY",
            example = "Pouvez-vous m'aider à trouver cette adresse?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v63",
            french = "Je suis désolé(e)",
            english = "I am sorry",
            kurdish = "ببوورە (bi-bou-re)",
            pronunciation = "zhuh swee day-zo-lay",
            example = "Je suis désolé, je ne peux pas venir ce soir.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v64",
            french = "Bon appétit",
            english = "Enjoy your meal",
            kurdish = "نۆش گیان (noch gi-yan)",
            pronunciation = "bohn ah-pay-tee",
            example = "Voici votre plat. Bon appétit!",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v65",
            french = "À votre santé",
            english = "Cheers (to your health)",
            kurdish = "بە ساغی (bé sa-ghi)",
            pronunciation = "ah votr sahn-tay",
            example = "Levons nos verres. À votre santé!",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v66",
            french = "C'est combien?",
            english = "How much is it?",
            kurdish = "چەندە؟ (chen-de?)",
            pronunciation = "say kom-bee-YEN",
            example = "C'est combien, le total?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v67",
            french = "Je ne sais pas",
            english = "I don't know",
            kurdish = "من نازانم (min na-za-nim)",
            pronunciation = "zhuh nuh say pah",
            example = "Je ne sais pas où se trouve la banque.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v68",
            french = "Quelle heure est-il?",
            english = "What time is it?",
            kurdish = "کاتژمێر چەندە؟ (kat-zh-mér chen-de?)",
            pronunciation = "kel er eh-teel",
            example = "Excusez-moi, quelle heure est-il?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v69",
            french = "Je m'appelle...",
            english = "My name is...",
            kurdish = "ناوم...ـە (na-wim...é)",
            pronunciation = "zhuh mah-PELL",
            example = "Bonjour, je m'appelle Pierre.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v70",
            french = "D'où venez-vous?",
            english = "Where are you from?",
            kurdish = "خەڵکی کوێیت؟ (khel-ki ku-yit?)",
            pronunciation = "doo vuh-NAY voo",
            example = "D'où venez-vous? Je viens du Canada.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v71",
            french = "Je viens de...",
            english = "I come from...",
            kurdish = "من خەڵکی...م (min khel-ki...im)",
            pronunciation = "zhuh vee-YEN duh",
            example = "Je viens de Paris.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v72",
            french = "Félicitations!",
            english = "Congratulations!",
            kurdish = "پیرۆزبایی! (pi-roz-ba-yi!)",
            pronunciation = "fay-lee-see-tah-see-YON",
            example = "Félicitations pour votre nouveau travail!",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v73",
            french = "Bonne chance",
            english = "Good luck",
            kurdish = "بەختت باش بێت (bekht-it bash bét)",
            pronunciation = "bun SHAHNS",
            example = "Bonne chance pour votre examen!",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v74",
            french = "Je suis content(e) de vous voir",
            english = "I'm happy to see you",
            kurdish = "خۆشحاڵم بە بینینت (khoch-hal-im be bi-ni-nit)",
            pronunciation = "zhuh swee kon-TAHN/kon-TAHNT duh voo vwahr",
            example = "Je suis content de vous voir après si longtemps!",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v75",
            french = "Puis-je avoir l'addition?",
            english = "Can I have the bill?",
            kurdish = "دەتوانم حیسابەکە وەربگرم؟ (de-twa-nim hi-sa-be-ké wer-bi-grim?)",
            pronunciation = "pwee-zhuh ah-VWAHR lah-dee-see-YON",
            example = "Excusez-moi, puis-je avoir l'addition, s'il vous plaît?",
            lessonId = "3"
        ),
        
        // Continuing Lesson 5: Daily Routines
        VocabularyItem(
            id = "5_13",
            french = "La nuit",
            english = "Night",
            kurdish = "شەو (shew)",
            pronunciation = "lah nwee",
            example = "Je dors bien la nuit.",
            lessonId = "5"
        ),
        VocabularyItem(
            id = "5_14",
            french = "La semaine",
            english = "Week",
            kurdish = "هەفتە (hef-te)",
            pronunciation = "lah suh-men",
            example = "Ma semaine est très occupée.",
            lessonId = "5"
        ),
        
        // Lesson 3: Food & Dining
        VocabularyItem(
            id = "3_1",
            french = "Le restaurant",
            english = "Restaurant",
            kurdish = "چێشتخانە (chécht-kha-ne)",
            pronunciation = "luh res-toh-rahn",
            example = "Allons au restaurant ce soir.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_2",
            french = "Le menu",
            english = "Menu",
            kurdish = "مێنیو (mé-ni-u)",
            pronunciation = "luh muh-noo",
            example = "Pouvez-vous m'apporter le menu, s'il vous plaît?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_3",
            french = "L'addition",
            english = "Bill",
            kurdish = "پسووڵە (pi-su-le)",
            pronunciation = "lah-dee-syohn",
            example = "L'addition, s'il vous plaît.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_4",
            french = "Le pain",
            english = "Bread",
            kurdish = "نان (nan)",
            pronunciation = "luh pan",
            example = "Le pain français est délicieux.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_5",
            french = "L'eau",
            english = "Water",
            kurdish = "ئاو (aw)",
            pronunciation = "loh",
            example = "Un verre d'eau, s'il vous plaît.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_6",
            french = "Le vin",
            english = "Wine",
            kurdish = "شەراب (she-rab)",
            pronunciation = "luh van",
            example = "Une bouteille de vin rouge, s'il vous plaît.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_7",
            french = "Le café",
            english = "Coffee",
            kurdish = "قاوە (qa-we)",
            pronunciation = "luh kah-fay",
            example = "Je prends un café tous les matins.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_8",
            french = "Le thé",
            english = "Tea",
            kurdish = "چا (cha)",
            pronunciation = "luh tay",
            example = "Voulez-vous du thé ou du café?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_9",
            french = "La viande",
            english = "Meat",
            kurdish = "گۆشت (gocht)",
            pronunciation = "lah vee-ahnd",
            example = "Je ne mange pas de viande.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_10",
            french = "Le poisson",
            english = "Fish",
            kurdish = "ماسی (ma-si)",
            pronunciation = "luh pwah-sohn",
            example = "Le poisson est frais aujourd'hui.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_11",
            french = "Les légumes",
            english = "Vegetables",
            kurdish = "سەوزەوات (sew-ze-wat)",
            pronunciation = "lay lay-goom",
            example = "Il faut manger beaucoup de légumes.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_12",
            french = "Les fruits",
            english = "Fruits",
            kurdish = "میوە (mi-we)",
            pronunciation = "lay frwee",
            example = "J'aime manger des fruits frais.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_13",
            french = "Le dessert",
            english = "Dessert",
            kurdish = "شیرینی (shi-ri-ni)",
            pronunciation = "luh day-sair",
            example = "Que voulez-vous comme dessert?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_14",
            french = "La cuisine",
            english = "Kitchen/Cooking",
            kurdish = "چێشتخانە/چێشتلێنان (chécht-kha-ne/chécht-lé-nan)",
            pronunciation = "lah kwee-zeen",
            example = "J'aime faire la cuisine française.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_15",
            french = "Le petit-déjeuner",
            english = "Breakfast",
            kurdish = "نانی بەیانی (na-ni be-ya-ni)",
            pronunciation = "luh puh-tee day-zhuh-nay",
            example = "Le petit-déjeuner est le repas le plus important.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_16",
            french = "Le déjeuner",
            english = "Lunch",
            kurdish = "نانی نیوەڕۆ (na-ni ni-we-ro)",
            pronunciation = "luh day-zhuh-nay",
            example = "On déjeune ensemble?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_17",
            french = "Le dîner",
            english = "Dinner",
            kurdish = "نانی ئێوارە (na-ni é-wa-re)",
            pronunciation = "luh dee-nay",
            example = "Le dîner est servi à 20h.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "3_18",
            french = "Bon appétit",
            english = "Enjoy your meal",
            kurdish = "نۆش گیان (noch gi-yan)",
            pronunciation = "bohn ah-pay-tee",
            example = "Bon appétit à tous!",
            lessonId = "3"
        ),
        // Common Everyday Vocabulary
        VocabularyItem(
            id = "v76",
            french = "La maison",
            english = "House",
            kurdish = "خانوو (kha-nu)",
            pronunciation = "lah may-zohn",
            example = "J'habite dans une grande maison.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v77",
            french = "La voiture",
            english = "Car",
            kurdish = "ئۆتۆمبێل (o-tom-bél)",
            pronunciation = "lah vwah-toor",
            example = "Ma voiture est rouge.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v78",
            french = "Le téléphone",
            english = "Phone",
            kurdish = "تەلەفۆن (te-le-fon)",
            pronunciation = "luh tay-lay-fon",
            example = "Quel est ton numéro de téléphone?",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v79",
            french = "Le livre",
            english = "Book",
            kurdish = "کتێب (k-téb)",
            pronunciation = "luh lee-vruh",
            example = "J'aime lire des livres.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v80",
            french = "La table",
            english = "Table",
            kurdish = "مێز (méz)",
            pronunciation = "lah tah-bluh",
            example = "Le livre est sur la table.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v81",
            french = "La chaise",
            english = "Chair",
            kurdish = "کورسی (kur-si)",
            pronunciation = "lah shez",
            example = "Asseyez-vous sur la chaise.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v82",
            french = "Le lit",
            english = "Bed",
            kurdish = "قەرەوێڵە (qe-re-wé-le)",
            pronunciation = "luh lee",
            example = "Je suis fatigué, je vais au lit.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v83",
            french = "La fenêtre",
            english = "Window",
            kurdish = "پەنجەرە (pen-je-re)",
            pronunciation = "lah fuh-neh-truh",
            example = "Ouvrez la fenêtre, s'il vous plaît.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v84",
            french = "La porte",
            english = "Door",
            kurdish = "دەرگا (der-ga)",
            pronunciation = "lah port",
            example = "Fermez la porte, s'il vous plaît.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v85",
            french = "Le jardin",
            english = "Garden",
            kurdish = "باخچە (bakh-che)",
            pronunciation = "lah zhar-dan",
            example = "Les fleurs dans le jardin sont belles.",
            lessonId = "3"
        ),
        // More Common Phrases - Updating Kurdish with pronunciation
        VocabularyItem(
            id = "v86",
            french = "Je ne comprends pas",
            english = "I don't understand",
            kurdish = "تێناگەم (té-na-gem)",
            pronunciation = "zhuh nuh kom-prahn pah",
            example = "Désolé, je ne comprends pas bien.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v87",
            french = "Pouvez-vous répéter?",
            english = "Can you repeat?",
            kurdish = "دەکرێت دووبارەی بکەیتەوە؟ (de-krét dou-ba-rey bi-key-te-we?)",
            pronunciation = "poo-vay voo ray-pay-tay",
            example = "Pardon, pouvez-vous répéter plus lentement?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v88",
            french = "Où sont les toilettes?",
            english = "Where is the bathroom?",
            kurdish = "ئاودەست لە کوێیە؟ (aw-dest le ku-yé?)",
            pronunciation = "oo son lay twah-let",
            example = "Excusez-moi, où sont les toilettes?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v89",
            french = "Je suis perdu(e)",
            english = "I am lost",
            kurdish = "ون بووم (win boum)",
            pronunciation = "zhuh swee pehr-doo/doo",
            example = "Excusez-moi, je suis perdu.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v90",
            french = "Quelle heure est-il?",
            english = "What time is it?",
            kurdish = "کاتژمێر چەندە؟ (kat-zh-mér chen-de?)",
            pronunciation = "kel er eh-teel",
            example = "Pardon, quelle heure est-il?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v91",
            french = "Je suis désolé(e)",
            english = "I am sorry",
            kurdish = "ببوورە (bi-bou-re)",
            pronunciation = "zhuh swee day-zo-lay",
            example = "Je suis désolé pour le retard.",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v92",
            french = "Bon appétit",
            english = "Enjoy your meal",
            kurdish = "نۆش گیان (noch gi-yan)",
            pronunciation = "bohn ah-pay-tee",
            example = "Bon appétit à tous!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v93",
            french = "À votre santé",
            english = "Cheers (to your health)",
            kurdish = "بە ساغی (bé sa-ghi)",
            pronunciation = "ah votr sahn-tay",
            example = "À votre santé! Santé!",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v94",
            french = "C'est combien?",
            english = "How much is it?",
            kurdish = "بە چەندە؟ (bé chen-de?)",
            pronunciation = "seh kom-byen",
            example = "Excusez-moi, c'est combien?",
            lessonId = "1"
        ),
        VocabularyItem(
            id = "v95",
            french = "Je voudrais...",
            english = "I would like...",
            kurdish = "...دەمەوێت (...de-me-wét)",
            pronunciation = "zhuh voo-dreh",
            example = "Je voudrais un café, s'il vous plaît.",
            lessonId = "1"
        ),
        // Colors - Updating Kurdish with pronunciation
        VocabularyItem(
            id = "v96",
            french = "Rouge",
            english = "Red",
            kurdish = "سوور (sour)",
            pronunciation = "roozh",
            example = "Ma voiture est rouge.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v97",
            french = "Bleu",
            english = "Blue",
            kurdish = "شین (chin)",
            pronunciation = "bluh",
            example = "Le ciel est bleu.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v98",
            french = "Vert",
            english = "Green",
            kurdish = "سەوز (sewz)",
            pronunciation = "vehr",
            example = "J'aime les arbres verts.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v99",
            french = "Jaune",
            english = "Yellow",
            kurdish = "زەرد (zerd)",
            pronunciation = "zhohn",
            example = "Le soleil est jaune.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v100",
            french = "Noir",
            english = "Black",
            kurdish = "ڕەش (resh)",
            pronunciation = "nwahr",
            example = "Mon chat est noir.",
            lessonId = "3"
        ),

        // Days of the Week - Updating Kurdish with pronunciation
        VocabularyItem(
            id = "v101",
            french = "Lundi",
            english = "Monday",
            kurdish = "دووشەممە (dou-chem-me)",
            pronunciation = "luhn-dee",
            example = "Je travaille le lundi.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v102",
            french = "Mardi",
            english = "Tuesday",
            kurdish = "سێشەممە (sé-chem-me)",
            pronunciation = "mahr-dee",
            example = "J'ai cours le mardi.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v103",
            french = "Mercredi",
            english = "Wednesday",
            kurdish = "چوارشەممە (chwar-chem-me)",
            pronunciation = "mehr-kruh-dee",
            example = "Le mercredi, je fais du sport.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v104",
            french = "Jeudi",
            english = "Thursday",
            kurdish = "پێنجشەممە (pénj-chem-me)",
            pronunciation = "zhuh-dee",
            example = "Nous avons une réunion jeudi.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v105",
            french = "Vendredi",
            english = "Friday",
            kurdish = "هەینی (hey-ni)",
            pronunciation = "vahn-druh-dee",
            example = "Vendredi, c'est le weekend!",
            lessonId = "2"
        ),

        // Family Members - Updating Kurdish with pronunciation
        VocabularyItem(
            id = "v106",
            french = "La mère",
            english = "Mother",
            kurdish = "دایک (dayk)",
            pronunciation = "lah mehr",
            example = "Ma mère est très gentille.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v107",
            french = "Le père",
            english = "Father",
            kurdish = "باوک (bawk)",
            pronunciation = "luh pehr",
            example = "Mon père travaille beaucoup.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v108",
            french = "La sœur",
            english = "Sister",
            kurdish = "خوشک (khuchk)",
            pronunciation = "lah sur",
            example = "J'ai une sœur plus âgée.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v109",
            french = "Le frère",
            english = "Brother",
            kurdish = "برا (bra)",
            pronunciation = "luh frehr",
            example = "Mon frère est plus jeune que moi.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v110",
            french = "Les grands-parents",
            english = "Grandparents",
            kurdish = "داپیر و باپیر (da-pir u ba-pir)",
            pronunciation = "lay grahn-pah-rahn",
            example = "Mes grands-parents habitent à la campagne.",
            lessonId = "3"
        ),

        // Weather Terms - Updating Kurdish with pronunciation
        VocabularyItem(
            id = "v111",
            french = "Il fait beau",
            english = "It's nice weather",
            kurdish = "کەش خۆشە (kech kho-che)",
            pronunciation = "eel fay boh",
            example = "Aujourd'hui, il fait beau.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v112",
            french = "Il pleut",
            english = "It's raining",
            kurdish = "باران دەبارێت (ba-ran de-ba-rét)",
            pronunciation = "eel pluh",
            example = "Je reste à la maison car il pleut.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v113",
            french = "Il fait chaud",
            english = "It's hot",
            kurdish = "گەرمە (ger-me)",
            pronunciation = "eel fay shoh",
            example = "En été, il fait chaud.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v114",
            french = "Il fait froid",
            english = "It's cold",
            kurdish = "ساردە (sar-de)",
            pronunciation = "eel fay frwah",
            example = "En hiver, il fait froid.",
            lessonId = "3"
        ),
        VocabularyItem(
            id = "v115",
            french = "Il neige",
            english = "It's snowing",
            kurdish = "بەفر دەبارێت (befr de-ba-rét)",
            pronunciation = "eel nezh",
            example = "En décembre, il neige souvent.",
            lessonId = "3"
        ),

        // Directions - Updating Kurdish with pronunciation
        VocabularyItem(
            id = "dir1",
            french = "À gauche",
            english = "Left",
            kurdish = "چەپ (chep)",
            pronunciation = "ah gosh",
            example = "Tournez à gauche",
            lessonId = "directions"
        ),
        VocabularyItem(
            id = "dir2",
            french = "À droite",
            english = "Right",
            kurdish = "ڕاست (rast)",
            pronunciation = "ah drwat",
            example = "Tournez à droite",
            lessonId = "directions"
        ),
        VocabularyItem(
            id = "dir3",
            french = "En haut",
            english = "Up",
            kurdish = "سەرەوە (serawe)",
            pronunciation = "on oh",
            example = "Allez en haut",
            lessonId = "directions"
        ),
        VocabularyItem(
            id = "dir4",
            french = "En bas",
            english = "Down",
            kurdish = "خوارەوە (khwa-re-we)",
            pronunciation = "on bah",
            example = "Allez en bas",
            lessonId = "directions"
        ),
        VocabularyItem(
            id = "dir5",
            french = "Près",
            english = "Near",
            kurdish = "نزیک (nizik)",
            pronunciation = "preh",
            example = "C'est près d'ici",
            lessonId = "directions"
        ),
        VocabularyItem(
            id = "dir6",
            french = "Loin",
            english = "Far",
            kurdish = "دوور (dour)",
            pronunciation = "lwan",
            example = "C'est loin d'ici",
            lessonId = "directions"
        ),
        VocabularyItem(
            id = "dir7",
            french = "Ici",
            english = "Here",
            kurdish = "لێرە (lé-re)",
            pronunciation = "ee-see",
            example = "Je suis ici",
            lessonId = "directions"
        ),
        VocabularyItem(
            id = "dir8",
            french = "Là",
            english = "There",
            kurdish = "لەوێ (le-wé)",
            pronunciation = "lah",
            example = "C'est là",
            lessonId = "directions"
        ),

        // Emergency Phrases - Updating Kurdish with pronunciation
        VocabularyItem(
            id = "em1",
            french = "Au secours!",
            english = "Help!",
            kurdish = "یارمەتی! (yar-me-ti!)",
            pronunciation = "oh suh-KOOR",
            example = "Au secours! J'ai besoin d'aide!",
            lessonId = "emergency"
        ),
        VocabularyItem(
            id = "em2",
            french = "J'ai besoin d'un médecin",
            english = "I need a doctor",
            kurdish = "پزیشکم پێویستە (pizich-kim pé-wis-te)",
            pronunciation = "zhay buh-ZWAN dun may-DSAN",
            example = "J'ai besoin d'un médecin urgement",
            lessonId = "emergency"
        ),
        VocabularyItem(
            id = "em3",
            french = "Où est l'hôpital?",
            english = "Where is the hospital?",
            kurdish = "نەخۆشخانە لە کوێیە? (ne-khoch-kha-ne le ku-yé?)",
            pronunciation = "oo ay lo-pee-TAL",
            example = "Excusez-moi, où est l'hôpital le plus proche?",
            lessonId = "emergency"
        ),
        VocabularyItem(
            id = "em4",
            french = "Appelez la police",
            english = "Call the police",
            kurdish = "پۆلیس بانگ بکە (po-lis bang bi-ke)",
            pronunciation = "ah-play lah poh-LEES",
            example = "S'il vous plaît, appelez la police!",
            lessonId = "emergency"
        ),

        // Adding Saturday and Sunday to Days of the Week - Updating Kurdish with pronunciation
        VocabularyItem(
            id = "v116",
            french = "Samedi",
            english = "Saturday",
            kurdish = "شەممە (chem-me)",
            pronunciation = "sahm-dee",
            example = "Le samedi, je fais les courses.",
            lessonId = "2"
        ),
        VocabularyItem(
            id = "v117",
            french = "Dimanche",
            english = "Sunday",
            kurdish = "یەکشەممە (yek-chem-me)",
            pronunciation = "dee-mahnsh",
            example = "Le dimanche est un jour de repos.",
            lessonId = "2"
        )
    )
    
    val commonPhrases = mutableListOf<Phrase>().apply {
        add(Phrase(
            id = "phrase_1",
            french = "Je veux de l'eau",
            english = "I want water",
            kurdish = "ئاوم دەوێ (awm dêwê)",
            pronunciation = "awm dêwê",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        
        add(Phrase(
            id = "phrase_2",
            french = "J'ai faim",
            english = "I am hungry",
            kurdish = "برسیمە (brsîme)",
            pronunciation = "brsîme",
            category = PhraseCategory.DINING
        ))
        
        add(Phrase(
            id = "phrase_3",
            french = "C'est combien?",
            english = "How much is this?",
            kurdish = "ئەمە بە چەندە? (eme be çende?)",
            pronunciation = "eme be çende?",
            category = PhraseCategory.SHOPPING
        ))
        
        
        add(Phrase(
            id = "phrase_5",
            french = "Où sont les toilettes?",
            english = "Where is the bathroom?",
            kurdish = "تەوالێت لە کوێیە? (tewalêt le kweye?)",
            pronunciation = "tewalêt le kweye?",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        // Added new common phrases
        add(Phrase(
            id = "phrase_101",
            french = "Comment allez-vous?",
            english = "How are you? (formal)",
            kurdish = "چۆنیت؟ (chonit?)",
            pronunciation = "koh-mohn tah-lay voo",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        add(Phrase(
            id = "phrase_102",
            french = "Je suis fatigué(e)",
            english = "I am tired",
            kurdish = "من خەستەم (min khastem)",
            pronunciation = "zhuh swee fa-tee-gay",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        add(Phrase(
            id = "phrase_103",
            french = "Pouvez-vous m'aider?",
            english = "Can you help me?",
            kurdish = "دەتوانیت یارمەتیم بدەیت؟ (detwanit yarmetim bedayt?)",
            pronunciation = "poo-vay voo may-day",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        add(Phrase(
            id = "phrase_104",
            french = "Je ne comprends pas",
            english = "I don't understand",
            kurdish = "من نازانم (min nazanim)",
            pronunciation = "zhuh nuh kom-prahnd pah",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        add(Phrase(
            id = "phrase_105",
            french = "Parlez-vous anglais?",
            english = "Do you speak English?",
            kurdish = "ئینگلیزی دەزانیت؟ (inglizi dezanit?)",
            pronunciation = "par-lay voo ahn-glay",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        add(Phrase(
            id = "phrase_106",
            french = "Je suis perdu(e)",
            english = "I am lost",
            kurdish = "من گم شدم (min gom shudam)",
            pronunciation = "zhuh swee per-doo",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        add(Phrase(
            id = "phrase_107",
            french = "Où est la pharmacie?",
            english = "Where is the pharmacy?",
            kurdish = "داروخانا لە کوێیە؟ (darukhana le kuye?)",
            pronunciation = "oo ay lah far-mah-see",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        add(Phrase(
            id = "phrase_108",
            french = "Je voudrais un médecin",
            english = "I would like a doctor",
            kurdish = "من دکتۆر دەمەوێت (min doktor damewet)",
            pronunciation = "zhuh voo-dray uh mayd-sahn",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        add(Phrase(
            id = "phrase_109",
            french = "Merci beaucoup",
            english = "Thank you very much",
            kurdish = "زۆر سوپاس (zor supas)",
            pronunciation = "mehr-see boh-koo",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))
        add(Phrase(
            id = "phrase_110",
            french = "Excusez-moi",
            english = "Excuse me",
            kurdish = "ببورە (bboora)",
            pronunciation = "ex-kew-zay mwah",
            category = PhraseCategory.BASIC_EXPRESSIONS
        ))

        // Expressions affectueuses
        add(Phrase(
            id = "phrase_love_1",
            french = "Mon amour",
            english = "My love",
            kurdish = "خۆشەویستیم (kho-ché-wis-tim)",
            pronunciation = "mohn ah-moor",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_love_2",
            french = "Mon cher/Ma chère",
            english = "My dear",
            kurdish = "ئازیزەکەم (ah-zi-zé-kèm)",
            pronunciation = "mohn shehr/mah shehr",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_love_3",
            french = "Mon cœur",
            english = "My heart",
            kurdish = "دڵم (di-lim)",
            pronunciation = "mohn keur",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_love_4",
            french = "Mon âme",
            english = "My soul",
            kurdish = "ڕۆحم (ro-him)",
            pronunciation = "mohn ahm",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_love_5",
            french = "Je t'aime",
            english = "I love you",
            kurdish = "تۆم خۆشدەوێ (tom khoch-dé-wé)",
            pronunciation = "zhuh tem",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_love_6",
            french = "Tu es parfait(e)",
            english = "You are perfect",
            kurdish = "تۆ بێوێنەیت (to bé-wé-né-ït)",
            pronunciation = "tu eh par-feh/par-fet",
            category = PhraseCategory.SOCIAL
        ))

        // Phrases romantiques supplémentaires - Updating Kurdish with pronunciation
        add(Phrase(
            id = "phrase_rom_1",
            french = "Tu me manques",
            english = "I miss you",
            kurdish = "دڵم بۆت تەنگە (dlm bot tenge)",
            pronunciation = "dlm bot tenge",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_rom_2",
            french = "Tu es dans mon cœur",
            english = "You are in my heart",
            kurdish = "تۆ لە دڵمدایت (to le dlmdayt)",
            pronunciation = "to le dlmdayt",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_rom_3",
            french = "Pour toujours",
            english = "Forever",
            kurdish = "بۆ هەمیشە (bo hemîshe)",
            pronunciation = "bo hemîshe",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_rom_4",
            french = "Tu es ma vie",
            english = "You are my life",
            kurdish = "تۆ ژیانمیت (to zhyanmît)",
            pronunciation = "to zhyanmît",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_rom_5",
            french = "Je pense à toi",
            english = "I'm thinking of you",
            kurdish = "بیرت دەکەم (bîrt dekem)",
            pronunciation = "bîrt dekem",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_rom_6",
            french = "Tu es tout pour moi",
            english = "You are everything to me",
            kurdish = "تۆ هەموو شتێکمیت (to hemu shtêkmît)",
            pronunciation = "to hemu shtêkmît",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_rom_7",
            french = "Mon trésor",
            english = "My treasure",
            kurdish = "گەنجینەکەم (genjînekem)",
            pronunciation = "genjînekem",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_rom_8",
            french = "Tu es magnifique",
            english = "You are beautiful",
            kurdish = "تۆ زۆر جوانیت (to zor jwanît)",
            pronunciation = "to zor jwanît",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_rom_9",
            french = "Je ne peux pas vivre sans toi",
            english = "I can't live without you",
            kurdish = "ناتوانم بەبێ تۆ بژیم (natwanm bebê to bzhîm)",
            pronunciation = "natwanm bebê to bzhîm",
            category = PhraseCategory.SOCIAL
        ))

        add(Phrase(
            id = "phrase_rom_10",
            french = "Tu es mon bonheur",
            english = "You are my happiness",
            kurdish = "تۆ خۆشبەختی منیت (to xoshbextî mnît)",
            pronunciation = "to xoshbextî mnît",
            category = PhraseCategory.SOCIAL
        ))
    }

    fun getPhrasesByCategory(category: PhraseCategory): List<Phrase> {
        return commonPhrases.filter { it.category == category }
    }

    fun getAllPhrases(): List<Phrase> {
        return commonPhrases
    }

    fun getVocabularyForLesson(lessonId: String): List<VocabularyItem> {
        return vocabulary.filter { it.lessonId == lessonId }
    }
    
    fun getAllVocabulary(): List<VocabularyItem> {
        return vocabulary
    }
    
    fun searchVocabulary(query: String): List<VocabularyItem> {
        if (query.isBlank()) return emptyList()
        
        return vocabulary.filter { item ->
            item.french.contains(query, ignoreCase = true) ||
            item.english.contains(query, ignoreCase = true) ||
            item.kurdish.contains(query, ignoreCase = true)
        }
    }
} 