package model.entity;

import model.builder.PersonBuilder;

public class Person extends Model {
    private int id;
    private String name;
    private String password;
    private String login;
    private String email;
    private int access_level;
    private double funds;
    private int blocked_status;

    private Person(PersonBuilderImpl builder){
        super(builder.id);
        this.id = builder.id;
        this.name = builder.name;
        this.password = builder.password;
        this.login = builder.login;
        this.email = builder.email;
        this.access_level = builder.access_level;
        this.funds = builder.funds;
        this.blocked_status = builder.blocked_status;
    }

    public static class PersonBuilderImpl implements PersonBuilder{
        private int id;
        private String name;
        private String password;
        private String login;
        private String email;
        private int access_level;
        private double funds;
        private int blocked_status;
        private int role_id;

        @Override
        public PersonBuilder setId(int id) {
            this.id = id;
            return this;
        }

        @Override
        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public PersonBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        @Override
        public PersonBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public PersonBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public PersonBuilder setAccessLevel(int accessLevel) {
            this.access_level = accessLevel;
            return this;
        }

        @Override
        public PersonBuilder setFunds(Double funds) {
            this.funds = funds;
            return this;
        }

        @Override
        public PersonBuilder setBlockedStatus(int status) {
            this.blocked_status = status;
            return this;
        }

        @Override
        public PersonBuilder setRole(int role_id) {
            this.role_id = role_id;
            return this;
        }

        @Override
        public Person build() {
            return new Person(this);
        }
    }

    public Person(){

    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return access_level;
    }

    public void setAccessLevel(int accessLevel) {this.access_level = accessLevel;}

    public int getStatus() {
        return blocked_status;
    }

    public void setStatus(int status) {
        this.blocked_status = status;
    }

    public void setFunds(double funds){ this.funds = funds;}

    public double getFundsFunds(){ return funds; }

    public void setLogin(String login){ this.login = login; }

    public String getLogin(){ return login; }



}

/*
public boolean add() throws SQLException, NamingException {
        Connection connection = Connector.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(Constants.INSERT_USER);
            statement.setString(1, "Nazar");
            statement.setString(2, "vazard");
            statement.setString(3, "Hanakotop23");
            statement.setString(4, "vazard23@asdifj.com");
            statement.setInt(5, 1);
            statement.setDouble(6, 25.0);
            statement.setBoolean(7, false);
            statement.setInt(8, 1);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();

        }
    }
*/