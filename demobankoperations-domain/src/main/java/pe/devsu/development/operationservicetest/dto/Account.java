package pe.devsu.development.operationservicetest.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String accountNumber;

	private String accountType;

	private Integer customerId;

	private Float initialBalance;

	private Boolean active;

	private List<Transaction> transactions;

	private LocalDateTime creationTime;

	private LocalDateTime updateTime;

	private LocalDateTime deleteTime;

}
