package pe.devsu.development.operationservicetest.adapters.postgres.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.devsu.development.operationservicetest.adapters.postgres.entities.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    public List<AccountEntity> findByDeleteTimeIsNull() throws Exception;

}