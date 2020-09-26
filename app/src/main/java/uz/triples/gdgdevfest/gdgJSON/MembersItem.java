package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.io.Serializable;
public class MembersItem implements Serializable{

	@SerializedName("photoUrl")
	public String photoUrl;

	@SerializedName("name")
	public String name;

	@SerializedName("photo")
	public String photo;

	@SerializedName("socials")
	public List<SocialsItem> socials;

	@SerializedName("title")
	public String title;

	@SerializedName("order")
	public int order;
}