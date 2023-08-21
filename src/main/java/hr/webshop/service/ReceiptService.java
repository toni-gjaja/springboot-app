package hr.webshop.service;

import hr.webshop.entity.Receipt;
import hr.webshop.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    private final ReceiptRepository repo;

    @Autowired
    public ReceiptService(ReceiptRepository repo) {
        this.repo = repo;
    }

    public List<Receipt> getAllReceipts(){ return repo.findAll(); }

}
