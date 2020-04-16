package pro.sisit.unit9.service.clients.impl;

import org.springframework.stereotype.Service;
import pro.sisit.unit9.data.BuyerRepository;
import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.service.clients.BuyerService;

import java.util.Optional;

@Service
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;

    public BuyerServiceImpl(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    @Override
    public Buyer save(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    @Override
    public Buyer getBuyer(String name, String address) {
        return buyerRepository.findByNameAndAddress(name, address).
                stream().
                findFirst().
                orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }
}
