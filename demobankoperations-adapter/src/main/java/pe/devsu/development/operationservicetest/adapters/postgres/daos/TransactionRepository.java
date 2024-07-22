package pe.devsu.development.operationservicetest.adapters.postgres.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.devsu.development.operationservicetest.adapters.postgres.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

}