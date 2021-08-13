package com.gabriel.gonoring.question.app.user.repository;

import com.gabriel.gonoring.question.app.user.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserPORepository extends JpaRepository<UserPO, UUID> {
}
