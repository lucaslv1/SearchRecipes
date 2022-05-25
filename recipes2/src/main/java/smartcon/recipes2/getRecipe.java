package smartcon.recipes2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recipes")
public class getRecipe {
	
	@GetMapping(value = "/{dish}")
	public String Recipes(@PathVariable(name = "dish") String dish)  throws IOException, InterruptedException {
		return GenerateRecipes(dish);
	}
	
	public String GenerateRecipes(String dish) throws IOException, InterruptedException { 
		
		String URL_GET = "https://forkify-api.herokuapp.com/api/search?q=".concat(dish);
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder()
			.GET()
			.timeout(Duration.ofSeconds(10))
			.uri(URI.create(URL_GET))
			.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		return response.body();
	}
	
}
