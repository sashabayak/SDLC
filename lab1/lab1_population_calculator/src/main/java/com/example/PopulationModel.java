package com.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class PopulationModel {

  private static final double START_POPULATION = 7_950_000_000.0;
  private static final int START_YEAR = 2022;
  private static final double ANNUAL_GROWTH_RATE = 0.0098;

  private int day;
  private int month;
  private int year;
  private long calculatedPopulation;

  private final List<ModelListener> listeners = new ArrayList<>();

  public interface ModelListener {
	void onModelChanged();
  }

  public void addListener(ModelListener listener) {
	listeners.add(listener);
  }

  private void notifyListeners() {
	for (ModelListener listener : listeners) {
	  listener.onModelChanged();
	}
  }

  public void setData(int day, int month, int year) {
	if (year < 1900 || year > 2200) {
	  throw new IllegalArgumentException(
		  "Год должен находиться в диапазоне от 1900 до 2200."
	  );
	}

	if (month < 1 || month > 12) {
	  throw new IllegalArgumentException(
		  "Месяц должен находиться в диапазоне от 1 до 12."
	  );
	}

	if (day < 1 || day > 31) {
	  throw new IllegalArgumentException(
		  "День должен находиться в диапазоне от 1 до 31."
	  );
	}

	try {
	  LocalDate.of(year, month, day);
	} catch (Exception e) {
	  throw new IllegalArgumentException(
		  "Указанной даты не существует."
	  );
	}

	this.day = day;
	this.month = month;
	this.year = year;

	calculatePopulation();
	notifyListeners();
  }

  private void calculatePopulation() {
	LocalDate startDate = LocalDate.of(START_YEAR, 1, 1);
	LocalDate targetDate = LocalDate.of(year, month, day);
	long daysBetween = ChronoUnit.DAYS.between(startDate, targetDate);
	double elapsedYears = daysBetween / 365.2425;

	this.calculatedPopulation =
		(long) (START_POPULATION * Math.exp(ANNUAL_GROWTH_RATE * elapsedYears));
  }

  public int getDay() {
	return day;
  }

  public int getMonth() {
	return month;
  }

  public int getYear() {
	return year;
  }

  public long getCalculatedPopulation() {
	return calculatedPopulation;
  }

  public boolean hasData() {
	return year != 0;
  }
}