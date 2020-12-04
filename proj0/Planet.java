public class Planet {
    
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    static final double G = 6.67e-11;
    
    /** Constructor */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    
    public Planet(Planet P) {
        xxPos = P.xxPos;
        yyPos = P.yyPos;
        xxVel = P.xxVel;
        yyVel = P.yyVel;
        mass = P.mass;
        imgFileName = P.imgFileName;
    }
    
    /** Calculate the distance between two Planets */
    public double calcDistance(Planet P) {
        double dx = xxPos - P.xxPos;
        double dy = yyPos - P.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    /** Calculate the force exerted on this planet by the given planet */
    public double calcForceExertedBy(Planet P) {
        double dis = calcDistance(P);
        double force = G * mass * P.mass / (dis * dis);
        return force;
    }
    
    /** Calculate the force exerted on this planet by the given planet in the X direction */
    public double calcForceExertedByX(Planet P) {
        double dx = P.xxPos - xxPos;
        double dis = calcDistance(P);
        double force = calcForceExertedBy(P);
        return force * dx / dis;
    }
    
    /** Calculate the force exerted on this planet by the given planet in the Y direction */
    public double calcForceExertedByY(Planet P) {
        double dy = P.yyPos - yyPos;
        double dis = calcDistance(P);
        double force = calcForceExertedBy(P);
        return force * dy / dis;
    }
    
    /** Calculate the net X force exerted by all planets */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for (Planet P : allPlanets) {
            if (!P.equals(this)) {
                netForceX += calcForceExertedByX(P);
            }
        }
        return netForceX;
    }
    
    /** Calculate the net Y force exerted by all planets */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for (Planet P : allPlanets) {
            if (!P.equals(this)) {
                netForceY += calcForceExertedByY(P);
            }
        }
        return netForceY;
    }

    /** Calculate the changes of the planet’s velocity and position in a small period of time dt */
    public void update(double dt, double xForce, double yForce) {
        double ax = xForce / mass;
        double ay = yForce / mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    
    /** Draw the Planet’s image at the Planet’s position */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}
