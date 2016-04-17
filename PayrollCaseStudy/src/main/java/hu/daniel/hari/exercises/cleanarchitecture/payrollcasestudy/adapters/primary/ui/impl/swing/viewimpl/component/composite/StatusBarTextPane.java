package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.adapters.primary.ui.impl.swing.viewimpl.component.composite;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

//TODO: CLEAN
public class StatusBarTextPane extends JTextPane {

	private static final Color BLACK_COLOR = Color.black;
	private static final Color DEFAULT_FONT_COLOR = Color.WHITE;
	private static final Color DEFAULT_BG_COLOR = Color.WHITE;
	private static final int TIMER_PERIOD_MS = 1000;
	
	private final static int STATUS_STOPPED = 0;
	private final static int STATUS_LEADING = 1;
	private final static int STATUS_SHOWBIG = 2;
	private final static int STATUS_SHOWSMALL = 3;
	
	private final static int DELAY_LEADING = 100;
	private final static int DELAY_SHOWBIG = 10000;
	private final static int DELAY_SHOWSMALL = 10000;

	private Timer timer;
	private int timerstatus = STATUS_STOPPED;
	private Message waitForLeadingMessage;

	public StatusBarTextPane() {
		super();
		init();
	}

	private void init() {
		setEditable(false);
		initDefaultStyles();
		initTimer();
		clearText();
	}

	private void initDefaultStyles() {
		// SET FONT COLOR
		StyledDocument styledDocument = this.getStyledDocument();
		SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
		StyleConstants.setForeground(simpleAttributeSet, DEFAULT_FONT_COLOR);
		styledDocument.setParagraphAttributes(0, styledDocument.getLength(), simpleAttributeSet, false);

		// SET CENTER ALIGNMENT
		StyledDocument doc = this.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
	}

	private void initTimer() {
		timer = new Timer(TIMER_PERIOD_MS, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerRing();
			}
		});
	}

	private void setStatus(int newstatus) {
		if (newstatus == STATUS_LEADING) {
			timer.stop();
			clearText();
			timer.setInitialDelay(DELAY_LEADING);
			timer.start();
		} else if (newstatus == STATUS_SHOWBIG) {
			timer.stop();
			timer.setInitialDelay(DELAY_SHOWBIG);
			timer.start();
		} else if (newstatus == STATUS_SHOWSMALL) {
			timer.stop();
			timer.setInitialDelay(DELAY_SHOWSMALL);
			timer.start();
		} else if (newstatus == STATUS_STOPPED) {
			timer.stop();
		} else {
		}
		timerstatus = newstatus;
	}

	private void timerRing() {
		switch (timerstatus) {
		case STATUS_LEADING: {// Leading vége, most jön a show.
			timer.stop();
			setStatus(STATUS_SHOWBIG);
			setBGColor(waitForLeadingMessage.col);
			setNewText(waitForLeadingMessage.msg);
			waitForLeadingMessage = null;
			return;
		}
		case STATUS_SHOWBIG: {
			setStatus(STATUS_STOPPED);
			clearText();
			return;
		}
		case STATUS_SHOWSMALL: {
			setStatus(STATUS_STOPPED);
			clearText();
			return;
		}
		}
	}

	private void setBGColor(Color col) {
		setBackground(col);
	}

	public void setMessage(String message, Color backgroundColor) {
		if (timerstatus == STATUS_SHOWBIG || timerstatus == STATUS_SHOWSMALL) {
			// Leading indítása
			waitForLeadingMessage = new Message(message, backgroundColor);
			setStatus(STATUS_LEADING);
		} else if (timerstatus == STATUS_LEADING) {
			waitForLeadingMessage = new Message(message, backgroundColor);
		} else if (timerstatus == STATUS_STOPPED) {
			setStatus(STATUS_SHOWBIG);
			setBGColor(backgroundColor);
			setNewText(message);
		}
	}

	private void setNewText(String text) {
		super.setText(text);
	}

	private void clearText() {
		super.setText("");
		setBGColor(DEFAULT_BG_COLOR);
	}

	private static class Message {
		public String msg;
		public Color col;

		public Message(String msg, Color col) {
			this.msg = msg;
			this.col = col;
		}
	}
}
