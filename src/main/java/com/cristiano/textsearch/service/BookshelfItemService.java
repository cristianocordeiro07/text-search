package com.cristiano.textsearch.service;

import com.cristiano.textsearch.entity.BookShelfItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookshelfItemService {

    long count();

    String maxItems();

    long howManyMoreCanHold();

    String readPage(@PathVariable Long id);

    List<BookShelfItem> searchByText(String text) throws Exception;

    void updateFile(BookShelfItem item, MultipartFile file) throws IOException;

    void deleteFile(String fileName);
}
