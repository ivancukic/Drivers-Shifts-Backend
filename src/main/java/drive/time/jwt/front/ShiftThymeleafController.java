package drive.time.jwt.front;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import drive.time.jwt.entity.Driver;
import drive.time.jwt.entity.Line;
import drive.time.jwt.service.DriverService;
import drive.time.jwt.service.LineService;

@Controller
@RequestMapping("/shift")
public class ShiftThymeleafController {
	
	private LineService lineService;
	private DriverService driverService;
	
	@Autowired
	public ShiftThymeleafController(LineService lineService, DriverService driverService) {
		this.lineService = lineService;
		this.driverService = driverService;
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<Line> lineList = lineService.findAll();
		
		model.addAttribute("shiftList", lineList);
		
		return "shift/shift-list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("lineId") Integer theId, Model model) {
		
		Optional<Line> line = lineService.findByID(theId);
		
		List<Driver> list = driverService.findAll();
		List<String> driversNames = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++) {
			
			driversNames.add(list.get(i).getName());
		} 
		
		model.addAttribute("line", line);
		model.addAttribute("driversNames", driversNames);
		
		return "shift/shift-form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("line") Line line) {
		
		lineService.save(line);
		
		return "redirect:/shift/list";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("lineId") Integer theId) {
		
		driverService.delete(theId);
		
		return "redirect:/shift/list";
	}

}
