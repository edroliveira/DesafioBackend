package com.desafio.demo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.desafio.demo.model.Forecast;
import com.desafio.demo.repository.ForecastRepository;

@Component
public class InitialDB implements CommandLineRunner {
	
	private ForecastRepository forecastRepository;

	public InitialDB(ForecastRepository forecastRepository) {
		this.forecastRepository = forecastRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		this.forecastRepository.deleteAll();
		
		try {

	        URL url = new URL("https://api.hgbrasil.com/weather");

	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.connect();

	        //Getting the response code
	        int responsecode = conn.getResponseCode();

	        if (responsecode != 200) {
	            throw new RuntimeException("HttpResponseCode: " + responsecode);
	        } else {

	            String inline = "";
	            Scanner scanner = new Scanner(url.openStream());

	            //Write all the JSON data into a string using a scanner
	            while (scanner.hasNext()) {
	                inline += scanner.nextLine();
	            }

	            //Close the scanner
	            scanner.close();

	            //Using the JSON simple library parse the string into a json object
	            JSONParser parse = new JSONParser();
	            JSONObject data_obj = (JSONObject) parse.parse(inline);

	            //Get the required object from the above created object
	            JSONObject obj = (JSONObject) data_obj.get("results");

	            //Get the required data using its key
	            JSONArray new_obj = (JSONArray) obj.get("forecast");
	            System.out.println(new_obj);
	            

	            
	            for (int i = 0; i < 10; i++) {
	            	
	                JSONObject objects = (JSONObject) new_obj.get(i);
	                
	                System.out.println(objects);
	                
	                String  date = (String) objects.get("date");
	                System.out.println(" ~~~~ " + date);
	                
	                String description = (String) objects.get("description");
	                System.out.println(" ~~~~ " + description);
	                
	                String condition = (String) objects.get("condition");
	                System.out.println(" ~~~~ " + condition);
	                
	                Forecast forecast = new Forecast(date, description, condition);
	                this.forecastRepository.save(forecast);
	            	
	            }
	 
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		
		System.out.println(" -- DB Iniciado");
		
	}

}
