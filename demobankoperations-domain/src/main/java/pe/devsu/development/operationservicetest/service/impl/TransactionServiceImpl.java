package pe.devsu.development.operationservicetest.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.devsu.development.operationservicetest.dto.Transaction;
import pe.devsu.development.operationservicetest.persistence_ports.TransactionPersistence;
import pe.devsu.development.operationservicetest.service.TransactionService;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LogManager.getLogger(TransactionServiceImpl.class);

    private final TransactionPersistence trxPersistence;

    @Override
    public List<Transaction> findAll() throws Exception {
        try {
            return trxPersistence.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Transaction findById(Integer id) throws Exception {
        try {
            return trxPersistence.findById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Transaction save(Transaction c) throws Exception {
        Transaction saved = null;
        try {
            c.setCreationTime(LocalDateTime.now());
            saved = trxPersistence.save(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return saved;
    }

    @Override
    public Transaction update(Transaction c) throws Exception {
        Transaction savedTrx = null;
        try {
            c.setUpdateTime(LocalDateTime.now());
            savedTrx = trxPersistence.update(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return savedTrx;
    }

    @Override
    public Transaction partialUpdate(Transaction c) throws Exception {
        Transaction savedTrx = null;
        try {
            c.setUpdateTime(LocalDateTime.now());
            savedTrx = trxPersistence.update(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return savedTrx;
    }

    @Override
    public void delete(Transaction c) throws Exception {
        try {
            trxPersistence.delete(c);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }
}
