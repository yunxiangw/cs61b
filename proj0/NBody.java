public class NBody {

    /** Return the radius of the universe */
    public static double readRadius(String fileDir) {
        In in = new In(fileDir);
        int firstItem = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    
    /** Return an array of Planets correspoinding to the planets */
    public static Planet[] readPlanets(String fileDir) {
        In in = new In(fileDir);
        int nPlanets = in.readInt();
        in.readDouble();
        
        Planet[] allPlanets = new Planet[nPlanets];
        
        for (int i = 0; i < nPlanets; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return allPlanets;
    }
    
    /** Draw the universe in its starting position */
	public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] allPlanets = readPlanets(filename);
        double radius = readRadius(filename);
        
        // Draw the background
        StdDraw.setScale(-radius, radius);
   		StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        
        // Draw all planets
        for (Planet P : allPlanets) {
            P.draw();
        }
        
        // Create an animation
        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t < T) {
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];
            for (int i = 0; i < allPlanets.length; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
                
            }
            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet P : allPlanets) {
                P.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        
        // Print info
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                          allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
        }
	}
}
