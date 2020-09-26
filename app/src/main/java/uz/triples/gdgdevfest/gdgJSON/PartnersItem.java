package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class PartnersItem implements Serializable{

	@SerializedName("title")
	public String title;

	@SerializedName("logos")
	public List<LogosItem> logos;

	@SerializedName("order")
	public int order;
}