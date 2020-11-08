package com.spring.basics.Service.Implementation;

import com.spring.basics.DAO.Interface.CitizenDAO;
import com.spring.basics.Service.Interface.CitizenService;
import com.spring.basics.entity.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private CitizenDAO citizenDAO;

    @Override
    @Transactional
    public List<Citizen> getCitizens() {
        return citizenDAO.getCitizens();
    }

    @Override
    @Transactional
    public Citizen getCitizen(int theId) {
        return citizenDAO.getCitizen(theId);
    }

    @Override
    @Transactional
    public void saveCitizen(Citizen citizen) {
        citizenDAO.saveCitizen(citizen);
    }

    @Override
    @Transactional
    public void deleteCitizen(int id) {
        citizenDAO.deleteCitizen(id);
    }
}
