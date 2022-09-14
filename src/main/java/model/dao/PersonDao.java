package model.dao;

import model.entity.Offer;
import model.entity.Person;
import model.exception.DataBaseException;

import java.sql.SQLException;
import java.util.List;

public interface PersonDao extends CrudDao<Integer, Person> {
    List<Person> getAll() throws DataBaseException;

    Person getByLoginAndPass(String login, String password) throws DataBaseException;

    Person getByLogin(String login) throws DataBaseException;

    int getCountBlocked(List<Person> person);

}
