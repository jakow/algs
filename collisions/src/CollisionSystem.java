import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by jakub on 26/02/2017.
 */
public class CollisionSystem {
    private static double REFRESH_RATE = 2;
    private MinPQ<Event> eventQueue;
    private double t = 0.0;
    private Particle[] particles;
    public CollisionSystem(Particle[] particles) {
        this.particles = particles;
        StdDraw.enableDoubleBuffering();
    }
    private void predict(Particle a) {
        if (a == null) return;

        for (int j = 0; j < particles.length; ++j) {
            Particle b = particles[j];
            double timeToHit = a.timeToHitParticle(b);
            if (timeToHit != Double.POSITIVE_INFINITY) // avoid polluting the queue with events that will not occur
                eventQueue.insert(new Event(t + timeToHit, a, b));
        }
        eventQueue.insert(new Event(t + a.timeToHitVerticalWall(), a, null)); // insert new events
        eventQueue.insert(new Event(t + a.timeToHitHorizontalWall(), null, a)); // insert new events
    }

    public void simulate() {
        eventQueue = new MinPQ<>(3*particles.length);

        for (int i = 0; i < particles.length; ++i) predict(particles[i]);
        eventQueue.insert(new Event(0, null, null)); // redraw event

        while (!eventQueue.isEmpty()) {
            Event e = eventQueue.delMin();
            if (!e.isValid()) continue;

//            System.out.println(e.toString());
            for (int i = 0; i < particles.length; ++i)
                particles[i].move(e.time - t);
            t = e.time; // update the time

            Particle a = e.a, b = e.b;
            if (a != null && b != null) a.bounceOffParticle(b);
            else if (a != null) a.bounceOffVerticalWall(); // only b null
            else if (b != null) b.bounceOffHorizontalWall(); // only a null
            else redraw();
            // enqueue new events
            predict(a);
            predict(b);
        }
    }


    private void redraw() {
        StdDraw.clear();
        for(Particle p : particles) {
            p.draw();
        }
        StdDraw.show();
        StdDraw.pause(20);
        eventQueue.insert(new Event(t + REFRESH_RATE, null, null));
    }

    public static void main(String[] args) {
        int N;
        try {
            N = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            N = 100;
        }
        Particle[] particles = new Particle[N];
        for (int i = 0; i < particles.length; ++i) {
            particles[i] = new Particle();
        }
        CollisionSystem cs = new CollisionSystem(particles);
        cs.simulate();
    }
}
