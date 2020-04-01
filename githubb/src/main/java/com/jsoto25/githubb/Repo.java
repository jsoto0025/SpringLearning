package com.jsoto25.githubb;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GitHub repository class operations
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Repo {
	/**
	 * Repository name
	 */
	@JsonProperty("name")
	public String name = "";
	/**
	 * Repository description
	 */
	@JsonProperty("description")
	public String description = "";
	/**
	 * Repository home page URL
	 */
	@JsonProperty("homepage")
	public String homepage = "";
	/**
	 * ???????
	 */
	@JsonIgnore
	public Boolean autoInit = true;
	
	@JsonIgnore
	/**
	 * Repository class construtor for initialization.
	 * @param name Repository name
	 * @param description Repository descriptiom
	 * @param homepage Repository home page URL
	 */
	public Repo(String name, String description, String homepage){
		this.name=name;
		this.description=description;
		this.homepage=homepage;
	}

	public Repo()
	{
		super();
	}
	
}
