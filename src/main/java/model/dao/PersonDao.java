package model.dao;

import model.entity.Offer;
import model.entity.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao extends CrudDao<Integer, Person> {
    List<Person> getAll();

    Person getByLoginAndPass(String login, String password);

    Person getByLogin(String login);

    int getCountBlocked(List<Person> person);

}
