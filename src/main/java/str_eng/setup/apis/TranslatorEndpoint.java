package str_eng.setup.apis;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Set up the endpoint for use
@RestController                                             // Endpoint using Springboot REST API framework
@RequestMapping(value = "/send")                            // The API's endpoint as a link
@CrossOrigin(origins = "http://127.0.0.1:5500")             // Allows the API to receive requests from the localhost server
public class TranslatorEndpoint {

    @GetMapping
    public String translatedString(@RequestParam String initString){
        // Correctly format the string before returning it.
        String finishedTranslation = String.format("Received data: %s.", initString);
        return finishedTranslation;
    }
    
}
