package tw.jingxing.rentcar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.jingxing.rentcar.model.Car;
import tw.jingxing.rentcar.model.RentalInfo;
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
	
	
	
	 List<Car> findByCarModel_carmodelid(int carTypeID);

	 
	 
	 List<Car> findByStatus(String status);



}
