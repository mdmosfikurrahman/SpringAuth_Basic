package mmr.mosfik.SpringAuth.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/all")
public class AllCanSeeController {
    @GetMapping
    public ResponseEntity<String> sayHelloFromOpenSource() {
        return ResponseEntity.ok("idk u, bt u cn see me");
    }

}