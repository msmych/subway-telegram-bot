package digital.matvey.subwaybot

const val START_COMMAND = "/start"
const val HELP_COMMAND = "/help"
const val LINES_COMMAND = "/lines"

const val LINES_CALLBACK_DATA = "lines"

const val LINES_TEXT = "Subway lines:"

const val BACK_TO_LINES_TEXT = "◀️ Back to lines"
val START_TEXT = """
    Welcome to Nizhny Novgorod subway!
    
    Send /lines command to start
    
    ⬇️ Also you can save the map below for offline use
    """.trimIndent()
const val HELP_TEXT = "Send /lines command to see the list of lines"
const val NN_MAP_PDF_URI = "https://nizhny800.ru/assets/uploads/user/2269/29/63/Presentation.pdf"