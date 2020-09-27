package uz.triples.gdgdevfest.models

import java.io.Serializable

class AgendaItemModel(
    val startTime: String?,
    val endTime: String?,
    val date: String?,
    val complexity: String?,
    val description: String?,
    val language: String?,
    val presentation: String?,
    val speakers: List<String>,
    val title: String?,
    val image: String?
) : Serializable {
}