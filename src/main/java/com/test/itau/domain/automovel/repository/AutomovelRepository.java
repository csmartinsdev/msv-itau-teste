package com.test.itau.domain.automovel.repository;

import com.test.itau.domain.automovel.entity.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long>, JpaSpecificationExecutor<Automovel> {

}
