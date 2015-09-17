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
public class Vector3d extends Vector {
    //double[] values;

    public Vector3d(double[] values) {
        super(values);
        if ( values.length != 3 )
            throw new IllegalStateException("Constructor overriden.  Use other constructor");
    }
    
    public Vector3d(double i, double j, double k) {
        double[] res = {i, j, k};
        values = res;
    }
    /**
     * This method returns the cross product of two vectors.  The cross product of two vectors 
     * exists only in 3 and 7 dimensions.  It is a vector with direction perpindicular to the
     * plane formed by the two argument vectors and with magnitude equal to the product of the
     * vectors magnitude times sin theta where theta is the angle between the vectors.
     * Cross product is anticommutative: A x B = -B x A
     * Cross product distributes over addition A x (B + C) = A x B + A x C
     * Compatible with scalar multiplication: (rA) x B = A x (rB) = r(A x B)
     * Not assosciative, but satisfies the Jacobi Identity:
     * A x (B x C) + B x (C x A) + C x (A x B) = 0)
     * @param v the second vector multiplicand
     * @return 
     */
    public Vector3d crossProduct(Vector3d v) {
        double a, b, c;
        a = this.getCoordinate(1) * v.getCoordinate(2) - this.getCoordinate(2) * v.getCoordinate(1);
        b = this.getCoordinate(2) * v.getCoordinate(0) - this.getCoordinate(0) * v.getCoordinate(2);
        c = this.getCoordinate(0) * v.getCoordinate(1) - this.getCoordinate(1) * v.getCoordinate(0);
        
        return new Vector3d(a, b, c);
    }
    
    @Override 
    public Vector3d add(Vector v) {
        if ( v.dimensions() != 3 )
            throw new IllegalStateException("Vector must have 3 dimensions to add");
        
        double[] arr = v.getCoordinates();
        return new Vector3d(this.values[0] + arr[0], values[1]+ arr[1], values[2] + arr[2]);
    }
    
    @Override
    public int dimensions() {
        return 3;
    }
    
    @Override
    public Vector3d scale(double s) {
       return new Vector3d(values[0] * s, values[1] * s, values[2] * s); 
    }
    
    @Override
    public Vector3d subtract(Vector v) {
        if ( v.dimensions() != 3 )
            throw new IllegalStateException("Vector must have 3 dimensions to substract");
        
        double[] arr = v.getCoordinates();
        return new Vector3d(values[0] - arr[0], values[1] - arr[1], values[2] - arr[2]);
    }
    
    @Override
    public Vector3d toUnitVector() {
        double fac = 1.0 / this.magnitude();
        
        return this.scale(fac);
    }
    
}
