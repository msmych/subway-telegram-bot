package digital.matvey.subwaybot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.InlineKeyboardButton
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup
import com.pengrad.telegrambot.request.AnswerCallbackQuery
import com.pengrad.telegrambot.request.SendDocument
import digital.matvey.subwaybot.line.Line

private const val SUBWAY_NN_BOT_TOKEN_ENV = "SUBWAY_NN_BOT_TOKEN"

fun main() {
    val bot = TelegramBot(System.getenv(SUBWAY_NN_BOT_TOKEN_ENV))
    bot.setUpdatesListener { updates ->
        updates.forEach { u ->
            bot.startCommand(u)
                    .helpCommand(u)
                    .linesCommand(u)
                    .lineStationsQuery(u)
                    .backToLinesQuery(u)
        }
        CONFIRMED_UPDATES_ALL
    }
    println("READY")
}

private fun TelegramBot.startCommand(update: Update): TelegramBot {
    if (update.message()?.text() == START_COMMAND) {
        val chatId = update.message().chat().id()
        sendMessage(chatId, START_TEXT)
        execute(SendDocument(chatId, NN_MAP_PDF_URI))
    }
    return this
}

private fun TelegramBot.helpCommand(update: Update): TelegramBot {
    if (update.message()?.text() == HELP_COMMAND) {
        sendMessage(update.message().chat().id(), HELP_TEXT)
    }
    return this
}

private fun TelegramBot.linesCommand(update: Update): TelegramBot {
    if (update.message()?.text() == LINES_COMMAND) {
        sendMessage(update.message().chat().id(), LINES_TEXT, markup = linesMarkup())
    }
    return this
}

private fun linesMarkup() = InlineKeyboardMarkup(
        *NN_LINES
                .map { (name, line) -> InlineKeyboardButton(line.fullName()).callbackData(name) }
                .map { b -> arrayOf(b) }
                .toTypedArray())

private fun TelegramBot.backToLinesQuery(update: Update): TelegramBot {
    if (update.callbackQuery()?.data() == LINES_CALLBACK_DATA) {
        val message = update.callbackQuery().message()
        editMessage(message.chat().id(), message.messageId(), LINES_TEXT, markup = linesMarkup())
        execute(AnswerCallbackQuery(update.callbackQuery().id()))
    }
    return this
}

private fun TelegramBot.lineStationsQuery(update: Update): TelegramBot {
    if (NN_LINES.containsKey(update.callbackQuery()?.data())) {
        val line = requireNotNull(NN_LINES[update.callbackQuery().data()])
        val message = update.callbackQuery().message()
        editMessage(message.chat().id(), message.messageId(), line.fullNameHtml(), markup = line.stationsMarkup())
        execute(AnswerCallbackQuery(update.callbackQuery().id()))
    }
    return this
}

private fun Line.stationsMarkup() =
        InlineKeyboardMarkup(
                *stations
                        .map { s -> InlineKeyboardButton(s.name).callbackData(s.name) }
                        .union(listOf(InlineKeyboardButton(BACK_TO_LINES_TEXT).callbackData(LINES_CALLBACK_DATA)))
                        .map { b -> arrayOf(b) }
                        .toTypedArray())