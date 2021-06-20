package digital.matvey.subwaybot.station

import digital.matvey.subwaybot.line.Line

data class Station(val name: String) {

    lateinit var line: Line

    fun id() = "${line.id}|$name"
}