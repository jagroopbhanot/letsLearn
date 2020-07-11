package jagroop.example.blogPostAPI.blog;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BlogDaoService {
	
	private static List<Blog> blogs = new ArrayList<>();
	
	private static int blogsCount = 3;
	
	static
	{
		blogs.add(new Blog(1,"Jagroop", "This is my first blog", new Date()));
		blogs.add(new Blog(2,"Dayna", "This is my second blog", new Date()));
		blogs.add(new Blog(3,"Steve", "This is my third blog", new Date()));
	}

	public List<Blog> findAll()
	{
		return blogs;
	}
	
	public Blog save(Blog blog)
	{
		if(null == blog.getId())
		{
			blog.setId(++blogsCount);
		}
		blog.setBlogDate(new Date());
		blogs.add(blog);
		return blog;
	}
	
	public Blog deleteById(int id)
	{
		Iterator<Blog> itr = blogs.iterator();
		
		while(itr.hasNext())
		{
			Blog blog = itr.next();
			if(blog.getId() == id)
			{
				itr.remove();
				return blog;
			}
		}
		return null;
	}	
}
