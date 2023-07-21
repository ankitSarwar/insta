package com.geekster.InstagramProject.repo;

import com.geekster.InstagramProject.model.Admin;
import com.geekster.InstagramProject.model.AdminAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminTokenRepo extends JpaRepository<AdminAuthenticationToken, Long> {

    AdminAuthenticationToken findFirstByToken(String token);

    AdminAuthenticationToken findByAdmin(Admin admin);
}
