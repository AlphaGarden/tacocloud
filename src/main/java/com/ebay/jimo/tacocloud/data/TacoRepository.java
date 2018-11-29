package com.ebay.jimo.tacocloud.data;

import com.ebay.jimo.tacocloud.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
