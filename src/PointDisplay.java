import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A class for displaying a collection of points. A PointDisplay object takes an
 * array of Point objects and displays it, with options to specify the size of
 * the points and whether the points should be connected.
 * 
 * @author Norm Krumpe
 */

public class PointDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private PointPanel panel;

	/**
	 * Constructs a PointDisplay with a given array of points and a diameter to
	 * be used for displaying those points. Add this display to a JFrame object
	 * and it will automatically show its points.
	 * 
	 * @param points
	 *            An array of Point objects to be displayed
	 * @param diameter
	 *            The diameter, in pixels, of each point to be displayed
	 * @param connectTheDots
	 *            A flag to indicate whether each dot should be connected to the
	 *            previous
	 * @param jiggleFactor
	 *            The amount of random jiggling that should take place for each
	 *            point, in pixels. 0 means there will be no jiggling.
	 * 
	 */
	public PointDisplay(Point[] points, int diameter, boolean connectTheDots,
			int jiggleFactor) {
		super("Points Lab");
		panel = new PointPanel(points, diameter, connectTheDots, jiggleFactor);
		this.add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		// The 10 is the delay, in milliseconds, for the timer. Increasing
		// the 10 will slow down the frame rate.
		Timer timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.repaint();
			}
		});
		timer.start();
	}

	/**
	 * Constructs a PointDisplay with a given array of points and a diameter to
	 * be used for displaying those points. Add this display to a JFrame object
	 * and it will automatically show its points. The points won't jiggle.
	 * 
	 * @param points
	 *            An array of Point objects to be displayed
	 * @param diameter
	 *            The diameter, in pixels, of each point to be displayed
	 * @param connectTheDots
	 *            A flag to indicate whether each dot should be connected to the
	 *            previous
	 */
	public PointDisplay(Point[] points, int diameter, boolean connectTheDots) {
		this(points, diameter, connectTheDots, 0);
	}

	/**
	 * Constructs a PointDisplay with a given array of points. Points will have
	 * a default diameter of 10 pixels, and will be connected to each other, and
	 * won't jiggle.
	 * 
	 * @param points
	 *            An array of Point objects to be displayed
	 */
	public PointDisplay(Point[] points) {
		this(points, 10, true, 0);
	}

	private class PointPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private Point[] points;
		private int diameter;
		private boolean connectTheDots;
		private int jiggleFactor;

		/**
		 * Creates a panel for the points using specifics about the diameter,
		 * the connectedness, and the jiggling.
		 * 
		 * @param points
		 *            The points that will be displayed on the panel
		 * @param diameter
		 *            The diameter, in pixels, of each point
		 * @param connectTheDots
		 *            Whether each dot should be connected to the next dot
		 * @param jiggleFactor
		 *            The amount that each points should randomly jiggle.
		 */
		public PointPanel(Point[] points, int diameter, boolean connectTheDots,
				int jiggleFactor) {
			this.setPreferredSize(new Dimension(500, 500));
			this.points = points;
			this.diameter = diameter;
			this.connectTheDots = connectTheDots;
			this.jiggleFactor = jiggleFactor;
		}

		// What should happen each time the window is repainted
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.gray);

			if (points != null) {
				Point lastPoint = null;
				int step = 0;
				for (Point p : points) {
					int shade = 255 * step / points.length;
					step++;
					g.setColor(new Color(shade, 0, 255 - shade));

					// Draw the point, and maybe connect to the previous point
					if (p != null) {
						int jiggleX = (int) (jiggleFactor * Math.random() - jiggleFactor / 2);
						int jiggleY = (int) (jiggleFactor * Math.random() - jiggleFactor / 2);

						g.fillOval(p.x + jiggleX - diameter / 2, p.y + jiggleY
								- diameter / 2, diameter, diameter);
						if (connectTheDots) {
							if (lastPoint != null)
								g.drawLine(lastPoint.x, lastPoint.y, p.x, p.y);
							lastPoint = p;
						}
					} // end of drawing one point
				} // End of loop that draws all points
			} // end of non-null logic
		} // end paintComponent

	}

}