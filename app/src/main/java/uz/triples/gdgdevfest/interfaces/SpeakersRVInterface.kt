package uz.triples.gdgdevfest.interfaces

import uz.triples.gdgdevfest.models.AgendaItemModel
import java.io.Serializable

interface SpeakersRVInterface{
    fun getFullInfo(agendaItemModel: AgendaItemModel)
}