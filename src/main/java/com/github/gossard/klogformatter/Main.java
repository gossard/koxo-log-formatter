package com.github.gossard.klogformatter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class Main {

	private Main() {
	}

	public static void main(String[] args) {
		try {
			setSystemLookAndFeel();

			JFileChooser chooser = createFileChooser();
			if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
				System.exit(0);

			File srcDir = chooser.getSelectedFile();
			File dstDir = new File(srcDir, "formatted-logs");

			LogFormatter formatter = createLogFormatter(srcDir, dstDir);
			int numFormattedFiles = formatter.format();

			JOptionPane.showConfirmDialog(null, "Files formatted: " + numFormattedFiles, "Formatting finished",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "An error are occured", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
	}

	private static void setSystemLookAndFeel() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}

	private static JFileChooser createFileChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select the folder containing the logs");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		return chooser;
	}

	private static LogFormatter createLogFormatter(File srcDir, File dstDir) {

		LogFileFormat format = new LogFileFormat("LOG", 6, "#####", "[LOGOFF]");

		List<LinesFormatter> formatters = new ArrayList<>();
		formatters.add(new RemoveHeaderLinesFormatter(format.getNumHeaderLines()));
		formatters.add(new RemoveIfStartsWithLinesFormatter(format.getLogOffPrefix()));

		return new LogFormatter(srcDir, dstDir, formatters, format);
	}

}
