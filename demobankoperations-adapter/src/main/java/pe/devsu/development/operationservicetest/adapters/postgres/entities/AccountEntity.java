package pe.devsu.development.operationservicetest.adapters.postgres.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.devsu.development.operationservicetest.dto.Account;
import pe.devsu.development.operationservicetest.dto.Transaction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(schema = "\"public\"", name = "\"Account\"")
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "initial_balance")
    private Float initialBalance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionEntity> transactions;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "delete_time")
    private LocalDateTime deleteTime;

    public Account toDomain() {
        Account account = new Account();
        BeanUtils.copyProperties(this, account);
        return account;
    }
}