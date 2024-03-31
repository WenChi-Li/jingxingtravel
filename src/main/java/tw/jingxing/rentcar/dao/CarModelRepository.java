package tw.jingxing.rentcar.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.jingxing.rentcar.model.CarModel;

public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
	
	
	public Optional<CarModel> findByCarmodelname(String CarModelName);
//	List<CarModel> findByName(String CarModelName);
}
