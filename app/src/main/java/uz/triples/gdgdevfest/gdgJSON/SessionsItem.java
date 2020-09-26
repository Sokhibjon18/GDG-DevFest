package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.io.Serializable;
public class SessionsItem implements Serializable{

	@SerializedName("items")
	public List<Integer> items;
}