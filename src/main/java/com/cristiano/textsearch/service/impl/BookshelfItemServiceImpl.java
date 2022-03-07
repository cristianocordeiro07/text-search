package com.cristiano.textsearch.service.impl;

import com.cristiano.textsearch.entity.BookShelfItem;
import com.cristiano.textsearch.repository.BookshelfItemRepository;
import com.cristiano.textsearch.service.BookshelfItemService;
import com.cristiano.textsearch.service.LuceneService;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookshelfItemServiceImpl implements BookshelfItemService {

    @Value("${bookshelf.max.items}")
    String MAX_ITEMS;

    @Value("${file-upload-dir}")
    String FILE_DIRECTORY;

    private final BookshelfItemRepository bookshelfItemRepository;

    private final LuceneService luceneService;

    public BookshelfItemServiceImpl(BookshelfItemRepository bookshelfItemRepository, LuceneService luceneService) {
        this.bookshelfItemRepository = bookshelfItemRepository;
        this.luceneService = luceneService;
    }

    public long count() {
        return bookshelfItemRepository.count();
    }

    public String maxItems() {
        return MAX_ITEMS;
    }

    public long howManyMoreCanHold() {

        //Differente between maximum items configured and total items in the database
        try {
            return Long.parseLong(Objects.requireNonNull(MAX_ITEMS)) - bookshelfItemRepository.count();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String readPage(@PathVariable Long id) {
        return "";
    }

    public List<BookShelfItem> searchByText(String text) throws Exception {

        List<BookShelfItem> items = new ArrayList<>();
        List<Document> documents = luceneService.searchText(text);

        for (Document doc : documents) {
            if(doc.get("itemId") != null){
                bookshelfItemRepository.findById(Long.parseLong(doc.get("itemId"))).ifPresent(items::add);
            }
        }

        return items;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void updateFile(BookShelfItem item, MultipartFile file) throws IOException {
        File myFile = new File(FILE_DIRECTORY + item.getId());
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();

        //TODO create a Job
        //Index all files
        luceneService.indexFiles();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void deleteFile(String fileName) {
        File file = new File(FILE_DIRECTORY + fileName);
        file.delete();
    }
}
