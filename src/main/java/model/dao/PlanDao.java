package model.dao;

import model.entity.Plan;
import model.exception.DataBaseException;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface PlanDao {

    public List<Plan> getPlansByUser(int userId)  throws SQLException;

    public boolean updatePlan(Plan plan) throws DataBaseException, SQLException, NamingException;

    public boolean add(int offer_id, int person_id) throws DataBaseException, SQLException, NamingException;

    public List<Integer> getPlanId(int person_id) throws SQLException;


}
