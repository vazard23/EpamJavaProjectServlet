package service.impl;

import model.dao.PersonDao;
import model.dao.factory.DaoFactory;
import model.entity.Person;
import model.exception.DataBaseException;
import model.exception.NotFoundPersonException;
import model.exception.ServiceException;
import model.exception.WrongDataException;
import org.apache.log4j.Logger;
import service.PersonService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonServiceImpl implements PersonService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private PersonDao personDao = daoFactory.getPersonDAO();

    @Override
    public Person getByLoginAndPass(String login, String password) throws NotFoundPersonException, WrongDataException, DataBaseException {
        Person person = personDao.getByLoginAndPass(login, password);

        if (Objects.isNull(person)) {
            throw new NotFoundPersonException();
        }

        return person;
    }

    @Override
    public Person getByLogin(String login) throws DataBaseException {
        return personDao.getByLogin(login);
    }

    @Override
    public Person getEntity(Integer id) throws DataBaseException, ServiceException {
        try {
            Person person = personDao.getById(id);
            return person;
        } catch (DataBaseException e) {

            throw new ServiceException("Cannot get person in service", e);
        }
    }

    @Override
    public boolean add(Person entity) throws ServiceException {
        try {
            personDao.add(entity);
            return true;
        } catch (DataBaseException | NamingException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Person update(Person entity) throws DataBaseException {
        return personDao.updateEntity(entity);
    }

    @Override
    public boolean delete(Integer id) {
        boolean flag = false;
        try {
            flag = personDao.deleteEntity(id);
        } catch (SQLException | NamingException e) {

        }
        return flag;
    }

    @Override
    public List<Person> getAllFree() throws ServiceException {
        return null;
    }


    @Override
    public List<Person> getAllPerson() throws ServiceException, DataBaseException {
        List<Person> all = personDao.getAll();

        return all.stream()
                .filter(p -> p.getAccessLevel() == 2)
                .collect(Collectors.toList());
    }

    @Override
    public Person getById(int id) throws DataBaseException {
        return personDao.getById(id);
    }


    @Override
    public int getAllBlocked() throws DataBaseException {
        return personDao.getCountBlocked(personDao.getAll());
    }
}
