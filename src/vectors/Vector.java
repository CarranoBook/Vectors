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
public class Vector {
    double[] values;
    
    
    public Vector() {
        this.values = null;
    }
    /**
     * Instantiates an n-dimensional Vector in Euclidean space
     * @param values each member of the array represents a coordinate of the vector
     */
    public Vector(double[] values) {
        this.values = values;
    }
    
    public double[] toArray() {
        return this.getCoordinates();
    }
   
    
    public String toString() {
        String result = "{";
        for ( int i = 0; i < values.length-1; i++ )
            result += values[i] + ", ";
        
        result += values[values.length - 1] + "}";
        return result;
    }
    
    /**
     * Returns the number of dimensions the vector exists in
     * @return 
     */
    public int dimensions() {
        return values.length;
    }
    
    /**
     * This method returns the magnitude or length of a vector
     * @return 
     */
    public double magnitude() {
        double val = 0;
        for (int i = 0; i < values.length; i++ ) {
            val += values[i] * values[i];
        }
        return Math.sqrt(val);
    }
    
    /**
     * This method returns the coordinates of the vector
     * @return the coordinates of the vector
     */
    public double[] getCoordinates() {
        return values;
    }
    
    /**
     * This method returns the index+1 dimensional coordinate of the vector
     * @param index the dimension-1 coordinate to get
     * @return the index+1 dimensional coordinate of the vector
     */
    public double getCoordinate(int index) {
        return values[index];
    }
    
    /**
     * This method adds two vectors using standard vector addition and returns this
     * sum as a new vector.
     * Vector addition commutes, so order of addends does not matter
     * @param v vector to be added
     * @return the sum of two Vectors as a Vector
     */
    public Vector add(Vector v) {
        if ( this.values.length != v.dimensions() )
            throw new IllegalArgumentException("Cannot add vectors of unequal dimensions");
        
        double[] arr = new double[values.length];
        for ( int i = 0; i < values.length; i++ ) {
            arr[i] = values[i] + v.getCoordinate(i);
        }
        return new Vector(arr);
    }
    
    /**
     * This method subtracts a passed vector from the vector the method is called on.
     * Vector subtraction is anti-commutative, so order of addends does matter.
     * This method returns a new vector, and leaves both of the operands intact.
     * @param v vector to be subtracted from the method vector
     * @return this vector minus the passed vector
     */
    public Vector subtract(Vector v) {
        if ( this.values.length != v.dimensions() )
            throw new IllegalArgumentException("Cannot add vectors of unequal dimensions");
        
        double[] arr = new double[values.length];
        for ( int i = 0; i < values.length; i++ ) {
            arr[i] = values[i] - v.getCoordinate(i);
        }
        return new Vector(arr);
    }
    
    /**
     * This method decomposes the vector into an array of n vectors where n is 
     * the number of dimensions of the original vector.
     * Each of the decomposed vectors is n-dimensional, but only has a non-zero coordinate
     * for at most one dimension.
     * For example, if the coordinates of a vector V are {3, 2, -4, 0}, this method
     * decomposes V into an array of 4 vectors:
     * Index 0: {3, 0, 0, 0}
     * Index 1: {0, 2, 0, 0}
     * Index 2: {0, 0, -4, 0}
     * Index 3: {0, 0, 0, 0}
     * @return array of n-dimensional vectors - decomposition of the method vector
     */
    public Vector[] decompose() {
        Vector[] result = new Vector[values.length];
        Vector add;
        double[] arr;
        
        for ( int i = 0; i < values.length; i++ ) {
            arr = new double[values.length];
            for ( int j = 0; j < values.length; j++ ) {
                if ( j == i ) {
                    arr[j] = values[j];
                }
                else
                    arr[j] = 0;
            } //end inner for loop
            add = new Vector(arr);
            result[i] = add;
        } //end outer for loop
        return result;
    }
    
    /**
     * This method returns a new vector of magnitude 1 with the same direction as
     * the original vector
     * @return 
     */
    public Vector toUnitVector() {
        double fac = 1.0 / this.magnitude();
        double[] arr = new double[values.length];
        
        for ( int i = 0; i < values.length; i++ ) {
            arr[i] = values[i] * fac;
        }
        return new Vector(arr);
    }
    
    /**
     * Compares two vectors.  If the vectors have the same magnitude and direction, returns true
     * @param v vector to compare for equality
     * @return true if vectors have same magnitude and direction, false if they do not
     */
    public boolean equals(Vector v) {
        if ( this.values.length != v.dimensions() )
            throw new IllegalArgumentException("vectors of unequal dimensions");
        
        for ( int i = 0; i < values.length; i++ ) {
            if ( !compareDoubles(values[i], v.getCoordinate(i)) )
                return false;
        } //end for
        return true;
    }
    
    /**
     * This method checkts to see if a passed vector is equal to 0 - method vector
     * That is, if a vector is in the opposite direction with equal magnitude
     * @param v vector to compare
     * @return true if vectors have same magnitude and opposite direction.
     */
    public boolean isOpposite(Vector v) {
        if ( this.values.length != v.dimensions() )
            throw new IllegalArgumentException("vectors of unequal dimensions");
        
        if ( !compareDoubles(this.magnitude(), v.magnitude()) )
            return false;
        
        return this.isAntiParallel(v);
    }
    
    /**
     * This method determines whether two vectors are antiparallel - that is
     * if they are directionally opposite one another
     * @param v vector to compare
     * @return true if vectors are antiparallel
     */
    public boolean isAntiParallel(Vector v) {
        if ( this.values.length != v.dimensions() )
            throw new IllegalArgumentException("vectors of unequal dimensions");
        
        Vector a = this.toUnitVector();
        Vector b = v.toUnitVector();
        
        for ( int i = 0; i < this.values.length; i++ ) {
            if (  !compareDoubles(a.getCoordinate(i), b.getCoordinate(i)) )
                return false;
        } //end for
        return true;
    }
    
    /**
     * This method determines if two vectors are parallel
     * @param v vector to compare to method vector
     * @return true if passed vector is parallel to method vector
     */
    public boolean isParallel(Vector v) {
        if ( this.values.length != v.dimensions() )
            throw new IllegalArgumentException("vectors of unequal dimensions");
        
        Vector a = this.toUnitVector();
        Vector b = v.toUnitVector();
        
        return a.equals(b);
    }
    
    /**
     * This method returns a vector that is a scalar multiple of the vector the method
     * is called on.  For example, if the original vector has magnitude of 3 and
     * the value passed to the method is 5, this method returns a vector
     * of magnitude 15 and the same direction as the original vector
     * @param s scalar multiple
     * @return vector with same direction but s times original magnitude
     */
    public Vector scale(double s) {
        double[] arr = new double[values.length];
        for ( int i = 0; i < values.length; i++ )
            arr[i] = values[i] * s;
        
        return new Vector(arr);
    }
    
    
    /**
     * This method returns the dot product aka inner product of two vectors
     * Properties of dot product: Commutative, distributes over vector addition
     * and bilinear
     * Two vectors are orthogonal if their dotproduct = 0
     * @param v a vector
     * @return dot product of the calling vector and the passed vector
     */
    public double dotProduct(Vector v) {
        if ( this.values.length != v.dimensions() )
            throw new IllegalArgumentException("vectors of unequal dimensions");
        
        double dot = 0;
        for ( int i = 0; i < values.length; i++ )
            dot += this.values[i] * v.getCoordinate(i);
        
        return dot;
    }
    
    
    /**
     * dot product = ||A||*||B||*cos(theta)
     * This method returns the angle between the calling vector and the passed
     * vector
     * @param v  
     * @return the angle (in radians) between the two vectors
     */
    public double getAngleBetween(Vector v) {
        if ( this.values.length != v.dimensions() )
            throw new IllegalArgumentException("vectors of unequal dimensions");
        
        double dot = this.dotProduct(v);
        dot /= this.magnitude() * v.magnitude();
        
        return Math.acos(dot);  
    }
    
    public double getAngleBetweenDegrees(Vector v) {
        return getAngleBetween(v) * 180. / Math.PI;
    }
    
    /**
     * Compares two doubles.  If their difference is less than .00001 returns true
     */
    private boolean compareDoubles(double a, double b) {
        return Math.abs(a - b) < .00001;
    }
}
