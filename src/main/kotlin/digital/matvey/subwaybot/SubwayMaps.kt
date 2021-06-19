package digital.matvey.subwaybot

import digital.matvey.subwaybot.line.Line
import digital.matvey.subwaybot.station.Station

val NN_LINES = listOf(
        Line("Автозаводская",
                "\uD83D\uDD341️⃣",
                Station("Сенная"),
                Station("Оперный театр"),
                Station("Горьковская"),
                Station("Московская"),
                Station("Чкаловская"),
                Station("Ленинская"),
                Station("Заречная"),
                Station("Двигатель Революции"),
                Station("Пролетарская"),
                Station("Автозаводская"),
                Station("Комсомольская"),
                Station("Кировская"),
                Station("Парк культуры")),
        Line("Сормовско-Мещерская",
                "\uD83D\uDD352️⃣",
                Station("Стрелка"),
                Station("Московская"),
                Station("Канавинская"),
                Station("Бурнаковская"),
                Station("Буревестник"),
                Station("Варя")
))
        .associateBy { l -> l.name }