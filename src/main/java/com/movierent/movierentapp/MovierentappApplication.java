package com.movierent.movierentapp;


import com.movierent.movierentapp.domen.AgeCategory;
import com.movierent.movierentapp.domen.StyleCategory;
import com.movierent.movierentapp.service.CustomerService;
import com.movierent.movierentapp.service.FilmService;
import com.movierent.movierentapp.service.RentingService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class MovierentappApplication implements CommandLineRunner {

	//import file with JSON format data
	@Value("${movierentapp.importfile}")
	private String importFile;

	@Value("${customersinfo.importfile}")
	private String importClientsInfo;
	//link to  film service class

	@Value("${rentingsinfo.importfile}")
	private String importRentingInfo;

	@Autowired
	private FilmService filmService;

	@Autowired
	private RentingService rentingService;

	@Autowired
	private CustomerService customerService;


	public static void main(String[] args) {
		SpringApplication.run(MovierentappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addFilms(importFile);
		long numOfFilms = filmService.total();
		addClients(importClientsInfo);
		long numOfClients = customerService.total();
		addRentings(importRentingInfo);
		long numOfRentings = rentingService.total();
	}

	//class for adding data from file to repository
	private void addFilms(String fileToImport) throws IOException {
		FilmFromFile.read(fileToImport).forEach(importedFilm ->
				filmService.addFilm(
				importedFilm.getTitle(),
				importedFilm.getDescription(),
				importedFilm.getReleaseDate(),
				importedFilm.getDuration(),
				importedFilm.getAvailability(),
				importedFilm.getMainActors(),
				importedFilm.getAgeCategory(),
				importedFilm.getStyleCategory()));
	}
	private void addClients(String filetoImport) throws IOException {
		CustomerFromFile.read(filetoImport).forEach(importedClient ->
				customerService.addCustomer(importedClient.getName(), importedClient.getRentings()));
	}
	private void addRentings(String fileToImport) throws IOException {
		RentingFromFile.read(fileToImport).forEach(importedRenting ->
				rentingService.addRenting(
						importedRenting.getCustomerId(),
						importedRenting.getRentings(),
						importedRenting.getPrice()));
	}

	private static class RentingFromFile{
		private Integer customerId;
		private String rentings;
		private Double price;
		static  List<RentingFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().setVisibility(FIELD, ANY).
					readValue(new FileInputStream(fileToImport), new TypeReference<List<RentingFromFile>>() {});
		}
		protected RentingFromFile(){}

		public Integer getCustomerId() { return customerId; }

		public String getRentings() { return rentings; }

		public Double getPrice(){ return price; }

	}


	private static class CustomerFromFile{
		private Integer customerId;
		private String name;
		private String rentings;
		static  List<CustomerFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().setVisibility(FIELD, ANY).
					readValue(new FileInputStream(fileToImport), new TypeReference<List<CustomerFromFile>>() {});
		}
		protected CustomerFromFile(){}

		public Integer getCustomerId() {
			return customerId;
		}

		public String getName() {
			return name;
		}

		public String getRentings() {
			return rentings;
		}
	}
	//Class for reformating data per film for adding it in class above
	private static class FilmFromFile {
		//fields
		private String title, description, releaseDate, availability, mainActors, ageCategory, styleCategory;
		private Integer duration;

		//reader
		static List<FilmFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().setVisibility(FIELD, ANY).
					readValue(new FileInputStream(fileToImport), new TypeReference<List<FilmFromFile>>() {});
		}
		protected FilmFromFile(){}

		//Getter methods
		String getTitle() { return title; }

		String getDescription() { return description; }

		//reformating date data from String to LocalDate format
		LocalDate getReleaseDate() {
			LocalDate date = null;
			try {
				date = LocalDate.parse(releaseDate, DateTimeFormatter.ISO_DATE);
			}
			catch (IllegalArgumentException | DateTimeParseException e) {
				System.out.println("Exception " + e);
			}
			return date;
		}
		Integer getDuration() {
			return duration;
		}

		//reformating data for availability status from String to boolean
		boolean getAvailability() {
			boolean status;
			if(availability.equals("available")){
				status = true;
			}
			else{status = false;}
			return status;
		}


		List<String> getMainActors() {
			return Arrays.asList(mainActors.split("\\s*,\\s*"));
		}

		AgeCategory getAgeCategory() { return AgeCategory.valueOf(ageCategory); }

		StyleCategory getStyleCategory() { return StyleCategory.valueOf(styleCategory); }
	}
}
