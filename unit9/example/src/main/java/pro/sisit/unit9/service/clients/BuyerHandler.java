package pro.sisit.unit9.service.clients;

import pro.sisit.unit9.entity.Buyer;

public interface BuyerHandler {
    public Buyer save(Buyer buyer);

    public Buyer getBuyer(String name, String address);
}
