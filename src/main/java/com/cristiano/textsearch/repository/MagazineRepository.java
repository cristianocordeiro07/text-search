package com.cristiano.textsearch.repository;

import com.cristiano.textsearch.entity.Magazine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends CrudRepository<Magazine, Long> {}