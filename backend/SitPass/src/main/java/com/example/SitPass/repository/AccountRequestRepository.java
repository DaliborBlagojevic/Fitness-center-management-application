package com.example.SitPass.repository;

import com.example.SitPass.model.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {

    @Query("SELECT ar FROM AccountRequest ar WHERE ar.requestStatus = 'PENDING'")
    List<AccountRequest> findAllPending();

    Optional<AccountRequest> findByEmail(String email);


}

