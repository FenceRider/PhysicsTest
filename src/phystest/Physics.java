/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phystest;

import java.util.ArrayList;

/**
 *
 * 
 */
public class Physics {

	private ArrayList<Vector> v;
	private Vector Gravity = new Vector(0, 0, -1);
	private Vector velocity = new Vector(0, 0, -1);

	private double grav_accel = 0;
	private double mass;
	private double x, y;
	private boolean fixed;

	/**
	 * This is the radius around the object that collisions are checked for within
	 * 
	 * The distance between an object's pre and post update location alters the
	 * collision detection radius.
	 * 
	 */
	private double radius = 0;
	private double adjustedRadius = 0;

	public Physics() {

		v = new ArrayList();

		x = 0;
		y = 0;
		mass = 0;
		fixed = false;
	}

	/**
	 * Given a physics object this method will update the x,y coordinates according
	 * to active vectors
	 *
	 * @param fps frames per second
	 * @return
	 */
	public void updateLocation(double fps) {
		// do some fun math here!

		if (!fixed) {

			Vector df = new Vector(0, 0, 0);
			this.Gravity.setXY_comp(0, 900);
			df.add(Gravity);
			df.addList(v, true);

			double tpf = fps / 1000;

			double dxa = (df.get_x_comp() / mass) * tpf;
			double dya = (df.get_y_comp() / mass) * tpf;

			velocity.setXY_comp(dxa + velocity.get_x_comp(), dya + velocity.get_y_comp());

			this.x += velocity.get_x_comp() * tpf;
			this.y += velocity.get_y_comp() * tpf;

		}

	}

	public void clearVectors() {
		v.clear();
	}

	public void addVector(Vector vec) {
		v.add(vec);
	}

	public void setGravity(Vector acceleration) {

		grav_accel = acceleration.getMagnitude();
		Gravity.setDirection(acceleration.getDirection());
		Gravity.setMagnitude(mass * grav_accel);

	}

	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double get_x() {
		return x;
	}

	public double get_y() {
		return y;
	}

	public void setMass(double m) {
		this.mass = m;
		setGravity(new Vector(grav_accel, Gravity.getDirection(), -1));
	}

	public double get_mass() {
		return mass;
	}

	public double get_velocity() {
		return this.velocity.getMagnitude();
	}

	public double get_direction() {
		return this.velocity.getDirection();
	}

	public boolean getFixed() {
		return fixed;
	}

	public void setFixed(boolean b) {
		fixed = b;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * calculate the velocity after a collision has been detected This does not
	 * detect collisions
	 * 
	 * @param p1
	 * @param p2
	 */
	public static void Collision(Physics p1, Physics p2, boolean isHoriz) {

		/**
		 * this would be good if i were making a billiards game... but im not, so what
		 * do i do.
		 */

		double vel_x_1 = p1.velocity.get_x_comp();
		double vel_y_1 = p1.velocity.get_y_comp();

		double vel_x_2 = p2.velocity.get_x_comp();
		double vel_y_2 = p2.velocity.get_y_comp();

		// vf1 = [(m1 - m2)·vi1 + 2 m2·vi2]/(m1 + m2)
		// vf2 = [2 m1·vi1 - (m1 - m2)·vi2]/(m1 + m2)

		if (!p1.fixed) {

			if (isHoriz) {
				p1.velocity.setXY_comp(((p1.mass - p2.mass) * vel_x_1 + 2 * p2.mass * vel_x_2) / (p1.mass + p2.mass),
						-((p1.mass - p2.mass) * vel_y_1 + 2 * p2.mass * vel_y_2) / (p1.mass + p2.mass));
			} else {
				p1.velocity.setXY_comp(-((p1.mass - p2.mass) * vel_x_1 + 2 * p2.mass * vel_x_2) / (p1.mass + p2.mass),
						((p1.mass - p2.mass) * vel_y_1 + 2 * p2.mass * vel_y_2) / (p1.mass + p2.mass));
			}
		}

		if (!p2.fixed) {
			if (isHoriz) {
				p2.velocity.setXY_comp((2 * p1.mass * vel_x_1 - (p1.mass - p2.mass) * vel_x_2) / (p1.mass + p2.mass),
						-(2 * p1.mass * vel_y_1 - (p1.mass - p2.mass) * vel_y_2) / (p1.mass + p2.mass));
			} else {
				p2.velocity.setXY_comp(-(2 * p1.mass * vel_x_1 - (p1.mass - p2.mass) * vel_x_2) / (p1.mass + p2.mass),
						(2 * p1.mass * vel_y_1 - (p1.mass - p2.mass) * vel_y_2) / (p1.mass + p2.mass));
			}

		}

	}

	public static double CheckCollision(Physics p1, Physics p2, double fps) {
		/**
		 * Check for collision in the space between post and pre frame update
		 * 
		 */

		double tpf = fps / 1000;

		double slopeP1 = (p1.velocity.get_y_comp()) / (p1.velocity.get_x_comp());

		// y-y1 = m(x-x1)

		// y = mx+b
		// b = y-mx

		double bP1 = p1.y - p1.x * slopeP1;

		double slopeP2 = (p2.velocity.get_y_comp()) / (p2.velocity.get_x_comp());

		// y = slopeP1*(x-p1.x)+p1.y;
		// y = slopeP2*(x-p2.x)+p2.y

		double inx_x = (slopeP2 * p2.x + p2.y - p1.y) / (slopeP1 - slopeP2);
		double inx_y = (slopeP1 * (inx_x - p1.x)) + p1.y;

		/**
		 * Get the magnitude distance traveled create a displacement function and find
		 * the a t that relates to the intersection point if the t is within the range
		 * of the frame then a collision has occurred
		 * 
		 * 
		 * i dont think this will work
		 * 
		 */

		if (inx_x < p1.get_x() && inx_x > p1.get_x() - p1.velocity.get_x_comp() * tpf) {
			// Paths crossed for the given time... return x and allow object to
			// check for specific dimesions given the x value

		}

		/**
		 * 
		 * //y = mx+b //b = y-mx
		 * 
		 * double bP2 = p2.y - p2.x * slopeP1;
		 * 
		 * //x is point of intersection // x = (bP1 - bP2) / (slopeP2 - SlopeP1) double
		 * x = (bP1 - bP2) / (slopeP2 - slopeP1);
		 * 
		 * if(x < p1.get_x() && x > p1.get_x() - p1.velocity.get_x_comp()*tpf){ //Paths
		 * crossed for the given time... return x and allow object to //check for
		 * specific dimesions given the x value
		 * 
		 * 
		 * } return 0;
		 * 
		 **/
		return 0;
	}

	// put these in a helper class maybe later!
	public static double timePerFrame(double fps) {
		return 1.0 / fps;
	}

}
