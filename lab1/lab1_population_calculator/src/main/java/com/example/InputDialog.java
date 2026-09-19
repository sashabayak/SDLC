package com.example;

import javax.swing.*;
import java.awt.*;

public class InputDialog extends JDialog {

  private final JTextField txtDay = new JTextField();
  private final JTextField txtMonth = new JTextField();
  private final JTextField txtYear = new JTextField();

  public InputDialog(JFrame parent, PopulationController controller) {
	super(parent, "Ввод даты", true);

	setSize(360, 230);
	setResizable(false);

	JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
	panel.setBorder(
		BorderFactory.createEmptyBorder(20, 20, 20, 20)
	);

	panel.add(new JLabel("День (1-31):"));
	panel.add(txtDay);

	panel.add(new JLabel("Месяц (1-12):"));
	panel.add(txtMonth);

	panel.add(new JLabel("Год (1900-2200):"));
	panel.add(txtYear);

	JButton btnOk = new JButton("OK");
	JButton btnCancel = new JButton("Отмена");

	panel.add(btnCancel);
	panel.add(btnOk);

	add(panel);

	btnOk.addActionListener(e ->
		controller.processInput(
			txtDay.getText(),
			txtMonth.getText(),
			txtYear.getText()
		)
	);

	btnCancel.addActionListener(e -> dispose());

	txtDay.addActionListener(e -> btnOk.doClick());
	txtMonth.addActionListener(e -> btnOk.doClick());
	txtYear.addActionListener(e -> btnOk.doClick());
  }

  public void setValues(int day, int month, int year) {
	if (year != 0) {
	  txtDay.setText(String.valueOf(day));
	  txtMonth.setText(String.valueOf(month));
	  txtYear.setText(String.valueOf(year));
	} else {
	  txtDay.setText("");
	  txtMonth.setText("");
	  txtYear.setText("");
	}
  }
}