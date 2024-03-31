package tw.jingxing.rentcar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.jingxing.rentcar.model.CarModeSearch;

@RestController
public class ReadCarModelController {
	
	ArrayList<CarModeSearch> CarModels = new ArrayList<CarModeSearch>();

	public ReadCarModelController() {
		CarModels.add(new CarModeSearch(1,"BMW"));
		CarModels.add(new CarModeSearch(2,"Audi"));
		CarModels.add(new CarModeSearch(3,"Chrysler"));
		CarModels.add(new CarModeSearch(4,"Daihatsu"));
		CarModels.add(new CarModeSearch(5,"Ford"));
		CarModels.add(new CarModeSearch(6,"Honda"));
		CarModels.add(new CarModeSearch(7,"Infiniti"));
		CarModels.add(new CarModeSearch(8,"Jaguar"));
		CarModels.add(new CarModeSearch(9,"Benz"));
		CarModels.add(new CarModeSearch(9,"Mazda"));
		CarModels.add(new CarModeSearch(9,"Lexus"));
	}
	
	@PostMapping("/animalreader.controller2")
	public List<CarModeSearch> processAnimalAction(@RequestParam("keyword") String animalName){
		return searchAnimals(animalName);
	}

	private List<CarModeSearch> searchAnimals(String animalName) {
        ArrayList<CarModeSearch> result = new ArrayList<CarModeSearch>();
        
        for(CarModeSearch a: CarModels) {
        	if(a.getAname().contains(animalName) && animalName.length()!=0) {
        		result.add(a);
        	}
        }
        
		return result;
	}

}
