package com.gorixl.laizhumainbackend.repository;

import com.gorixl.laizhumainbackend.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
