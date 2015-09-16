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
    public Vector2d add(Vector v) {
        if ( v.dimensions() != 2 ) 
            throw new IllegalStateException("Cannot add vectors of unequal dimension");
        
        return new Vector2d(this.values[0] + v.getCoordinate(0), this.values[1] + v.getCoordinate(1) );
    }
    
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
    
    @Override
    public Vector2d[] decompose() {
        Vector2d[] result = new Vector2d[2];
        Vector2d x = new Vector2d(this.values[0], 0);
        Vector2d y = new Vector2d(0, this.values[1]);
        result[0] = x;
        result[1] = y;
        return result;
    }
    
    @Override
    public Vector2d toUnitVector() {
        
        return new Vector2d(1, this.getTheta(), true);
    }
    
    @Override
    public Vector2d subtract(Vector v) {
        if ( v.dimensions() != 2 )
            throw new IllegalStateException("Cannot subtract vectors of different dimensions");
        
        double x = this.values[0] - v.getCoordinate(0);
        double y = this.values[1] - v.getCoordinate(1);
        
        return new Vector2d(x, y);
    }
    
    @Override
    public Vector2d scale(double s) {
        return new Vector2d(values[0] * s, values[1] * s);
    }
    
}
