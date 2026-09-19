package com.example;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame
	implements PopulationModel.ModelListener {

  private final PopulationController controller;
  private final PopulationModel model;

  private final JLabel lblDate =
	  new JLabel("Дата не задана", SwingConstants.CENTER);

  private final JLabel lblPopulation =
	  new JLabel("Численность населения: -", SwingConstants.CENTER);

  public MainView(
	  PopulationController controller,
	  PopulationModel model
  ) {
	this.controller = controller;
	this.model = model;

	model.addListener(this);

	initView();
  }

  private void initView() {
	setTitle("Калькулятор населения мира");
	setSize(520, 300);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);

	JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
	mainPanel.setBorder(
		BorderFactory.createEmptyBorder(25, 30, 25, 30)
	);

	JLabel title = new JLabel(
		"Калькулятор населения мира",
		SwingConstants.CENTER
	);
	title.setFont(new Font("Segoe UI", Font.BOLD, 22));

	JPanel resultPanel = new JPanel(
		new GridLayout(2, 1, 10, 10)
	);

	lblDate.setFont(
		new Font("Segoe UI", Font.PLAIN, 17)
	);

	lblPopulation.setFont(
		new Font("Segoe UI", Font.BOLD, 19)
	);

	resultPanel.add(lblDate);
	resultPanel.add(lblPopulation);

	JButton btnInput = new JButton("Ввести данные");
	btnInput.setFont(
		new Font("Segoe UI", Font.BOLD, 15)
	);

	btnInput.setPreferredSize(
		new Dimension(180, 45)
	);

	btnInput.addActionListener(
		e -> controller.openInputDialog(this)
	);

	JPanel buttonPanel = new JPanel();
	buttonPanel.add(btnInput);

	mainPanel.add(title, BorderLayout.NORTH);
	mainPanel.add(resultPanel, BorderLayout.CENTER);
	mainPanel.add(buttonPanel, BorderLayout.SOUTH);

	add(mainPanel);
  }

  @Override
  public void onModelChanged() {
	lblDate.setText(
		String.format(
			"Введенная дата: %02d.%02d.%d",
			model.getDay(),
			model.getMonth(),
			model.getYear()
		)
	);

	lblPopulation.setText(
		String.format(
			"Население мира: %,d человек",
			model.getCalculatedPopulation()
		)
	);
  }
}