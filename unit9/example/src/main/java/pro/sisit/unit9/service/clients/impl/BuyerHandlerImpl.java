package pro.sisit.unit9.service.clients.impl;

import org.springframework.stereotype.Service;
import pro.sisit.unit9.data.BuyerRepository;
import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.service.clients.BuyerHandler;

import java.util.Optional;

@Service
public class BuyerHandlerImpl implements BuyerHandler {

    private final BuyerRepository buyerRepository;

    public BuyerHandlerImpl(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    @Override
    public Buyer save(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    @Override
    public Buyer getBuyer(String name, String address) {
        Optional<Buyer> buyer = buyerRepository.findByNameAndAddress(name, address).stream().findFirst();
        if (buyer.isPresent()) {
            return buyer.get();
        } else {
            throw new RuntimeException("Покупатель не найден");
        }
    }
}
