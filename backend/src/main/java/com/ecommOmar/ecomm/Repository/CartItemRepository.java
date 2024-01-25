package com.ecommOmar.ecomm.Repository;


import com.ecommOmar.ecomm.Entity.CartItemes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemes,Long> {


	Optional<CartItemes> findByProductIdAndOrderIdAndUserId(Long productId, Long orderId, Long userId);
}
