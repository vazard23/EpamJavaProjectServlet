package service;

import model.entity.Plan;
import model.exception.DataBaseException;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface PlanService {

    public List<Plan> getPlansByUser(int userId) throws SQLException;

    public boolean updatePlan(Plan plan) throws SQLException, NamingException, DataBaseException;


}
