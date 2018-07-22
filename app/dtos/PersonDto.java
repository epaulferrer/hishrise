package dtos;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import models.Contact;

@Root(name = "person", strict = false)
public class PersonDto {

	@Element(name = "first-name")
	private String firstName;

	@Element(name = "last-name")
	private String lastName;

	@Element(name = "tags")
	private TagsDto tags;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public TagsDto getTags() {
		return tags;
	}

	public void setTags(TagsDto tags) {
		this.tags = tags;
	}
	
	public Contact toContactModel() {
		Contact contact = new Contact(this.firstName + " " + this.lastName, this.tags.toTagModelList());
		
		return contact;
	}

}
