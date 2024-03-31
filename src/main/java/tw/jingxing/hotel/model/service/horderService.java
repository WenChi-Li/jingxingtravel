package tw.jingxing.hotel.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.jingxing.hotel.model.bean.hOrder;
import tw.jingxing.hotel.model.repository.horderRepository;

@Service
public class horderService  {

    @Autowired
    horderRepository hOrderRepository;

   
    public void save(hOrder hOrder) {
        hOrderRepository.save(hOrder);
    }

    
    public List<hOrder> findByMid(int mid) {
        return hOrderRepository.findByMid(mid);
    }


    public List<hOrder> getAllhOrders() {
        return hOrderRepository.findAll();
    }
}
