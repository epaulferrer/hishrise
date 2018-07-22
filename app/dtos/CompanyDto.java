package dtos;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import models.Contact;

@Root(name = "company", strict = false)
public class CompanyDto {

	@Element
	private String name;

	@Element(name = "tags")
	private TagsDto tags;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TagsDto getTags() {
		return tags;
	}

	public void setTags(TagsDto tags) {
		this.tags = tags;
	}
	
	public Contact toContactModel() {
		Contact contact = new Contact(this.name, true, this.tags.toTagModelList());
		
		return contact;
	}

}