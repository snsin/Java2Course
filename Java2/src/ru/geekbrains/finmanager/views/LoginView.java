package ru.geekbrains.finmanager.views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginView extends JPanel {

	private static final long serialVersionUID = -7518448193672418436L;
	
	private JButton signIn;
	private JButton cancel;
	private JButton logIn;
	private String userName;
	private String password;
	
	public LoginView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel loginField = createInputField("Login:");
		loginField.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		add(loginField);
		JPanel passwordField = createInputField("Password:");
		passwordField.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		add(passwordField);
		signIn = new JButton("SignIn");
		cancel = new JButton("Cancel");
		logIn = new JButton("LogIn");
		add(createButtonsBar(Arrays.asList(signIn, cancel, logIn)));
		
	}
	
	private void test() {
		LayoutManager boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
	}
	
	private static JButton createButton (String label, JComponent comp) {
		JButton button = new JButton(label);
		comp.add(button);
		return button;
	}
	
	private static JPanel createInputField(String label) {
		JPanel field = new JPanel();
		field.setLayout(new BoxLayout(field, BoxLayout.X_AXIS));
		field.add(new JLabel(label));
		field.add(Box.createRigidArea(new Dimension(20, 0)));
		JTextField text = new JTextField();
		field.add(text);
		return field;
	}
	
	private static JPanel createButtonsBar(List<JButton> buttons) {
		JPanel buttonsBar = new JPanel();
		buttonsBar.setLayout(new BoxLayout(buttonsBar, BoxLayout.X_AXIS));
		Component lastGlue = null;
		for (JButton button : buttons) {
			buttonsBar.add(button);
			buttonsBar.add((lastGlue = Box.createHorizontalGlue()));
		}
		buttonsBar.remove(lastGlue);
		return buttonsBar;
	}

}
