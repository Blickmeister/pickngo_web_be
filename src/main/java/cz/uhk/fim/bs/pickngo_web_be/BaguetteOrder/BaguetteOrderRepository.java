package cz.uhk.fim.bs.pickngo_web_be.BaguetteOrder;

import cz.uhk.fim.bs.pickngo_web_be.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaguetteOrderRepository extends JpaRepository<BaguetteOrder, Long> {

    List<Optional<BaguetteOrder>> findAllByCustomer(Optional<Customer> customer);
}
