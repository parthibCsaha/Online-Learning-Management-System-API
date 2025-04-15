package com.onlinelearning.repo;

import com.onlinelearning.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
