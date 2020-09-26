package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BadgesItem implements Serializable {

	@SerializedName("name")
	public String name;

	@SerializedName("link")
	public String link;

	@SerializedName("description")
	public String description;
}