package com.ah.algov.dto;

/***
 * 
 * DTO holding algorithm information.
 *
 */
public class AlgorithmDTO {

	private String id;
	private String name;
	private String template;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}
