package com.spring.basics.DAO.Interface;

import com.spring.basics.entity.Citizen;

import java.util.List;

public interface CitizenDAO {
    public List<Citizen> getCitizens();

    public Citizen getCitizen(int theId);

    public void saveCitizen(Citizen citizen);

    public void deleteCitizen(int id);
}
