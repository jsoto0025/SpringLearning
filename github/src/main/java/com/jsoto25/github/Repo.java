package com.jsoto25.github;

/**
 * GitHub repository class operations
 */
public class Repo {
	/**
	 * Repository name
	 */
	String name = "";
	/**
	 * Repository description
	 */
	String description = "";
	/**
	 * Repository home page URL
	 */
	String homepage = "";
	/**
	 * ???????
	 */
	Boolean autoInit = true;
	

	
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
	
}
