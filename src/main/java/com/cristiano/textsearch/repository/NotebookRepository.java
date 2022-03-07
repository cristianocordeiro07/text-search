package com.cristiano.textsearch.repository;

import com.cristiano.textsearch.entity.Book;
import com.cristiano.textsearch.entity.Notebook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotebookRepository extends CrudRepository<Notebook, Long> {}