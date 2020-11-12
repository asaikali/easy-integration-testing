/*
 * Copyright 2020 Adib Saikali
 *
 */

package com.programmingmastery.database.jpa;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface UniquePersistableRepository extends JpaRepository<UniquePersistable, UUID> {}
