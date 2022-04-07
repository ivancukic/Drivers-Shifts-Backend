package drive.time.jwt.front;

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

import drive.time.jwt.entity.Line;
import drive.time.jwt.service.LineService;

@Controller
@RequestMapping("/line")
public class LineThymeleafController {

	private LineService lineService;

	@Autowired
	public LineThymeleafController(LineService lineService) {
		this.lineService = lineService;
	}

	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<Line> lineList = lineService.findAll();
		
		model.addAttribute("lineList", lineList);
		
		return "line/line-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Line line = new Line();
		
		model.addAttribute("line", line);
		
		return "line/line-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("lineId") Integer theId, Model model) {
		
		Optional<Line> line = lineService.findByID(theId);
		
		model.addAttribute("line", line);
		
		return "line/line-form";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("line") Line line) {
		
		lineService.save(line);
		lineService.calcTotalTime(line.getEnd_time(), line.getStart_time());
		lineService.negativeTime();
		lineService.numberOfDrivers();
		
		return "redirect:/line/list";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("lineId") Integer theId) {
		
		lineService.delete(theId);
		
		return "redirect:/line/list";
	}
	
}
