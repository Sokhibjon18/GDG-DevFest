package uz.triples.gdgdevfest.models

import java.io.Serializable

class AgendaItemModel(
    val startTime: String?,
    val duration: String?,
    val date: String?,
    val complexity: String?,
    val description: String?,
    val language: String?,
    val presentation: String?,
    var speakers: List<String?>,
    var degree: String?,
    val title: String?,
    var image: String?
) : Serializable {
}