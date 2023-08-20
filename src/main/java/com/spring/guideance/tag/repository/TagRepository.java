package com.spring.guideance.tag.repository;

import com.spring.guideance.tag.domain.Tag;
import com.spring.guideance.util.SizeType;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagName(String tagName);

    Page<Tag> findByTagNameContaining(String tagName, Pageable pageable);

    boolean existsByTagName(String tagName);

    @BatchSize(size = SizeType.SIZE.MEDIUM)
    List<Tag> findAll();
}
