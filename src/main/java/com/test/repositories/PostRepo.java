package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entities.Category;
import com.test.entities.Post;
import com.test.entities.User;

import java.util.List;


public interface PostRepo extends JpaRepository<Post, Integer>{
List<Post> findByUser(User user);
List<Post> findByCategory(Category category);
List<Post> findByTitleContaining(String title);

}
