package com.example.vkr.data

import com.example.vkr.mainScreen.News.News
import com.example.vkr.mainScreen.Search.Game
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Data {
    var names = arrayOf(
        "Pokemon FireRed/LeafGreen",
        "Pokemon Red/Blue",
        "Stronghold Crusader",
        "BioShock",
        "BioShock 2",
        "Pokemon Yellow: Special Pikachu Edition",
        "Portal",
        "Super Mario Odyssey",
        "The Elder Scrolls V: Skyrim",
        "Journey",
        "The Last of Us",
        "God of War",
        "The Legend of Zelda: Breath of the Wild",
        "Half-Life 2",
        "The Legend of Zelda: Ocarina of Time",
        "Hearthstone",
        "Inscryption",
        "Clash Royale",
        "Magic: The Gathering Arena",
        "World of Warcraft",
        "Allods Online",
        "Atomic Heart",
        "The Witcher 3: Wild Hunt",
        "Persona 5",
        "The Witcher",
        "The Witcher 2: Assassins of Kings",
        "Elden Ring",
        "Dark Souls",
        "Dark Souls II",
        "Dark Souls III"
    )
    var platforms = arrayOf(
        arrayListOf("Game Boy Advance"),
        arrayListOf("Game Boy", "Nintendo 3DS"),
        arrayListOf("Windows", "Mac"),
        arrayListOf("Windows", "Mac", "PlayStation 3", "Xbox 360"),
        arrayListOf("Windows", "Mac", "PlayStation 3", "Xbox 360"),
        arrayListOf("Game Boy", "Nintendo 3DS"),
        arrayListOf("Android", "Linux", "Mac", "Windows"),
        arrayListOf("Nintendo Switch"),
        arrayListOf(
            "Nintendo Switch",
            "Windows",
            "PlayStation 3",
            "PlayStation 4",
            "PlayStation 5",
            "Xbox 360"
        ),
        arrayListOf("Windows", "PlayStation 3", "PlayStation 4"),
        arrayListOf("PlayStation 3", "PlayStation 4"),
        arrayListOf("Windows", "PlayStation 4"),
        arrayListOf("Nintendo Switch"),
        arrayListOf("Android", "Linux", "Mac", "Windows"),
        arrayListOf("Nintendo 3DS", "Nintendo Switch"),
        arrayListOf("Android", "Mac", "Windows", "iPhone"),
        arrayListOf("Windows", "PlayStation 4", "PlayStation 5"),
        arrayListOf("Android", "iPhone"),
        arrayListOf("Android", "Mac", "Windows", "iPhone"),
        arrayListOf("Mac", "Windows"),
        arrayListOf("Windows"),
        arrayListOf("Windows", "PlayStation 4", "PlayStation 5"),
        arrayListOf("Nintendo Switch", "Windows", "PlayStation 4"),
        arrayListOf("PlayStation 3", "PlayStation 4"),
        arrayListOf("Mac", "Windows"),
        arrayListOf("Linux", "Mac", "Windows", "Xbox 360"),
        arrayListOf("Windows", "PlayStation 4", "PlayStation 5"),
        arrayListOf("Nintendo Switch", "Windows", "PlayStation 3", "PlayStation 4", "Xbox 360"),
        arrayListOf("Windows", "PlayStation 3", "PlayStation 4", "Xbox 360"),
        arrayListOf("Windows", "PlayStation 4")
    )
    var genres = arrayOf(
        arrayListOf("Ролевая игра", "Стратегия"),
        arrayListOf("Ролевая игра", "Стратегия"),
        arrayListOf("Стратегия", "Cимулятор"),
        arrayListOf("Action-adventure", "Шутер от превого лица"),
        arrayListOf("Action-adventure", "Шутер от превого лица"),
        arrayListOf("Ролевая игра"),
        arrayListOf("Платформер", "Головоломка"),
        arrayListOf("Платформер"),
        arrayListOf("Action-adventure", "Ролевая игра"),
        arrayListOf("Приключения", "Платформер"),
        arrayListOf("Action-adventure", "Шутер"),
        arrayListOf("Action-Adventure"),
        arrayListOf("Action-Adventure"),
        arrayListOf("Шутер от превого лица"),
        arrayListOf("Action-Adventure"),
        arrayListOf("Карточная игра"),
        arrayListOf("Карточная игра"),
        arrayListOf("Карточная игра", "Стратегия"),
        arrayListOf("Карточная игра"),
        arrayListOf("MMORPG"),
        arrayListOf("MMORPG"),
        arrayListOf("Action-Adventure", "Шутер от превого лица"),
        arrayListOf("Action-Adventure", "Реловая игра"),
        arrayListOf("Ролевая игра"),
        arrayListOf("Action-adventure", "Ролевая игра"),
        arrayListOf("Action-adventure", "Ролевая игра"),
        arrayListOf("Action-adventure", "Ролевая игра"),
        arrayListOf("Action-adventure", "Ролевая игра"),
        arrayListOf("Action-adventure", "Ролевая игра"),
        arrayListOf("Action-adventure", "Ролевая игра")
    )
    var developer = arrayOf(
        "Game Freak",
        "Game Freak",
        "Firefly Studios",
        "Irrational Games",
        "2K Marin",
        "Game Freak",
        "Valve Corporation",
        "Nintendo EPD",
        "Bethesda Softworks",
        "Thatgamecompany",
        "Naughty Dog",
        "SCE Studio Santa Monica",
        "Nintendo EPD",
        "Valve Corporation",
        "Nintendo EPD",
        "Blizzard Entertainment",
        "Daniel Mullins Games",
        "Supercell",
        "Wizards of the Coast",
        "Blizzard Entertainment",
        "Nival",
        "Mundfish",
        "CD Projekt RED",
        "P Studio",
        "CD Projekt RED",
        "CD Projekt RED",
        "FromSoftware",
        "FromSoftware",
        "FromSoftware",
        "FromSoftware"
    )
    var publishers = arrayOf(
        arrayListOf("Nintendo", "The Pokémon Company"),
        arrayListOf("Nintendo"),
        arrayListOf("Take-Two Interactive", "God Games"),
        arrayListOf("2K Games", "Take-Two Interactive"),
        arrayListOf("2K Games", "Take-Two Interactive"),
        arrayListOf("Nintendo", "The Pokémon Company"),
        arrayListOf("EA Games", "Valve Corporation"),
        arrayListOf("Nintendo"),
        arrayListOf("Bethesda Softworks"),
        arrayListOf("Sony Computer Entertainment", "Annapurna Interactive"),
        arrayListOf("Sony Computer Entertainment"),
        arrayListOf("Sony Computer Entertainment"),
        arrayListOf("Nintendo"),
        arrayListOf("Valve Corporation"),
        arrayListOf("Nintendo"),
        arrayListOf("Blizzard Entertainment"),
        arrayListOf("Devolver Digital"),
        arrayListOf("Supercell"),
        arrayListOf("Wizards of the Coast"),
        arrayListOf("Blizzard Entertainment"),
        arrayListOf("Astrum Entertainment", "MyGames"),
        arrayListOf("Astrum Entertainment"),
        arrayListOf("CD Projekt RED"),
        arrayListOf("Atlus", "Deep silver"),
        arrayListOf("Atari SA"),
        arrayListOf("CD Projekt RED"),
        arrayListOf("FromSoftware"),
        arrayListOf("Namco Bandai Games"),
        arrayListOf("FromSoftware", "Namco Bandai Games"),
        arrayListOf("Namco Bandai Games")
    )
    var description = arrayOf(
        "Стань тренером покемонов и отправляйся в путешествие по региону Канто, чтобы ловить, тренировать и сражаться с другими тренерами и покемонами.",
        "Стань тренером покемонов и отправляйся в путешествие по региону Канто, чтобы ловить, тренировать и сражаться с другими тренерами и покемонами.",
        "Компьютерная игра в жанре стратегии в реальном времени. Является второй частью серии Stronghold и создана на движке первой игры.Игра посвящена крестовым походам на Ближнем Востоке.",
        "Отправляйтесь в таинственный утопический подводный город Восторг и узнайте, что превратило его в руины, в этой эпопее от первого лица.",
        "Спустя десять лет после событий первой игры Субъект Дельта просыпается и должен разгадать тайну Старших сестер и своего прошлого в разрушенном подводном городе Восторг.",
        "Улучшенная версия игры Pokémon Red / Blue, которая больше напоминает популярное аниме о покемонах. Вместо того, чтобы выбирать основного покемона, игроки получают Пикачу, который следует за ними по пятам.",
        "Игра-головоломка Portal с видом от первого лица, разработанная Valve и выпускниками DigiPen, заставляет испытуемого-человека провести серию изнурительных пространственных экспериментов, проводимых неисправным психопатичным искусственным интеллектом по имени GLaDOS.",
        "Любимый сантехник Nintendo и его новый спутник в форме шляпы путешествуют далеко за пределы Грибного королевства в этом эксклюзивном для Switch 3D-платформере.",
        "Действие пятой части франшизы Bethesda Elder Scrolls разворачивается в одноименной провинции Скайрим, где древняя угроза драконов, возглавляемая зловещим Алдуином, снова восстает, угрожая всем смертным расам. Только игрок, как предсказанный герой Довакиин, может спасти мир от разрушения.",
        "Journey - это третий релиз этой игровой компании для Sony. Путешествуйте по землям, открывая для себя историю древней цивилизации, отправляясь в поход к далекой горе. Отправляйтесь в одиночку или исследуйте мир онлайн с незнакомцами.",
        "Джоэл и Элли должны выжить в постапокалиптическом мире, где смертельный грибок-паразит поражает мозг людей, в этой эксклюзивной приключенческой игре от третьего лица для PS3 от Naughty Dog.",
        "Играйте за спартанского воина Кратоса, который прокладывает себе путь сквозь армии мифологических существ, чтобы убить Ареса, бога войны, и получить искупление за свои прошлые грехи.",
        "Забудьте все, что вы знали об играх Legend of Zelda. Окунитесь в мир открытий, исследований и приключений в The Legend of Zelda: Breath of the Wild, новой игре из знаменитой серии, которая выходит за рамки привычного. Путешествуйте по бескрайним полям, лесам и горным вершинам, чтобы узнать, что стало с королевством Хайрул в этом потрясающем приключении под открытым небом. Теперь на Nintendo Switch ваше путешествие стало более свободным и открытым, чем когда-либо. Берите свою систему с собой куда угодно и играйте в качестве Link любым удобным для вас способом.",
        "Через несколько лет после катастрофы в Черной Мезе Гордон Фримен выходит из стазиса, чтобы помочь движению сопротивления Синдикату, коллективу транспространственных инопланетян, завоевавших Землю.",
        "Первая 3D-игра Legend of Zelda, Ocarina of Time, была создана для Nintendo 64 в 1998 году и представила инновационные механики, такие как Z-таргетинг, а также многие другие фирменные знаки серии. Многие издания часто называли ее величайшей игрой всех времен.",
        "Бесплатная коллекционная карточная игра от Blizzard Entertainment, действие которой разворачивается во вселенной Warcraft.",
        "Inscryption - это карточная одиссея, в которой сочетаются ролевые игры в стиле декстроительства, головоломки в стиле escape room и психологический хоррор.",
        "Мобильная бесплатная стратегическая игра в реальном времени на арене от создателей Clash of Clans, сочетающая в себе элементы коллекционных карточных игр и MOBA.",
        "MTG Arena - это цифровая коллекционная карточная игра от Wizards of the Coast.",
        "World of Warcraft - это MMORPG, действие которой разворачивается во вселенной Warcraft от Blizzard Entertainment. На пике своего развития она насчитывала более 12,5 миллионов подписчиков, что делало ее самой популярной MMO всех времен.",
        "Allods Online - это MMORPG по вселенной Allods, первая однопользовательская RPG, выпущенная в России.",
        "Приключенческий шутер от первого лица, действие которого разворачивается в Советском Союзе в альтернативной реальности.",
        "Третий \"Ведьмак\" от CD Projekt RED сочетает в себе нелинейное повествование серии с обширным открытым миром, который завершает сагу о Геральте из Ривии.",
        "Шестая основная часть продолжительной серии Persona, \"Персона 5\", рассказывает о группе старшеклассников (и коте), которые подрабатывают Призрачными ворами, чтобы реформировать общество по одному прогнившему взрослому за раз.",
        "Станьте ведьмаком, Геральтом из Ривии, легендарным истребителем монстров, попавшим в паутину интриг, сплетенных силами, борющимися за власть над миром. Принимайте сложные решения и живите с последствиями в игре, которая погрузит вас в необыкновенную историю, как никакая другая.",
        "Продолжение ролевой игры The Witcher, получившей признание критиков в 2007 году. В этом сюжетном приключении игроки снова получают управление Геральтом из Ривии.",
        "Elden Ring от FromSoftware - это совместная работа Хидэтаки Миядзаки и Джорджа Р.Р. Мартина.",
        "Квази-продолжение экшн-ролевой игры Demon's Souls от From Software, действие которой разворачивается в новой вселенной, сохраняя при этом большую часть основного игрового процесса и высокий уровень сложности. В игре менее линейный мир, новая система контрольных точек в виде костров и уникальная система гуманизма.",
        "DARK SOULS II, разработанная компанией FROM SOFTWARE, является долгожданным продолжением изнурительного хита 2011 года Dark Souls. Уникальная олдскульная ролевая игра в жанре action RPG поразила воображение геймеров по всему миру невероятными испытаниями и сильной эмоциональной отдачей.",
        "Dark Souls продолжает расширять границы в последней, амбициозной главе признанной критиками и определяющей жанр серии. Приготовьтесь и примите Тьму!",
    )
    var date = arrayOf(
        "7 сентября 2004",
        "27 февраля 1996",
        "25 сентября 2002",
        "21 августа 2007",
        "9 февраля 2010",
        "12 сентября 1998",
        "10 октября 2007",
        "27 октября 2017",
        "11 ноября 2011",
        "13 марта 2012",
        "14 июня 2013",
        "22 марта 2005",
        "3 марта 2017",
        "16 ноября 2004",
        "21 ноябра 1998",
        "11 марта 2014",
        "19 октября 2021",
        "4 июня 2016",
        "26 сентября 2019",
        "23 ноября 2004",
        "1 октября 2009",
        "21 февраля 2023",
        "19 мая 2015",
        "15 сентября 2016",
        "26 октября 2007",
        "17 мая 2011",
        "25 февраля 2022",
        "22 сентября 2011",
        "11 марта 2014",
        "24 марта 2016"
    )
    var path = arrayOf(
        "games/PokemonFireRedLeafGreen.png",
        "games/PokemonRedBlue.jpg",
        "games/StrongholdCrusader.jpg",
        "games/BioShock.jpg",
        "games/BioShock2.jpg",
        "games/PokemonYellowSpecialPikachuEdition.png",
        "games/Portal.png",
        "games/SuperMarioOdyssey.png",
        "games/TheElderScrollsVSkyrim.png",
        "games/Journey.png",
        "games/TheLastofUs.jpg",
        "games/GodofWar.png",
        "games/TheLegendofZeldaBreathoftheWild.jpg",
        "games/Half-Life2.jpg",
        "games/TheLegendofZeldaOcarinaofTime.png",
        "games/Hearthstone.png",
        "games/Inscryption.jpg",
        "games/ClashRoyale.jpg",
        "games/MagicTheGatheringArena.jpg",
        "games/WorldofWarcraft.png",
        "games/AllodsOnline.png",
        "games/AtomicHeart.jpg",
        "games/TheWitcher3.jpg",
        "games/Persona5.jpg",
        "games/TheWitcher.jpg",
        "games/TheWitcher2.jpg",
        "games/EldenRing.png",
        "games/DarkSouls.jpg",
        "games/DarkSouls2.jpg",
        "games/DarkSouls3.png"
    )

    fun setData() {
        val db = Firebase.firestore
        var id = 100001
        for (i in 0..names.size - 1) {
            db.collection("game").document(id.toString()).collection("rating")
            db.collection("game").document(id.toString()).set(
                Game(
                    id,
                    names[i],
                    platforms[i],
                    genres[i],
                    developer[i],
                    publishers[i],
                    description[i],
                    date[i],
                    path[i]
                )
            )
            id++;
        }
    }

    var newsHeadline = arrayOf(
        "Но синтетическое тестирование",
        "Сложно сказать, почему представители современных социальных резервов",
        "Внезапно, сторонники тоталитаризма",
        "Задача организации, в особенности же курс на социально-ориентированный национальный проект",
        "Также как граница обучения кадров",
        "С другой стороны, современная методология",
        "Есть над чем задуматься",
        "Значимость этих проблем настолько очевидна",
        "Высокий уровень вовлечения представителей целевой аудитории",
        "С учётом сложившейся международной обстановки"
    )
    var newsDescription = arrayOf(
        "Но синтетическое тестирование создаёт необходимость включения в производственный план целого ряда внеочередных мероприятий с учётом комплекса глубокомысленных рассуждений. Являясь всего лишь частью общей картины, сторонники тоталитаризма в науке, инициированные исключительно синтетически, обнародованы. В целом, конечно, современная методология разработки играет определяющее значение для экспериментов, поражающих по своей масштабности и грандиозности.",
        "Сложно сказать, почему представители современных социальных резервов формируют глобальную экономическую сеть и при этом — представлены в исключительно положительном свете. С другой стороны, сплочённость команды профессионалов играет важную роль в формировании позиций, занимаемых участниками в отношении поставленных задач. В целом, конечно, постоянное информационно-пропагандистское обеспечение нашей деятельности обеспечивает широкому кругу (специалистов) участие в формировании новых предложений. Прежде всего, синтетическое тестирование играет важную роль в формировании дальнейших направлений развития. Ясность нашей позиции очевидна: социально-экономическое развитие предоставляет широкие возможности для новых предложений. Современные технологии достигли такого уровня, что синтетическое тестирование обеспечивает актуальность глубокомысленных рассуждений.",
        "Внезапно, сторонники тоталитаризма в науке ограничены исключительно образом мышления. Таким образом, существующая теория представляет собой интересный эксперимент проверки благоприятных перспектив.",
        "Задача организации, в особенности же курс на социально-ориентированный национальный проект прекрасно подходит для реализации своевременного выполнения сверхзадачи. Учитывая ключевые сценарии поведения, семантический разбор внешних противодействий прекрасно подходит для реализации системы обучения кадров, соответствующей насущным потребностям. Прежде всего, социально-экономическое развитие говорит о возможностях системы массового участия. Учитывая ключевые сценарии поведения, укрепление и развитие внутренней структуры однозначно фиксирует необходимость приоретизации разума над эмоциями. А также реплицированные с зарубежных источников, современные исследования призывают нас к новым свершениям, которые, в свою очередь, должны быть заблокированы в рамках своих собственных рациональных ограничений. Равным образом, реализация намеченных плановых заданий обеспечивает актуальность как самодостаточных, так и внешне зависимых концептуальных решений. Имеется спорная точка зрения, гласящая примерно следующее: сделанные на базе интернет-аналитики выводы указаны как претенденты на роль ключевых факторов. Лишь активно развивающиеся страны третьего мира призывают нас к новым свершениям, которые, в свою очередь, должны быть призваны к ответу. Лишь независимые государства объективно рассмотрены соответствующими инстанциями. Наше дело не так однозначно, как может показаться: постоянный количественный рост и сфера нашей активности предполагает независимые способы реализации позиций, занимаемых участниками в отношении поставленных задач.",
        "Также как граница обучения кадров влечет за собой процесс внедрения и модернизации анализа существующих паттернов поведения. Банальные, но неопровержимые выводы, а также стремящиеся вытеснить традиционное производство, нанотехнологии рассмотрены исключительно в разрезе маркетинговых и финансовых предпосылок. В рамках спецификации современных стандартов, действия представителей оппозиции рассмотрены исключительно в разрезе маркетинговых и финансовых предпосылок. Однозначно, активно развивающиеся страны третьего мира обнародованы.",
        "С другой стороны, современная методология разработки говорит о возможностях первоочередных требований. Как уже неоднократно упомянуто, некоторые особенности внутренней политики представлены в исключительно положительном свете. В рамках спецификации современных стандартов, некоторые особенности внутренней политики набирают популярность среди определенных слоев населения, а значит, должны быть указаны как претенденты на роль ключевых факторов.",
        "Есть над чем задуматься: реплицированные с зарубежных источников, современные исследования, превозмогая сложившуюся непростую экономическую ситуацию, указаны как претенденты на роль ключевых факторов. Мы вынуждены отталкиваться от того, что существующая теория позволяет выполнить важные задания по разработке глубокомысленных рассуждений. В своём стремлении повысить качество жизни, они забывают, что постоянный количественный рост и сфера нашей активности предоставляет широкие возможности для новых принципов формирования материально-технической и кадровой базы. Картельные сговоры не допускают ситуации, при которой некоторые особенности внутренней политики освещают чрезвычайно интересные особенности картины в целом, однако конкретные выводы, разумеется, рассмотрены исключительно в разрезе маркетинговых и финансовых предпосылок. Предварительные выводы неутешительны: перспективное планирование выявляет срочную потребность существующих финансовых и административных условий.",
        "Значимость этих проблем настолько очевидна, что современная методология разработки позволяет выполнить важные задания по разработке первоочередных требований. Предварительные выводы неутешительны: существующая теория представляет собой интересный эксперимент проверки инновационных методов управления процессами. Приятно, граждане, наблюдать, как сделанные на базе интернет-аналитики выводы неоднозначны и будут ассоциативно распределены по отраслям.",
        "Высокий уровень вовлечения представителей целевой аудитории является четким доказательством простого факта: граница обучения кадров предопределяет высокую востребованность переосмысления внешнеэкономических политик. Прежде всего, экономическая повестка сегодняшнего дня, в своём классическом представлении, допускает внедрение системы массового участия. Значимость этих проблем настолько очевидна, что современная методология разработки предоставляет широкие возможности для вывода текущих активов! Являясь всего лишь частью общей картины, элементы политического процесса освещают чрезвычайно интересные особенности картины в целом, однако конкретные выводы, разумеется, разоблачены.",
        "С учётом сложившейся международной обстановки, сплочённость команды профессионалов позволяет оценить значение как самодостаточных, так и внешне зависимых концептуальных решений. В целом, конечно, семантический разбор внешних противодействий требует анализа поставленных обществом задач! Принимая во внимание показатели успешности, высококачественный прототип будущего проекта предоставляет широкие возможности для существующих финансовых и административных условий."
    )
    var newsImage = arrayOf(
        "news/1.jpg",
        "news/2.jpg",
        "news/3.jpg",
        "news/4.jpg",
        "news/5.jpg",
        "news/6.jpg",
        "news/7.jpg",
        "news/8.jpg",
        "news/9.jpg",
        "news/10.jpg"
    )
    var newsDate = arrayOf(
        "19.01.2024",
        "20.01.2024",
        "21.01.2024",
        "22.01.2024",
        "23.01.2024",
        "24.01.2024",
        "25.01.2024",
        "26.01.2024",
        "27.01.2024",
        "28.01.2024"
    )

    fun setNews() {
        val db = Firebase.firestore
        var id = 1
        for (i in 0..newsHeadline.size - 1) {
            db.collection("news").document(id.toString()).set(
                News(
                    id,
                    newsImage[i],
                    newsHeadline[i],
                    newsDescription[i],
                    newsDate[i]
                )
            )
            id++;
        }
    }
    var publisher = arrayOf(
        "Take-Two Interactive",
        "God Games",
        "Nintendo",
        "The Pokemon Company",
        "2K Games",
        "EA Games",
        "Valve Corporation",
        "Bethesda Softworks",
        "Sony Computer Entertainment",
        "Blizzard Entertainment",
        "Valve Corporation",
        "Devolver Digital",
        "Supercell",
        "Wizards of the Coast",
        "Astrum Entertainment",
        "MyGames",
        "Atlus",
        "Deep silver",
        "FromSoftware",
        "CD Projekt RED",
        "Namco Bandai Games",
        "Annapurna Interactive",
        "Atari SA"
    )
    var developers = arrayOf(
        "Firefly Studios",
        "Game Freak",
        "Irrational Games",
        "2K Marin",
        "Valve Corporation",
        "Nintendo EPD",
        "Bethesda Softworks",
        "Thatgamecompany",
        "Valve Corporation",
        "SCE Studio Santa Monica",
        "Naughty Dog",
        "Blizzard Entertainment",
        "Daniel Mullins Games",
        "Supercell",
        "Wizards of the Coast",
        "Mundfish",
        "Nival",
        "P Studio",
        "FromSoftware",
        "CD Projekt RED",
        "FromSoftware"
    )
    var genre = arrayOf(
        "Стратегия",
        "Симулятор",
        "Ролевая игра",
        "Платформер",
        "Головоломка",
        "Action-adventure",
        "Шутер",
        "Шутер от первого лица",
        "Приключения",
        "Карточная игра",
        "MMORPG",
    )
    var platform = arrayOf(
        "Мас",
        "Windows",
        "Game Boy Advance",
        "Game Boy",
        "Play Station 3",
        "Xbox 360",
        "Nintendo 3DS",
        "Linux",
        "Аndroid",
        "Play Station 4",
        "Play Station 5",
        "iPhone",
        "Nintendo Switch"
    )
    fun setPublDevGenrePlatf(){
        val db = Firebase.firestore
        for (i in 0..publisher.size - 1) {
            db.collection("publishers").document(i.toString()).set(hashMapOf("publisher" to publisher[i]))
        }
        for (i in 0..developers.size - 1) {
            db.collection("developers").document(i.toString()).set(hashMapOf("developer" to developers[i]))
        }
        for (i in 0..genre.size - 1) {
            db.collection("genres").document(i.toString()).set(hashMapOf("genre" to genre[i]))
        }
        for (i in 0..platform.size - 1) {
            db.collection("platforms").document(i.toString()).set(hashMapOf("platform" to platform[i]))
        }
    }
}