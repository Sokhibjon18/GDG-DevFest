package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.io.Serializable;
public class JsonMember124 implements Serializable{

	@SerializedName("complexity")
	public String complexity;

	@SerializedName("speakers")
	public List<String> speakers;

	@SerializedName("description")
	public String description;

	@SerializedName("language")
	public String language;

	@SerializedName("videoId")
	public String videoId;

	@SerializedName("title")
	public String title;

	@SerializedName("tags")
	public List<String> tags;
}