package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;
    @Autowired
    private ImageRepository imageRepository;

    public List<Blog> showBlogs(){
        //find all blogs
        return blogRepository1.findAll();
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time

        //updating the blog details

        //Updating the userInformation and changing its blogs
        try{
            Blog newBlog = new Blog(title,content,new Date());
            User user = userRepository1.findById(userId).get();
            newBlog.setUser(user);
            List<Blog> currBlogList = user.getBlogList();
            currBlogList.add(newBlog);
            user.setBlogList(currBlogList);
            blogRepository1.save(newBlog);
            userRepository1.save(user);
        }catch(Exception e){
            System.out.println(e);
        }

    }

    public Blog findBlogById(int blogId){
        //find a blog
        Blog blog = null;
        try{
           blog = blogRepository1.findById(blogId).get();
        }catch(Exception e){
            System.out.println(e);
        }
        return blog;
    }

    public void addImage(Integer blogId, String description, String dimensions) {
        //add an image to the blog after creating it
        try{
            Blog blog = blogRepository1.findById(blogId).get();
            if(blog== null){
                return;
            }
            Image image = imageService1.createAndReturn(blog,description,dimensions);
            image.setBlog(blog);
            List<Image> imageList = blog.getImageList();
            if(imageList==null){
                imageList = new ArrayList<>();
            }
            imageList.add(image);
            blog.setImageList(imageList);
            blogRepository1.save(blog);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        try{
            if(blogRepository1.findById(blogId).get()==null){
                return;
            }
            blogRepository1.deleteById(blogId);
            //since blog is parent should delete all images corresponding
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
