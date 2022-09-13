package service.impl;

import model.dao.OfferDao;
import model.dao.PersonDao;
import model.dao.PlanDao;
import model.dao.factory.DaoFactory;
import model.entity.Plan;
import model.exception.DataBaseException;
import service.PlanService;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class PlanServiceImpl implements PlanService {

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private OfferDao offerDao = daoFactory.getOfferDao();
    private PersonDao personDao = daoFactory.getPersonDAO();
    private PlanDao planDao = daoFactory.getPlanDAO();


    @Override
    public List<Plan> getPlansByUser(int userId) throws SQLException {
        return planDao.getPlansByUser(userId);
    }

    @Override
    public boolean updatePlan(Plan plan) throws SQLException, NamingException, DataBaseException {
        return planDao.updatePlan(plan);
    }
}
