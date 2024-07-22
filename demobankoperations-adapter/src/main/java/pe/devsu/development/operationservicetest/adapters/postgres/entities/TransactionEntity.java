package pe.devsu.development.operationservicetest.adapters.postgres.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@Table(schema = "\"public\"", name = "\"Transaction\"")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "transaction_text")
    private String transactionText;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "balance")
    private Float balance;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    public Transaction toDomain() {
        Transaction transact = new Transaction();
        BeanUtils.copyProperties(this, transact);
        return transact;
    }
}
