package uz.triples.gdgdevfest.repositories

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import uz.triples.gdgdevfest.database.GDGTashkentDatabase
import uz.triples.gdgdevfest.database.dao.*
import uz.triples.gdgdevfest.database.entities.*
import uz.triples.gdgdevfest.models.SessionsModel

class MainRepository(private val application: Application) {

    private val TAG = "MainRepository"
    private val databaseFireBase = FirebaseDatabase.getInstance().reference
    private val database = GDGTashkentDatabase.getInstance(application)

    fun updateDatabase() {
        databaseFireBase.child("speakers").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val speakersList = mutableListOf<Speakers>()
                for (speaker in snapshot.children) {
                    val newSpeaker = Speakers(
                        speaker.key.toString(),
                        speaker.child("name").value.toString(),
                        speaker.child("shortBio").value.toString(),
                        speaker.child("bio").value.toString(),
                        speaker.child("company").value.toString(),
                        speaker.child("companyLogoUrl").value.toString(),
                        speaker.child("country").value.toString(),
                        speaker.child("photoUrl").value.toString(),
                        speaker.child("title").value.toString(), null, null, null, null, null
                    )
                    for (social in speaker.child("socials").children) {
                        when (social.child("icon").value.toString()) {
                            "twitter" -> {
                                newSpeaker.twitter = social.child("link").value.toString()
                            }
                            "facebook" -> {
                                newSpeaker.facebook = social.child("link").value.toString()
                            }
                            "website" -> {
                                newSpeaker.web = social.child("link").value.toString()
                            }
                            "linkedin" -> {
                                newSpeaker.linkedIn = social.child("link").value.toString()
                            }
                            "instagram" -> {
                                newSpeaker.instagram = social.child("link").value.toString()
                            }
                        }
                    }
                    speakersList += newSpeaker
                }
                InsertSpeakers(database!!.speakersDao()).execute(speakersList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        databaseFireBase.child("sessions").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listOfSessions = mutableListOf<Sessions>()
                for (sessionsItem in snapshot.children) {
                    val session = sessionsItem.key.toString().toInt()
                    val complexity = sessionsItem.child("complexity").value.toString()
                    val description = sessionsItem.child("description").value.toString()
                    val language = sessionsItem.child("language").value.toString()
                    val presentation = sessionsItem.child("presentation").value.toString()
                    val title = sessionsItem.child("title").value.toString()
                    val image = sessionsItem.child("image").value.toString()
                    val tag = sessionsItem.child("tags").child("0").value.toString()
                    val speakersList = mutableListOf<String>()
                    for (speakers in sessionsItem.child("speakers").children) {
                        speakersList += speakers.value.toString()
                    }
                    val sessionsModel = SessionsModel(
                        complexity,
                        description,
                        language,
                        presentation,
                        speakersList,
                        tag,
                        title,
                        image
                    )
                    listOfSessions += Sessions(session, Gson().toJson(sessionsModel))
                }
                InsertSessions(database!!.sessionsDao()).execute(listOfSessions)
                Toast.makeText(application, "Updated Successfully", Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(application, "Problem during update", Toast.LENGTH_LONG).show()
            }
        })

        databaseFireBase.child("schedule").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var date: String?
                var idHelper = 1
                for (snapshotDate in snapshot.children) {
                    val listAgenda = mutableListOf<Agenda>()
                    date = snapshotDate.child("dateReadable").value.toString()
                    for (timeSlot in snapshotDate.child("timeslots").children) {
                        val startTime = timeSlot.child("startTime").value.toString()
                        val endTime = timeSlot.child("endTime").value.toString()
                        val listSessions = mutableListOf<Int>()
                        for (sessions in timeSlot.child("sessions").children) {
                            for (items in sessions.child("items").children) {
                                listSessions += items.value.toString().toInt()
                            }
                        }

                        val agenda =
                            Agenda(
                                idHelper * 100 + timeSlot.key.toString().toInt(),
                                date,
                                endTime,
                                startTime,
                                Gson().toJson(listSessions)
                            )
                        listAgenda += agenda
                    }
                    InsertAgenda(database!!.agendaDao()).execute(listAgenda)
                    idHelper++
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        databaseFireBase.child("team").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val membersList = mutableListOf<TeamMembers>()
                var idHelper = 1
                for (team in snapshot.children) {
                    val teamName = team.child("title").value.toString()
                    val membersCount = team.child("members").childrenCount.toInt()
                    for (member in team.child("members").children) {
                        val name = member.child("name").value.toString()
                        val photo = member.child("photoUrl").value.toString()
                        val responsible = member.child("title").value.toString()
                        val order = member.child("order").value.toString().toInt()
                        var twitter: String? = null
                        var facebook: String? = null
                        var linkedIn: String? = null
                        var instagram: String? = null
                        var web: String? = null
                        for (social in member.child("socials").children) {
                            when (social.child("icon").value.toString()) {
                                "twitter" -> {
                                    twitter = social.child("link").value.toString()
                                }
                                "facebook" -> {
                                    facebook = social.child("link").value.toString()
                                }
                                "website" -> {
                                    web = social.child("link").value.toString()
                                }
                                "linkedin" -> {
                                    linkedIn = social.child("link").value.toString()
                                }
                                "instagram" -> {
                                    instagram = social.child("link").value.toString()
                                }
                            }
                        }
                        membersList += TeamMembers(
                            idHelper * 100 + order,
                            teamName, name, order, photo, responsible,
                            twitter, facebook, linkedIn, instagram, web
                        )
                    }
                    InsertTeam(database!!.teamDao()).execute(Team(teamName, membersCount))
                    idHelper++
                }
                InsertTeamMembers(database!!.teamMembersDao()).execute(membersList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        databaseFireBase.child("partners").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var idHelper = 1
                for (typePartners in snapshot.children) {
                    val title = typePartners.child("title").value.toString()
                    for (partners in typePartners.child("logos").children) {
                        val logoUrl = partners.child("logoUrl").value.toString()
                        val name = partners.child("name").value.toString()
                        val url = partners.child("url").value.toString()
                        Log.d(TAG, "$name $title")
                        val order = partners.child("order").value.toString().toInt()
                        InsertSponsor(database!!.sponsorsDao()).execute(
                            Sponsors(
                                idHelper * 100 + order,
                                title,
                                name,
                                logoUrl,
                                order,
                                url
                            )
                        )
                    }
                    idHelper++
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        databaseFireBase.child("FAQ").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (faq in snapshot.children) {
                    InsertFAQ(database!!.faqDao()).execute(
                        FAQ(
                            faq.key!!.toInt(),
                            faq.child("question").value.toString(),
                            faq.child("answer").value.toString()
                        )
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


    companion object {
        class InsertAgenda(private val dao: AgendaDao) : AsyncTask<List<Agenda>, Void, Void>() {
            override fun doInBackground(vararg params: List<Agenda>?): Void? {
                for (agenda in params[0]!!) {
                    dao.insertAgenda(agenda)
                }
                return null
            }
        }

        class InsertSessions(private val dao: SessionsDao) :
            AsyncTask<List<Sessions>, Void, Void>() {
            override fun doInBackground(vararg params: List<Sessions>?): Void? {
                for (sessions in params[0]!!) {
                    dao.insertSessions(sessions)
                }
                return null
            }
        }

        class InsertSpeakers(private val dao: SpeakersDao) :
            AsyncTask<List<Speakers>, Void, Void>() {
            override fun doInBackground(vararg params: List<Speakers>?): Void? {
                for (speakers in params[0]!!) {
                    dao.insertSpeakers(speakers)
                }
                return null
            }
        }

        class InsertTeamMembers(private val dao: TeamMembersDao) :
            AsyncTask<List<TeamMembers>, Void, Void>() {
            override fun doInBackground(vararg params: List<TeamMembers>?): Void? {
                for (member in params[0]!!) {
                    dao.insertTeamMember(member)
                }
                return null
            }
        }

        class InsertTeam(private val dao: TeamDao) :
            AsyncTask<Team, Void, Void>() {
            override fun doInBackground(vararg params: Team?): Void? {
                dao.insertTeam(params[0]!!)
                return null
            }
        }

        class InsertSponsor(private val dao: SponsorsDao) :
            AsyncTask<Sponsors, Void, Void>() {
            override fun doInBackground(vararg params: Sponsors?): Void? {
                dao.insertSponsors(params[0]!!)
                return null
            }
        }

        class InsertFAQ(private val dao: FAQDao) :
            AsyncTask<FAQ, Void, Void>() {
            override fun doInBackground(vararg params: FAQ?): Void? {
                dao.insertFAQ(params[0]!!)
                return null
            }
        }
    }
}