package pe.devsu.development.operationservicetest.persistence_ports;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.devsu.development.operationservicetest.dto.Account;

@Repository
public interface AccountPersistence {
    public List<Account> findAll() throws Exception;

    public Account findById(Integer id) throws Exception;

    public Account save(Account c) throws Exception;

    public Account update(Account c) throws Exception;

    public void delete(Account a) throws Exception;
}