package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.io.Serializable;
public class JsonMember20160910 implements Serializable{

	@SerializedName("dateReadable")
	public String dateReadable;

	@SerializedName("timeslots")
	public List<TimeslotsItem> timeslots;

	@SerializedName("tracks")
	public List<TracksItem> tracks;
}