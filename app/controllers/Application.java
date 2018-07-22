package controllers;

import play.*;
import play.data.validation.Required;
import play.db.jpa.JPA;
import play.mvc.*;
import services.HighRiseResource;
import services.ResourceSerializer;

import java.util.*;

import javax.persistence.Query;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

import dtos.CompaniesDto;
import dtos.CompanyDto;
import dtos.PeopleDto;
import dtos.TagDto;
import dtos.TagsDto;
import models.*;
import models.Contact;
public class Application extends Controller {

	public static void index() {
		render("Contact/index.html");
	}

	/**
	 *  Pulls resource from HighRise API then converts to JPA entity > save to database.
	 * */
	public static void searchContactBytag(@Required String tagName) {
	   if(validation.hasErrors()) {
		   render("Contact/index.html");
        }
		
		String tagsXml = HighRiseResource.instance().getResource(HighRiseResource.TAGS_RESOURCE);
		TagDto tagDto = ResourceSerializer.instance().read(TagsDto.class, tagsXml).getTagDtoByName(tagName);

		if (tagDto == null) {
			boolean noTagFound = true;
			render("Contact/index.html", noTagFound, tagName);
			return;
		}

		String peopleXml = HighRiseResource.instance()
				.getResource(HighRiseResource.PEOPLE_BY_TAG_RESOURCE + tagDto.getId());
		String companiesXml = HighRiseResource.instance()
				.getResource(HighRiseResource.COMPANIES_BY_TAG_RESOURCE + tagDto.getId());

		PeopleDto people = ResourceSerializer.instance().read(PeopleDto.class, peopleXml);
		CompaniesDto companies = ResourceSerializer.instance().read(CompaniesDto.class, companiesXml);

		List<Contact> contacts = new ArrayList<>();

		contacts.addAll(people.toContactList());
		contacts.addAll(companies.toContactList());

		for (Contact contact : contacts) {
			contact.save();
		}
		
		String q = "SELECT c.* FROM contact c left join contact_tag ct on ct.contact_id = c.id "
				+ " left join tag t on t.id = ct.tag_id where t.tagid = :tagId";
		Tag tag = Tag.find("byTagId", tagDto.getId()).first();
		
	    Query query = JPA.em().createNativeQuery(q, Contact.class).setParameter("tagId", tag.getTagId());
	    
	    contacts = query.getResultList(); // Return the contacts that are saved in the database.
	    
	    render("Contact/index.html", contacts);
	}

}