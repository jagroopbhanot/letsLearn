package jagroop.example.blogPostAPI.blog;

import java.util.Date;

public class Blog {
	
	private String blogWrittenBy;
	
	private Integer id;
	
	private String blogText;
	
	private Date blogDate;

	public Blog(Integer id, String blogWrittenBy, String blogText, Date blogDate) 
	{
		super();
		this.id = id;
		this.blogWrittenBy = blogWrittenBy;
		this.blogText = blogText;
		this.blogDate = blogDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBlogWrittenBy() {
		return blogWrittenBy;
	}

	public void setBlogWrittenBy(String blogWrittenBy) {
		this.blogWrittenBy = blogWrittenBy;
	}

	public String getBlogText() {
		return blogText;
	}

	public void setBlogText(String blogText) {
		this.blogText = blogText;
	}

	public Date getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(Date blogDate) {
		this.blogDate = blogDate;
	}

	
}