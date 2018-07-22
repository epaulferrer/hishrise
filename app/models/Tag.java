package models;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity
public class Tag extends Model {

	@Column
	private Integer tagId;

	@Column
	private String tagName;

	public Tag(Integer tagId, String tagName) {
		this.tagId = tagId;
		this.tagName = tagName;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getTagId(), this.getTagName());
	}

	@Override
	public boolean equals(Object other) {

		if (!(other instanceof Tag))
			return false;

		Tag otherTag = (Tag) other;

		return otherTag.getTagId().equals(this.getTagId()) && otherTag.getTagName().equalsIgnoreCase(this.getTagName());

	}

	@Override
	public <T extends JPABase> T save() {
		Tag tag = this.find("byTagName", this.tagName).first();

		if (tag == null) {
			super.save();
		}

		return (T) tag;
	}

}
