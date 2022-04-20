package edu.sgu.inventory.repository;

import edu.sgu.inventory.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,String> {
}
