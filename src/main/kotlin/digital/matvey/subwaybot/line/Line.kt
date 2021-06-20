package digital.matvey.subwaybot.line

import digital.matvey.subwaybot.station.Station

data class Line internal constructor(
    val id: String,
    val name: String,
    val symbol: String,
    val stations: List<Station>
) {

    constructor(id: String, name: String, symbol: String, vararg stations: Station) : this(
        id,
        name,
        symbol,
        stations.toList()
    ) {
        stations.forEach { s -> s.line = this }
    }

    fun fullName() = "$symbol $name"

    fun fullNameHtml() = "$symbol <b>$name</b>"
}