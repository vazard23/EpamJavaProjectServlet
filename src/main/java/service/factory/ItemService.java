package service.factory;

import model.exception.*;
import model.entity.Model;

import java.util.List;

public interface ItemService <K, T extends Model> {

    T getEntity(K id) throws DataBaseException, ServiceException;

    boolean add(T entity) throws ServiceException;

    T update(T entity) throws DataBaseException;

    boolean delete(K id);

    List<T> getAllFree() throws ServiceException;

}