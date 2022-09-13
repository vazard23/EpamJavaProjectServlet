package model.dao.impl;

import model.dao.PlanDao;
import model.dao.connection.Connector;
import model.dao.constant.Constants;
import model.entity.Offer;
import model.entity.Plan;
import model.exception.DataBaseException;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDatabaseDao implements PlanDao {
    @Override
    public List<Plan> getPlansByUser(int userId)  throws SQLException {
        Connection connection = Connector.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(Constants.ALL_PLANS_BY_PERSON_ID);
        {
            List<Plan> plans = new ArrayList<>();
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Plan plan = new Plan();
                plan.setId(rs.getInt(1));
                plan.setStatus_id(rs.getInt(2));
                plan.setPerson_id(rs.getInt(3));
                plan.setOffer_id(rs.getInt(4));
                plan.setDate_end(rs.getTimestamp(5));
                plans.add(plan);
            }
            return plans;
        }
    }

    @Override
    public boolean updatePlan(Plan plan) throws DataBaseException, SQLException, NamingException {
        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_PLAN)) {
            connection.setAutoCommit(false);
            statement.setInt(1, plan.getStatus_id());
            statement.setInt(2, plan.getPerson_id());
            statement.setInt(3, plan.getOffer_id());
            statement.setTimestamp(4, plan.getDate_end());
            statement.setInt(5, plan.getId());

            statement.execute();
            connection.commit();
            return true;
        }
    }

    @Override
    public boolean add(int offer_id, int person_id) throws DataBaseException, SQLException, NamingException {
        Long time = System.currentTimeMillis() + Long.parseLong("2592000000");
        Timestamp afterThirtyDays = new Timestamp(time);

        try (Connection connection = Connector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.ATTACH_PLAN_TO_USER)) {
            connection.setAutoCommit(false);
            statement.setInt(1, 1);
            statement.setInt(2, person_id);
            statement.setInt(3, offer_id);
            statement.setTimestamp(4, afterThirtyDays);
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot add offer to plan table");
        }
    }

    @Override
    public List<Integer> getPlanId(int person_id) throws SQLException {
        Connection connection = Connector.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(Constants.ALL_PLANS_BY_PERSON_ID);
        {
            statement.setInt(1, person_id);
            ResultSet resultSet = statement.executeQuery();
            List<Integer> id_list = new ArrayList<>();
            List<Offer> offers = new ArrayList<Offer>();
            while (resultSet.next()) {
                id_list.add(resultSet.getInt(4));
            }
            return id_list;
        }
    }

}
