package pe.devsu.development.operationservicetest.service;

import java.util.List;

import pe.devsu.development.operationservicetest.dto.Account;

public interface AccountService {

    public List<Account> findAll() throws Exception;

    public Account findById(Integer id) throws Exception;

    public Account save(Account c) throws Exception;

    public Account update(Account c) throws Exception;

    public Account partialUpdate(Account c) throws Exception;

    public void delete(Account a) throws Exception;

}