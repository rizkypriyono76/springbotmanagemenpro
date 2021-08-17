package com.mp.service;

import com.mp.model.Liburan;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ILiburanService {
    List<Liburan> getLiburansByUser(String user);

    Optional< Liburan > getLiburanById(long id);

    void updateLiburan(Liburan liburan);

    void addLiburan(String name, String desc, Date targetDate, boolean isDone);

    void deleteLiburan(long id);

    void saveLiburan(Liburan liburan);
}
