package by.epam.webtask.model.service;

import by.epam.webtask.exception.ServiceException;
import by.epam.webtask.model.entity.Program;
import by.epam.webtask.model.entity.ProgramStatus;
import by.epam.webtask.model.entity.UserRole;

import java.util.Optional;

/**
 * The interface represents program service
 */
public interface ProgramService extends EntityService<Program> {
    Program insert(Long orderId, ProgramStatus status, String intensity, String schedule, String exercises,
                   String diet, String equipment) throws ServiceException;


    boolean update(Program program, String intensity, String schedule,
                   String exercises, String diet, String equipment, UserRole role) throws ServiceException;

    Optional<Program> findByOrderAndClientId(Long orderId, Long clientId) throws ServiceException;

    Optional<Program> find(long id) throws ServiceException;
}
