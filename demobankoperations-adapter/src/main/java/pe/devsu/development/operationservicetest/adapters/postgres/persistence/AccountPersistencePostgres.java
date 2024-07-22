package pe.devsu.development.operationservicetest.adapters.postgres.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.RequiredArgsConstructor;
import pe.devsu.development.operationservicetest.adapters.postgres.daos.AccountRepository;
import pe.devsu.development.operationservicetest.adapters.postgres.entities.AccountEntity;
import pe.devsu.development.operationservicetest.dto.Account;
import pe.devsu.development.operationservicetest.persistence_ports.AccountPersistence;

@RequiredArgsConstructor
@Repository("accPersistence")
public class AccountPersistencePostgres implements AccountPersistence {

    private static final Logger log = LogManager.getLogger(AccountPersistencePostgres.class);

    private final AccountRepository accountRepository;

    @Override
    public List<Account> findAll() throws Exception {
        List<Account> accountList = null;
        try {
            List<AccountEntity> accountEntityList = accountRepository.findByDeleteTimeIsNull();
            if (!CollectionUtils.isEmpty(accountEntityList)) {
                accountList = accountEntityList.stream().map(entity -> entity.toDomain())
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
        return accountList;
    }

    @Override
    public Account findById(Integer id) throws Exception {
        try {
            return accountRepository.findById(id)
                    .map(AccountEntity::toDomain)
                    .orElseThrow(() -> new Exception("Api: Account with id " + id + " not found"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Account save(Account c) throws Exception {
        try {
            return accountRepository.save(toEntity(c)).toDomain();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Account update(Account c) throws Exception {
        try {
            if (!accountRepository.existsById(c.getId())) {
                throw new Exception("Account not found");
            }
            return accountRepository.save(toEntity(c)).toDomain();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Account a) throws Exception {
        try {
            accountRepository.delete(toEntity(a));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage(), e);
        }
    }

    private AccountEntity toEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setActive(account.getActive());
        accountEntity.setCreationTime(account.getCreationTime());
        accountEntity.setCustomerId(account.getCustomerId());
        accountEntity.setDeleteTime(account.getDeleteTime());
        accountEntity.setId(account.getId());
        accountEntity.setInitialBalance(account.getInitialBalance());
        accountEntity.setUpdateTime(account.getUpdateTime());
        return accountEntity;
    }
}
