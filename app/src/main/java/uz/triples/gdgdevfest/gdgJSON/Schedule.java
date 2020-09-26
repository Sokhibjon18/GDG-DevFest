package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class Schedule implements Serializable{

	@SerializedName("2016-09-09")
	public JsonMember20160909 jsonMember20160909;

	@SerializedName("2016-09-10")
	public JsonMember20160910 jsonMember20160910;
}