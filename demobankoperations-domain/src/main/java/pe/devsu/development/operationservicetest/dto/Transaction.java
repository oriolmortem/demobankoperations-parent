package pe.devsu.development.operationservicetest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer accountId;

	private String transactionText;

	private String transactionType;

	private Float amount;

	private Float balance;

	private LocalDate transactionDate;

	private LocalDateTime creationTime;

	private LocalDateTime updateTime;

}
