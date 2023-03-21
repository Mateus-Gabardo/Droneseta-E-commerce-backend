package com.api.dronesetaecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.dronesetaecommerce.model.EnderecoModel;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, UUID>{}