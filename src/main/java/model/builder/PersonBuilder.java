package model.builder;

import model.entity.Person;

public interface PersonBuilder {
    PersonBuilder setId(int id);
    PersonBuilder setName(String name);
    PersonBuilder setLogin(String login);
    PersonBuilder setPassword(String password);
    PersonBuilder setEmail(String email);
    PersonBuilder setAccessLevel(int accessLevel);
    PersonBuilder setFunds(Double funds);
    PersonBuilder setBlockedStatus(int status);
    PersonBuilder setRole(int role_id);

    Person build();
}
