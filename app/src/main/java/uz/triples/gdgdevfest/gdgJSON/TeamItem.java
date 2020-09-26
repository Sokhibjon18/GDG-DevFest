package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.io.Serializable;

public class TeamItem implements Serializable{

	@SerializedName("members")
	public List<MembersItem> members;

	@SerializedName("title")
	public String title;
}