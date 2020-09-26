package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class SocialsItem implements Serializable{

	@SerializedName("icon")
	public String icon;

	@SerializedName("link")
	public String link;

	@SerializedName("name")
	public String name;
}