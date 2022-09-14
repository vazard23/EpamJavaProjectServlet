package service;

import model.entity.Person;
import model.exception.DataBaseException;
import model.exception.NotFoundPersonException;
import model.exception.ServiceException;
import model.exception.WrongDataException;
import service.factory.ItemService;

import java.util.List;

public interface PersonService extends ItemService<Integer, Person> {
    Person getByLoginAndPass(String login, String password) throws NotFoundPersonException, WrongDataException, DataBaseException;

    Person getByLogin(String login) throws DataBaseException;

    int getAllBlocked() throws DataBaseException;

    List<Person> getAllPerson() throws ServiceException, DataBaseException;

    Person getById(int id) throws DataBaseException;
}

