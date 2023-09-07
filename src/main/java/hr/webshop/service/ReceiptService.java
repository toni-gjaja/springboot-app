package hr.webshop.service;

import hr.webshop.entity.Receipt;
import hr.webshop.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {

    private final ReceiptRepository repo;

    @Autowired
    public ReceiptService(ReceiptRepository repo) {
        this.repo = repo;
    }

    public List<Receipt> getAllReceipts(){ return repo.findAll(); }

    public Optional<Receipt> getReceiptById(Long id){ return repo.findById(id);}

    public void saveReceipt(Receipt receipt){ repo.save(receipt); }

    public List<Receipt> getAllByUserId(Long id){ return repo.findAllByAppUser_Id(id); }

}
