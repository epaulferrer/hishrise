package dtos;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import models.Contact;

@Root(name = "companies", strict = false)
public class CompaniesDto {

	@ElementList(inline = true, required = false)
	private List<CompanyDto> list;

	public List<CompanyDto> getList() {
		return list;
	}

	public void setList(List<CompanyDto> list) {
		this.list = list;
	}

	public List<Contact> toContactList() {

		List<Contact> contacts = new ArrayList<>();

		if (this.list != null) {
			for (CompanyDto companyDto : this.list) {
				contacts.add(companyDto.toContactModel());
			}
		}

		return contacts;
	}

}
