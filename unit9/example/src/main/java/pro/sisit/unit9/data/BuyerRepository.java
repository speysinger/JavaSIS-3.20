package pro.sisit.unit9.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sisit.unit9.entity.Buyer;

import java.util.List;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Long> {
    Buyer save(Buyer buyer);

    List<Buyer> findByNameAndAddress(String name, String address);
}
