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
import pe.devsu.development.operationservicetest.dto.Account;
import pe.devsu.development.operationservicetest.service.AccountService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountResource {
    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final AccountService accountService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Account>> findAll() {
        try {
            List<Account> accountList = accountService.findAll();
            return new ResponseEntity<>(accountList, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Account>> findById(@PathVariable Integer id) {
        try {
            Account foundAccount = accountService.findById(id);
            return ResponseEntity.ok(new ApiResponse<>("success", "", foundAccount));
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
    public ResponseEntity<ApiResponse<Account>> save(@RequestBody Account account) {
        try {
            Account saveAccount = accountService.save(account);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("success", "", saveAccount));
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
    public ResponseEntity<ApiResponse<Account>> update(@PathVariable Integer id,
            @RequestBody Account account) {
        try {
            account.setId(id); // Ensure the ID is set for the update
            Account updatedAccount = accountService.update(account);
            return ResponseEntity.ok(new ApiResponse<>("success", "Account updated successfully", updatedAccount));
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
    public ResponseEntity<ApiResponse<Account>> partialUpdate(@PathVariable Integer id,
            @RequestBody Account account) {
        try {
            account.setId(id); // Ensure the ID is set for the update
            Account updatedAccount = accountService.partialUpdate(account);
            return ResponseEntity
                    .ok(new ApiResponse<>("success", "Account partially updated successfully", updatedAccount));
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
            Account account = new Account();
            account.setId(id); // Set the ID for deletion
            accountService.delete(account);
            return ResponseEntity.ok(new ApiResponse<>("success", "Account deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("error", "Internal server error", null));
        }
    }
}