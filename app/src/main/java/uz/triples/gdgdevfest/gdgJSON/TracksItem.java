package uz.triples.gdgdevfest.gdgJSON;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class TracksItem implements Serializable{

	@SerializedName("title")
	public String title;
}