package com.spring.guideance.user.repository;

import com.spring.guideance.user.domain.UserNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserNoticeRepository extends JpaRepository<UserNotice, Long> {
    List<UserNotice> findByUserId(Long userId);
    List<UserNotice> findByNoticeId(Long noticeId);
}