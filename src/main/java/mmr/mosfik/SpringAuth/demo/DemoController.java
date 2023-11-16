package mmr.mosfik.SpringAuth.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping("/all")
    public ResponseEntity<String> sayHelloFromAll() {
        return ResponseEntity.ok("I am from all");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> sayHelloFromAdmin() {
        return ResponseEntity.ok("I am from admin");
    }

    @GetMapping("/user")
    public ResponseEntity<String> sayHelloFromUser() {
        return ResponseEntity.ok("I am from user");
    }

}