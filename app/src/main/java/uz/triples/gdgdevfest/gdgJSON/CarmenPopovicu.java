package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CarmenPopovicu implements Serializable {

	@SerializedName("country")
	public String country;

	@SerializedName("featured")
	public boolean featured;

	@SerializedName("companyLogo")
	public String companyLogo;

	@SerializedName("bio")
	public String bio;

	@SerializedName("photo")
	public String photo;

	@SerializedName("title")
	public String title;

	@SerializedName("photoUrl")
	public String photoUrl;

	@SerializedName("companyLogoUrl")
	public String companyLogoUrl;

	@SerializedName("name")
	public String name;

	@SerializedName("shortBio")
	public String shortBio;

	@SerializedName("company")
	public String company;

	@SerializedName("socials")
	public List<SocialsItem> socials;

	@SerializedName("order")
	public int order;
}