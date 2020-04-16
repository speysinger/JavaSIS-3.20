package pro.sisit.unit9.service.clients;

import pro.sisit.unit9.entity.Buyer;

/**
 * Интерфейс предназначается для работы с сущностью покупателя в базе данных
 */
public interface BuyerService {
    public Buyer save(Buyer buyer);

    public Buyer getBuyer(String name, String address);
}
