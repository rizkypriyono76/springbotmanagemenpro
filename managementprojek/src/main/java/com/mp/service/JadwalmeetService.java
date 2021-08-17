package com.mp.service;

import com.mp.model.Jadwalmeet;
import com.mp.repository.JadwalmeetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JadwalmeetService implements IJadwalmeetService {

    @Autowired
    private JadwalmeetRepository jadwalmeetRepository;

    @Override
    public List<Jadwalmeet> getJadwalmeetsByUser(String user) {
        return jadwalmeetRepository.findByUserName(user);
    }

    @Override
    public Optional< Jadwalmeet > getJadwalmeetById(long id) {
        return jadwalmeetRepository.findById(id);
    }

    @Override
    public void updateJadwalmeet(Jadwalmeet jadwalmeet) {
        jadwalmeetRepository.save(jadwalmeet);
    }

    @Override
    public void addJadwalmeet(String name, String desc, Date targetDate, boolean isDone) {
        jadwalmeetRepository.save(new Jadwalmeet(name, desc, targetDate, isDone));
    }

    @Override
    public void deleteJadwalmeet(long id) {
        Optional < Jadwalmeet > jadwalmeet = jadwalmeetRepository.findById(id);
        if (jadwalmeet.isPresent()) {
            jadwalmeetRepository.delete(jadwalmeet.get());
        }
    }

    @Override
    public void saveJadwalmeet(Jadwalmeet jadwalmeet) {
        jadwalmeetRepository.save(jadwalmeet);
    }
}
