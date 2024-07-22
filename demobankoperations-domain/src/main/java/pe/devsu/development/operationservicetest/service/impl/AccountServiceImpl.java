package pe.devsu.development.operationservicetest.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.devsu.development.operationservicetest.dto.Account;
import pe.devsu.development.operationservicetest.persistence_ports.AccountPersistence;
import pe.devsu.development.operationservicetest.service.AccountService;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);

    private final AccountPersistence accPersistence;

    @Override
    public List<Account> findAll() throws Exception {
        try {
            return accPersistence.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Account findById(Integer id) throws Exception {
        try {
            return accPersistence.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Account save(Account c) throws Exception {
        Account saved = null;
        try {
            c.setCreationTime(LocalDateTime.now());
            saved = accPersistence.save(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return saved;
    }

    @Override
    public Account update(Account c) throws Exception {
        Account savedAccount = null;
        try {
            c.setUpdateTime(LocalDateTime.now());
            savedAccount = accPersistence.update(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return savedAccount;
    }

    @Override
    public Account partialUpdate(Account c) throws Exception {
        Account savedAccount = null;
        try {
            c.setUpdateTime(LocalDateTime.now());
            savedAccount = accPersistence.update(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return savedAccount;
    }

    @Override
    public void delete(Account c) throws Exception {
        try {
            accPersistence.delete(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }
}
