package com.cristiano.textsearch.service;

import org.apache.lucene.document.Document;

import java.util.List;

public interface LuceneService {

    void indexFiles();

    List<Document> searchText(String textToFind) throws Exception;
}
