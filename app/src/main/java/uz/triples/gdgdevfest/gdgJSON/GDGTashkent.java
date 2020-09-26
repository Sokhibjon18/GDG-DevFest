package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.io.Serializable;
public class GDGTashkent implements Serializable{

	@SerializedName("schedule")
	public Schedule schedule;

	@SerializedName("sessions")
	public Sessions sessions;

	@SerializedName("partners")
	public List<PartnersItem> partners;

	@SerializedName("speakers")
	public Speakers speakers;

	@SerializedName("team")
	public List<TeamItem> team;
}