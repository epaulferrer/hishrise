package dtos;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import models.Tag;
import play.db.jpa.Model;

@Root(name = "tag", strict = false)
public class TagDto {

	@Element
	private Integer id;

	@Element
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Tag toTagModel() {
		Tag tag = new Tag(this.id, this.name);
		return tag;
	}

}
