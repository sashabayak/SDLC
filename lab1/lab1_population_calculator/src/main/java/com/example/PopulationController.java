package com.example;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopulationController {

  private final PopulationModel model;
  private MainView view;
  private InputDialog inputDialog;

  public PopulationController(PopulationModel model) {
	this.model = model;
  }

  public void setView(MainView view) {
	this.view = view;
  }

  public void openInputDialog(JFrame parent) {
	if (inputDialog == null || !inputDialog.isDisplayable()) {
	  inputDialog = new InputDialog(parent, this);
	}

	inputDialog.setValues(
		model.getDay(),
		model.getMonth(),
		model.getYear()
	);

	inputDialog.setLocationRelativeTo(parent);
	inputDialog.setVisible(true);
  }

  public void processInput(String dayText, String monthText, String yearText) {
	try {
	  if (dayText.trim().isEmpty()
		  || monthText.trim().isEmpty()
		  || yearText.trim().isEmpty()) {

		throw new IllegalArgumentException(
			"Все поля должны быть заполнены."
		);
	  }

	  int day = Integer.parseInt(dayText.trim());
	  int month = Integer.parseInt(monthText.trim());
	  int year = Integer.parseInt(yearText.trim());

	  model.setData(day, month, year);

	  if (inputDialog != null) {
		inputDialog.dispose();
	  }

	} catch (NumberFormatException e) {
	  showError("День, месяц и год должны быть целыми числами.");
	} catch (IllegalArgumentException e) {
	  showError(e.getMessage());
	}
  }

  private void showError(String message) {
	JOptionPane.showMessageDialog(
		view,
		message,
		"Ошибка ввода",
		JOptionPane.ERROR_MESSAGE
	);
  }
}