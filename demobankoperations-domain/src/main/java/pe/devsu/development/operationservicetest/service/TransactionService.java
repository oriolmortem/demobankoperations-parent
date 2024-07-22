package pe.devsu.development.operationservicetest.service;

import java.util.List;

import pe.devsu.development.operationservicetest.dto.Transaction;

public interface TransactionService {

    public List<Transaction> findAll() throws Exception;

    public Transaction findById(Integer id) throws Exception;

    public Transaction save(Transaction c) throws Exception;

    public Transaction update(Transaction c) throws Exception;

    public Transaction partialUpdate(Transaction c) throws Exception;

    public void delete(Transaction a) throws Exception;

}