package com.bank.services;

import com.bank.entities.Document;
import com.bank.repositories.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document getDocumentById(int id) {
        return documentRepository.findById(id).orElse(null);
    }

    public void save(Document document) {
        documentRepository.save(document);
    }

    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    public void deleteAllById(List<Integer> ids) {
        documentRepository.deleteAllById(ids);
    }
}
