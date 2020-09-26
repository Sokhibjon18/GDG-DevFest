package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.io.Serializable;

public class TimeslotsItem implements Serializable{

	@SerializedName("sessions")
	public List<SessionsItem> sessions;

	@SerializedName("startTime")
	public String startTime;

	@SerializedName("endTime")
	public String endTime;
}