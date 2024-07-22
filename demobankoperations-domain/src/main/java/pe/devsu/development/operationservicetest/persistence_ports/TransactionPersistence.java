package pe.devsu.development.operationservicetest.persistence_ports;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.devsu.development.operationservicetest.dto.Transaction;

@Repository
public interface TransactionPersistence {
    public List<Transaction> findAll() throws Exception;

    public Transaction findById(Integer id) throws Exception;

    public Transaction save(Transaction c) throws Exception;

    public Transaction update(Transaction c) throws Exception;

    public void delete(Transaction a) throws Exception;
}