/*
 * Copyright 2020 Adib Saikali
 *
 */

package com.programmingmastery.database.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

interface PersistableRepository extends JpaRepository<Persistable, Long> {}
