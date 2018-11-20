package com.ebay.jimo.tacocloud.data;

import com.ebay.jimo.tacocloud.domain.Order;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByZip(String zip);
    List<Order> readOrdersByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);
    List<Order> findByCityOrderByPlacedAt(String city);
}
