package dtos;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.mysql.fabric.xmlrpc.base.Array;

import models.Contact;

@Root(name = "people", strict = false)
public class PeopleDto {

	@ElementList(inline = true, required = false)
	private List<PersonDto> list;

	public List<PersonDto> getList() {
		return list;
	}

	public void setList(List<PersonDto> list) {
		this.list = list;
	}
	
	public List<Contact> toContactList() {
		
		List<Contact> contacts = new ArrayList<>();
		
		if(this.list != null) {
			for(PersonDto personDto : this.list) {
				contacts.add(personDto.toContactModel());
			}
		}
		
		return contacts;
	}

}
