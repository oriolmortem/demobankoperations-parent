package pe.devsu.development.operationservicetest.adapters.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.devsu.development.operationservicetest.dto.Transaction;
import pe.devsu.development.operationservicetest.service.TransactionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class TransactionResource {
    private final Logger log = LoggerFactory.getLogger(TransactionResource.class);

    private final TransactionService transactionService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Transaction>> findAll() {
        try {
            List<Transaction> transactionList = transactionService.findAll();
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Transaction>> findById(@PathVariable Integer id) {
        try {
            Transaction foundTransaction = transactionService.findById(id);
            return ResponseEntity.ok(new ApiResponse<>("success", "", foundTransaction));
        } catch (Exception e) {
            log.error("Error retrieving account with id {}: {}", id, e.getMessage(), e);
            if (e.getMessage().startsWith("API")) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", e.getMessage(), null));
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", "Internal server error", null));
            }
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<ApiResponse<Transaction>> save(@RequestBody Transaction account) {
        try {
            Transaction savedTrx = transactionService.save(account);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("success", "", savedTrx));
        } catch (Exception e) {
            if (e.getMessage().startsWith("API")) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", e.getMessage(), null));
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", "Internal server error", null));
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Transaction>> update(@PathVariable Integer id,
            @RequestBody Transaction trx) {
        try {
            trx.setId(id); // Ensure the ID is set for the update
            Transaction updatedTrx = transactionService.update(trx);
            return ResponseEntity.ok(new ApiResponse<>("success", "Transaction updated successfully", updatedTrx));
        } catch (Exception e) {
            if (e.getMessage().startsWith("API")) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", e.getMessage(), null));
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", "Internal server error", null));
            }
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Transaction>> partialUpdate(@PathVariable Integer id,
            @RequestBody Transaction trx) {
        try {
            trx.setId(id); // Ensure the ID is set for the update
            Transaction updatedTrx = transactionService.partialUpdate(trx);
            return ResponseEntity
                    .ok(new ApiResponse<>("success", "Transaction partially updated successfully", updatedTrx));
        } catch (Exception e) {
            if (e.getMessage().startsWith("API")) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", e.getMessage(), null));
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("error", "Internal server error", null));
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        try {
            Transaction trx = new Transaction();
            trx.setId(id); // Set the ID for deletion
            transactionService.delete(trx);
            return ResponseEntity.ok(new ApiResponse<>("success", "Transaction deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("error", "Internal server error", null));
        }
    }
}