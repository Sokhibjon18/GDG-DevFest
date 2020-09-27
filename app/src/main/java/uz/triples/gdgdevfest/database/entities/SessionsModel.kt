package uz.triples.gdgdevfest.database.entities

class SessionsModel(
    val complexity: String?,
    val description: String?,
    val language: String?,
    val presentation: String?,
    val speakers: List<String>,
    val tags: String,
    val title: String?,
    val image: String?
) {

}