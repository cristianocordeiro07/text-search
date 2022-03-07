package com.cristiano.textsearch.service;

import com.cristiano.textsearch.entity.BookShelfItem;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface BookshelfItemService {

    long count();

    String maxItems();

    long howManyMoreCanHold();

    File readPage(Long itemId, Long pageNumber) throws PrinterException, IOException;

    List<BookShelfItem> searchByText(String text) throws Exception;

    void updateFile(BookShelfItem item, MultipartFile file) throws IOException;

    void deleteFile(String fileName);
}
