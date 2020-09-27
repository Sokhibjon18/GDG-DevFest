package uz.triples.gdgdevfest.database.entities

class SessionsModel(
    val complexity: String?,
    val description: String?,
    val language: String?,
    val presentation: String?,
    val speakers: List<String>,
    val tags: List<String>,
    val title: String?,
    val image: String?
) {

}