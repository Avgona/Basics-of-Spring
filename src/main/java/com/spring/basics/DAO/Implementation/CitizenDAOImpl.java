package com.spring.basics.DAO.Implementation;

import com.spring.basics.DAO.Interface.CitizenDAO;
import com.spring.basics.entity.Citizen;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CitizenDAOImpl implements CitizenDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Citizen> getCitizens() {

        Session session = sessionFactory.getCurrentSession();

        Query<Citizen> query = session.createQuery("From Citizen", Citizen.class);

        List<Citizen> citizens = query.getResultList();

        return citizens;
    }

    @Override
    public Citizen getCitizen(int theId) {

        Session session = sessionFactory.getCurrentSession();

        Citizen citizen = session.get(Citizen.class, theId);

        return citizen;
    }

    @Override
    public void saveCitizen(Citizen citizen) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(citizen);
    }

    @Override
    public void deleteCitizen(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from Citizen where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
