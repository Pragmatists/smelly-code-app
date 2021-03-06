/******************************************************************************************
 * MessageFrame.java               PokerApp                                               *
 *                                                                                        *
 *   Revision History                                                                     *
 * +---------+----------+---------------------------------------------------------------+ *
 * | Version | DATE     | Description                                                   | *
 * +---------+----------+---------------------------------------------------------------+ *
 * |  1.00   | 08/26/05 | Initial documented release                                    | *
 * |  1.00   | 07/05/07 | Prepare for open source.  Header/comments/package/etc...      | *
 * +---------+----------+---------------------------------------------------------------+ *
 *                                                                                        *
 * PokerApp Copyright (C) 2004  Dan Puperi                                                *
 *                                                                                        *
 *   This program is free software: you can redistribute it and/or modify                 *
 *   it under the terms of the GNU General Public License as published by                 *
 *   the Free Software Foundation, either version 3 of the License, or                    *
 *   (at your option) any later version.                                                  *
 *                                                                                        *
 *   This program is distributed in the hope that it will be useful,                      *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of                       *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                        *
 *   GNU General Public License for more details.                                         *
 *                                                                                        *
 *   You should have received a copy of the GNU General Public License                    *
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>                 *
 *                                                                                        *
 ******************************************************************************************/

package net.sourceforge.pokerapp;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/****************************************************
 * The MessageFrame class represents the window which shows the most recent
 * messages from the server.
 * 
 * @author Dan Puperi
 * @version 1.00
 * 
 **/
public class MessageFrame extends JFrame {
	private PokerApp theApp; // The PokerApp instance to which this class
								// belongs
	private DefaultListModel messageListModel; // The list model of all of the
												// messages
	private JScrollPane messageListScrollPane; // The scroll pane where the
												// messages are displayed
	private JList messageList; // The list of messages

	/***************************
	 * The default constructor creates the message window.
	 * 
	 * @param title
	 *            The text that will be displayed in the title bar
	 * @param a
	 *            The PokerApp class to which this window belongs.
	 * 
	 **/
	public MessageFrame(String title, PokerApp a) {
		super(title);
		ClassLoader cl = getClass().getClassLoader();
		java.net.URL url = cl.getResource("Images/icon.gif");
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));
		theApp = a;

		theApp.log("Constructing MessageFrame", 3);
		getContentPane().setLayout(new BorderLayout());

		JPanel messagePanel = new JPanel(new BorderLayout());
		messagePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		messageListModel = new DefaultListModel();
		messageList = new JList(messageListModel);
		messageListScrollPane = new JScrollPane(messageList);
		messageListScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		messageListScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		messagePanel.add(messageListScrollPane, BorderLayout.CENTER);
		getContentPane().add(messagePanel, BorderLayout.CENTER);

		for (int i = 0; i < PokerApp.NUM_SAVED_MESSAGES; i++)
			messageListModel.insertElementAt(theApp.pastMessages[i], 0);
	}

	/***************************
	 * sendMessage() displays a message on the scoll pane
	 * 
	 * @param m
	 *            The message that will be displayed
	 * 
	 **/
	public void sendMessage(String m) {
		messageListModel.insertElementAt(m, 0);
	}

	/***************************
	 * processWindowEvent() handles window events - specifically what to do when
	 * the window is closed
	 * 
	 * @param e
	 *            The event to process
	 * 
	 **/
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			dispose();
		}
		super.processWindowEvent(e);
	}
}