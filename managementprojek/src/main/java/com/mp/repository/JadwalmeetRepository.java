package com.mp.repository;

import com.mp.model.Jadwalmeet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JadwalmeetRepository extends JpaRepository<Jadwalmeet, Long> {
    List<Jadwalmeet> findByUserName(String user);
}
