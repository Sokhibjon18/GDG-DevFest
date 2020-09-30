package uz.triples.gdgdevfest.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.triples.gdgdevfest.database.entities.TeamMembers

@Dao
interface TeamMembersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeamMember(teamMembers: TeamMembers)

    @Query("SELECT * From teamMembers where teamName = :teamName")
    fun getAllTeamMembers(teamName: String?): List<TeamMembers>
}