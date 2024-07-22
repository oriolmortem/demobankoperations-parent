package pe.devsu.development.operationservicetest.adapters.postgres.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import pe.devsu.development.operationservicetest.adapters.postgres.daos.AccountRepository;
import pe.devsu.development.operationservicetest.adapters.postgres.daos.TransactionRepository;
import pe.devsu.development.operationservicetest.adapters.postgres.entities.TransactionEntity;
import pe.devsu.development.operationservicetest.dto.Transaction;
import pe.devsu.development.operationservicetest.persistence_ports.TransactionPersistence;

@RequiredArgsConstructor
@Repository("trxPersistence")
public class TransactionPersistencePostgres implements TransactionPersistence {
    private static final Logger log = LogManager.getLogger(TransactionPersistencePostgres.class);

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    @Override
    public List<Transaction> findAll() throws Exception {
        List<Transaction> transactionList = null;
        try {
            List<TransactionEntity> transactionEntityList = transactionRepository.findAll();
            if (!CollectionUtils.isEmpty(transactionEntityList)) {
                transactionList = transactionEntityList.stream().map(entity -> entity.toDomain())
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return transactionList;
    }

    @Override
    public Transaction findById(Integer id) throws Exception {
        try {
            return transactionRepository.findById(id)
                    .map(TransactionEntity::toDomain)
                    .orElseThrow(() -> new Exception("Api: Transaction with id " + id + " not found"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Transaction save(Transaction c) throws Exception {
        try {
            return transactionRepository.save(toEntity(c)).toDomain();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Transaction update(Transaction c) throws Exception {
        try {
            if (!transactionRepository.existsById(c.getId())) {
                throw new Exception("Transaction not found");
            }
            return transactionRepository.save(toEntity(c)).toDomain();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Transaction a) throws Exception {
        try {
            transactionRepository.delete(toEntity(a));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    private TransactionEntity toEntity(Transaction transact) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(transact.getAmount());
        transactionEntity.setBalance(transact.getBalance());
        transactionEntity.setId(transact.getId());
        transactionEntity.setTransactionDate(transact.getTransactionDate());
        transactionEntity.setTransactionText(transact.getTransactionText());
        transactionEntity.setTransactionType(transact.getTransactionType());
        transactionEntity.setCreationTime(transact.getCreationTime());
        transactionEntity.setUpdateTime(transact.getUpdateTime());
        transactionEntity.setAccount(accountRepository.findById(transact.getAccountId()).get());
        return transactionEntity;
    }
}
