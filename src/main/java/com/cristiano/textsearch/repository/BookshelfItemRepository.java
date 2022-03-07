package com.cristiano.textsearch.repository;

import com.cristiano.textsearch.entity.BookShelfItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookshelfItemRepository extends CrudRepository<BookShelfItem, Long> {

//    @Query(value = "SELECT count(id) FROM BookShelfItem")
//    long count();
}