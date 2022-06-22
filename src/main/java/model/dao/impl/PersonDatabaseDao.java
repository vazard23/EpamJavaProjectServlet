package model.dao.impl;

import model.dao.PersonDao;
import model.dao.connection.Connector;
import model.dao.constant.Constants;
import model.entity.Person;
import model.exception.DataBaseException;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class PersonDatabaseDao implements PersonDao {

    public PersonDatabaseDao(){
    }



    @Override
    public boolean add(Person person) throws SQLException, NamingException {
        Connection connection = Connector.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            PreparedStatement statement = connection.prepareStatement(Constants.INSERT_PERSON);
            statement.setString(1, person.getName());
            statement.setString(2, person.getLogin());
            statement.setString(3, person.getPassword());
            statement.setString(4, person.getEmail());
            statement.setInt(5, person.getAccessLevel());
            statement.setDouble(6, person.getFundsFunds());
            statement.setInt(7, person.getStatus());
            statement.setInt(8, 2);
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }


    @Override
    public Person getById(Integer id) throws DataBaseException {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.SELECT_BY_ID_PERSON)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return new Person.PersonBuilderImpl()
                    .setId(id)
                    .setName(resultSet.getString("name"))
                    .setEmail(resultSet.getString("email"))
                    .setLogin(resultSet.getString("login"))
                    .setPassword(resultSet.getString("password"))
                    .setAccessLevel(resultSet.getInt("level"))
                    .setRole(resultSet.getInt("role_id"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot getByLogin person", e);
        }
    }

    @Override
    public boolean deleteEntity(Integer id) throws NamingException, SQLException {
        return false;
    }

    @Override
    public Person updateEntity(Person entity) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public Person getByLoginAndPass(String login, String password) {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.SELECT_BY_LOGIN_AND_PASS)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            Person person = null;

            if (resultSet.next()) {
                person = new Person.PersonBuilderImpl()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("name"))
                        .setEmail(resultSet.getString("e_mail"))
                        .setLogin(resultSet.getString("login"))
                        .setPassword(resultSet.getString("password"))
                        .setAccessLevel(resultSet.getInt("access_level"))
                        .setFunds(resultSet.getDouble("funds"))
                        .setBlockedStatus(resultSet.getInt("blocked_status"))
                        .setRole(resultSet.getInt("role_id"))
                        .build();
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot getByLoginAndPass person", e);
        }
    }

    @Override
    public Person getByLogin(String login) {
        return null;
    }

    @Override
    public int getCountBlocked(List<Person> person) {
        var count = 0;
        for (Person num : person) {
            if (num.getStatus() == 2) {
                count++;
            }
        }
        return count;
    }
}
