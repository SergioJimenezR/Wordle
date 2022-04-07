package sergiojimenez.wordle.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wordle")
public class WordleController {

	@GetMapping("/getNuevaPalabra")
	public String getNuevaPalabra() {
		return "REINA";
	}

}
