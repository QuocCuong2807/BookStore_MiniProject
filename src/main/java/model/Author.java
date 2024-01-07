package model;

import java.sql.Date;

public class Author {
	private String authorId;
	private String authorName;
	private Date dayOfBirth;
	
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Author(String authorId, String authorName, Date dayOfBirth) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.dayOfBirth = dayOfBirth;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	
	
}
