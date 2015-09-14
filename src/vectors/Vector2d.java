/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vectors;

/**
 *
 * @author nbleier
 */
public class Vector2d extends Vector {
    //double[] values;

    public Vector2d(double[] values) {
        super(values);
        if ( values.length != 2 )
            throw new IllegalArgumentException("Constructor overriden.  Use other constructor");
    }
    
    public Vector2d(double i, double j) {
        double[] res = {i, j};
        values = res;
    }
    
    public Vector2d(double r, double theta, boolean polar) {
        this(r*Math.cos(theta), r*Math.sin(theta) );
    }
    
    @Override
    public int dimensions() { return 2;}
    
    @Override
    public double magnitude() {
        return Math.sqrt(values[0]*values[0] + values[1] * values[1]);
    }
    
    public double getTheta() {
        return Math.atan(values[1] / values[0]);
    }
    
    public double[] decomposePolar() {
        double[] result = {this.magnitude(), this.getTheta()};
        return result;
    }
}
