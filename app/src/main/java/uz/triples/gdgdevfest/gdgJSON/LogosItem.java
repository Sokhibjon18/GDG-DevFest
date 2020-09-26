package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class LogosItem implements Serializable{

	@SerializedName("name")
	public String name;

	@SerializedName("logoUrl")
	public String logoUrl;

	@SerializedName("url")
	public String url;

	@SerializedName("order")
	public int order;
}