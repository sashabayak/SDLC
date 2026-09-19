package com.example;

import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	  PopulationModel model = new PopulationModel();
	  PopulationController controller = new PopulationController(model);
	  MainView view = new MainView(controller, model);

	  controller.setView(view);
	  view.setVisible(true);
	});
  }
}