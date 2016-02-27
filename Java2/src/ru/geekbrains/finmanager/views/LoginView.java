package ru.geekbrains.finmanager.views;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

public class LoginView extends JDialog {

	private static final long serialVersionUID = -7518448193672418436L;
	
	private void test() {
		LayoutManager boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
	}

}
