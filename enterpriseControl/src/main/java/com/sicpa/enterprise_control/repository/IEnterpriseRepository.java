package com.sicpa.enterprise_control.repository;

import com.sicpa.enterprise_control.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnterpriseRepository extends JpaRepository<Enterprise, String> {
}
