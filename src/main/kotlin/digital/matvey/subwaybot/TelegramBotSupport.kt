package digital.matvey.subwaybot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.model.request.ParseMode.HTML
import com.pengrad.telegrambot.request.EditMessageText
import com.pengrad.telegrambot.request.SendMessage

fun TelegramBot.sendMessage(chatId: Long,
                            text: String,
                            parseMode: ParseMode = HTML,
                            markup: InlineKeyboardMarkup? = null) {
    val params = SendMessage(chatId, text)
            .parseMode(parseMode)
    execute(if (markup == null) params else params.replyMarkup(markup))
}

fun TelegramBot.editMessage(chatId: Long,
                            messageId: Int,
                            text: String,
                            parseMode: ParseMode = HTML,
                            markup: InlineKeyboardMarkup) {
    execute(EditMessageText(chatId, messageId, text)
            .parseMode(parseMode)
            .replyMarkup(markup))
}