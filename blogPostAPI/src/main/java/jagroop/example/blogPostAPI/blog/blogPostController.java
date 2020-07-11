package jagroop.example.blogPostAPI.blog;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class blogPostController {

	@Autowired
	private BlogDaoService blogDao;
	
	@GetMapping(path = "/blogs")
	public List<Blog> GetAllBlogs()
	{
		return blogDao.findAll();
	}
	
	@PostMapping(path = "/blogs")
	public ResponseEntity<Object> createBlog(@RequestBody Blog user)
	{
		Blog savedUser = blogDao.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/blogs/{id}")
	public void deleteUser(@PathVariable int id)
	{
		Blog user =  blogDao.deleteById(id);
		
	}
}
