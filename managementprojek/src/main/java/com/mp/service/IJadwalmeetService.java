package com.mp.service;

import com.mp.model.Jadwalmeet;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IJadwalmeetService {

    List<Jadwalmeet> getJadwalmeetsByUser(String user);

    Optional< Jadwalmeet > getJadwalmeetById(long id);

    void updateJadwalmeet(Jadwalmeet jadwalmeet);

    void addJadwalmeet(String name, String desc, Date targetDate, boolean isDone);

    void deleteJadwalmeet(long id);

    void saveJadwalmeet(Jadwalmeet jadwalmeet);
}