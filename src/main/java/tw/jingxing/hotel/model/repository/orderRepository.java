package tw.jingxing.hotel.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.jingxing.hotel.model.bean.hOrder;

public interface orderRepository extends JpaRepository<hOrder, Integer> {
    List<hOrder> findByMid(Integer mid);
}
