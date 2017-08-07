import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by jakub on 26/02/2017.
 */
public class Particle {
    private static double INFTY = Double.POSITIVE_INFINITY;
    private double rx, ry; // position
    private double vx, vy; // speed
    private final double radius;
    private final double mass;
    public int collisionCount;
    private int count;
    public Particle() {
        rx     = StdRandom.uniform(0.0, 1.0);
        ry     = StdRandom.uniform(0.0, 1.0);
        vx     = StdRandom.uniform(-0.005, 0.005);
        vy     = StdRandom.uniform(-0.005, 0.005);
        radius = 0.01;
        mass   = 0.5;
    }
    public Particle(double radius, double mass, double rx, double ry, double vx, double vy) {
        this.radius = radius;
        this.mass = mass;
        this.rx =  rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;

    }
    public double timeToHitParticle(Particle that) {
        if (this == that) return INFTY;
        double dx  = that.rx - this.rx;
        double dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;
        if (dvdr > 0) return INFTY;
        double dvdv = dvx*dvx + dvy*dvy;
        double drdr = dx*dx + dy*dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
        if (d < 0) return INFTY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public void bounceOffParticle(Particle that) {
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double drx = that.rx - this.rx, dry = that.ry - this.ry;
        double dvdr = dvx * drx  + dvy * dry;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ( (this.mass + that.mass) * dist);
        double Jx = J * drx/ dist;
        double Jy = J * dry/dist;
        this.vx += Jx/this.mass;
        this.vy += Jy/this.mass;
        that.vx -= Jx/that.mass;
        that.vy -= Jy/that.mass;
        this.count++;
        that.count++;
    }

    public void move(double deltaTime) {
        rx += this.vx * deltaTime;
        ry += this.vy * deltaTime;
    }
    public void bounceOffVerticalWall() {
        this.vx = -this.vx;
        count++;
    }

    public void bounceOffHorizontalWall() {
        this.vy = -this.vy;
         count++;
    }

    public double timeToHitVerticalWall() {
        return timeToHitWall(rx, vx);
    }

    public double timeToHitHorizontalWall() {
        return timeToHitWall(ry, vy);
    }

    private double timeToHitWall(double origin, double speed) {
        // assume that the walls (horizontal or vertical) are at (0,1)
        if (speed > 0) return  (1  - origin - radius) / speed;
        else if (speed < 0) return (radius - origin) / speed;
        else return INFTY;
    }

    public int getCount() {
        return count;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }

    public String toString() {
        return String.format("(%.2f, %.2f)", rx, ry);
    }
}
