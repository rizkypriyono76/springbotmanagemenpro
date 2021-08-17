package com.mp.service;

import com.mp.model.Liburan;
import com.mp.repository.LiburanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LiburanService implements ILiburanService {

    @Autowired
    private LiburanRepository liburanRepository;

    @Override
    public List <Liburan> getLiburansByUser(String user) {
        return liburanRepository.findByUserName(user);
    }

    @Override
    public Optional < Liburan > getLiburanById(long id) {
        return liburanRepository.findById(id);
    }

    @Override
    public void updateLiburan(Liburan liburan) {
        liburanRepository.save(liburan);
    }

    @Override
    public void addLiburan(String name, String desc, Date targetDate, boolean isDone) {
        liburanRepository.save(new Liburan(name, desc, targetDate, isDone));
    }

    @Override
    public void deleteLiburan(long id) {
        Optional < Liburan > liburan = liburanRepository.findById(id);
        if (liburan.isPresent()) {
            liburanRepository.delete(liburan.get());
        }
    }

    @Override
    public void saveLiburan(Liburan liburan) {
        liburanRepository.save(liburan);
    }
}