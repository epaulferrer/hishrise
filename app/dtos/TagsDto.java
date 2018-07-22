package dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import models.Tag;

@Root(name = "tags", strict = false)
public class TagsDto {

	@ElementList(inline = true)
	private List<TagDto> list;

	public List<TagDto> getList() {
		return list;
	}

	public void setList(List<TagDto> list) {
		this.list = list;
	}

	public Set<Tag> toTagModelList() {
		
		Set<Tag> tags = new HashSet();
		
		for (TagDto tagDto : this.list) {
			tags.add(tagDto.toTagModel());
		}
		
		return tags;
		
	}
	
	public TagDto getTagDtoByName(String tagName) {
		for(TagDto tagDto : this.list) {
			if(tagDto.getName().equalsIgnoreCase(tagName)) {
				return tagDto;
			}
		}
		return null;
	}
	
}
