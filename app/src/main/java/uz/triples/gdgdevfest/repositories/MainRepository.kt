package uz.triples.gdgdevfest.repositories

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.triples.gdgdevfest.database.GDGTashkentDatabase
import uz.triples.gdgdevfest.database.dao.AgendaDao
import uz.triples.gdgdevfest.database.dao.SessionsDao
import uz.triples.gdgdevfest.database.entities.Agenda
import uz.triples.gdgdevfest.database.entities.Sessions
import uz.triples.gdgdevfest.database.entities.SessionsModel

class MainRepository(private val application: Application) {

    private val TAG = "AgendaRepository"
    private val databaseFireBase = FirebaseDatabase.getInstance().reference
    private val database = GDGTashkentDatabase.getInstance(application)

    fun updateAgenda(){
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
                                "$date $startTime $endTime ${Gson().toJson(listSessions)}",
                                date,
                                endTime,
                                startTime,
                                Gson().toJson(listSessions)
                            )
                        listAgenda += agenda
                    }
                    InsertAgenda(database!!.agendaDao()).execute(listAgenda)
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

        class InsertSessions(private val dao: SessionsDao) : AsyncTask<List<Sessions>, Void, Void>() {
            override fun doInBackground(vararg params: List<Sessions>?): Void? {
                for (sessions in params[0]!!) {
                    dao.insertSessions(sessions)
                }
                return null
            }
        }
    }
}