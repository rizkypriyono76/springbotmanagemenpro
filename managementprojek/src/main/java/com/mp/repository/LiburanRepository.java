package com.mp.repository;

import com.mp.model.Liburan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiburanRepository extends JpaRepository<Liburan, Long > {
    List< Liburan > findByUserName(String user);
}
