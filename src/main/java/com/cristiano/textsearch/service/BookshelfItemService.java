package com.cristiano.textsearch.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface BookshelfItemService {

    long count();

    String maxItems();

    long howManyMoreCanHold();

    String readPage(@PathVariable Long id);
}
