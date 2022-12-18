
/**
 * A convex polygon within the plane constructed by getting the vertices . 
 *
 * @Rei Bashari , 315522623 .
 * @08-01-2021 .
 */
public class Polygon
{ //start of the class polygon . 
   private Point [] _vertices ; //private veriable
   int _noOfVertices ; //private variable
   private final int MAX_VERTICES = 10 ; //final value
   
   /**
     * Constructor for Initializing the array of the class polygon . 
     */
   public Polygon() { 
       _vertices = new Point [MAX_VERTICES] ; 
       
       for(int i=0 ; i<MAX_VERTICES ; i++ ) 
            {_vertices[i] = null ;
             _noOfVertices = 0 ; }
   }
   
   /**
    * method that add a new vertex to the polygon .
    * @param x = add the x of the new vertex to the polygon . 
    * @param y = add the y of the new vertex to the polygon . 
    * @return true if there place to the new vertex , and return false if not . 
    */
   public boolean addVertex(double x , double y) {
       if (_noOfVertices < MAX_VERTICES) //check if there place to adding vertex . 
            { _vertices [_noOfVertices++ ] = new Point(x,y) ;
              return true ; 
            }
       return false ; 
   }
   
   /**
    * method return the highest vertex in polygon if there any vertex(if there
    *  is no vertex return "null") .
    *  @return highest vertex . 
    */
   public Point highestVertex() {
       if (_noOfVertices == 0 ) 
            return null ; 
       else {
           Point highest = _vertices[0]; 
           for (int i = 1; i<_noOfVertices ; i++) //array that looking for the highest vertex . 
                if(_vertices[i].isAbove(highest))
                    highest = _vertices[i] ;
           return new Point (highest) ; 
       }
   }
   
   /**
    * method that return string of the number of the vertices in this format :
    * ((x,y),(x,y) ...)
    * @return string of the polygon vertices . 
   */ 
   public String toString () {
       if (_noOfVertices != 0 )    
       {String sum = " " ; 
       sum = "(" + _vertices[0].toString() ;
       for (int i = 1; i < _noOfVertices ; i++ ) 
       {
            sum += "," + _vertices[i].toString() ; 
        }
       
       return "The polygon has " + _noOfVertices + " vertices:\n" + sum + ")" ; 
    }
    else 
        return "The polygon has " + _noOfVertices + " vertices. " ;
    }
   
   /**
    * method calculate the perimeter of the polygon . 
    * @return the perimeter of the polygon . 
    */
   public double calcPerimeter() {
       if (_noOfVertices <= 1) //in case we have just 1 vertex or 0 .
           return 0 ;
       if (_noOfVertices == 2)  //in case we have just 2 vertex . 
           return _vertices[0].distance(_vertices[1]) ; 
       else  {
           double perimeter = 0 ;
           for (int j = 0 ; j< _noOfVertices-1  ; j++) 
           {
              perimeter += _vertices[j].distance(_vertices[j+1]) ; 
            }
           perimeter += _vertices[0].distance(_vertices[_noOfVertices-1]) ;
           return perimeter ; 
        }
    }
   
   /** 
    * method calculate the area of the polygon . 
    * @return the area of the polygon . 
    */
   public double calcArea() { 
       if (_noOfVertices < 3) //in case we have just 0,1,2 vertex so we couldnt calculate the area.
            return 0 ; 
       else {
           double area = 0 ;
           double halfArea = 0 ;
           double sideA = 0 ; 
           double sideB = 0 ; 
           double sideC = 0 ;
           for (int j=1 ; j< _noOfVertices-1 ; j++) { 
           sideA = _vertices[0].distance(_vertices[j]) ;
           sideB = _vertices[0].distance(_vertices[j+1]) ; 
           sideC = _vertices[j].distance(_vertices[j+1]) ; 
           halfArea = (sideA + sideB + sideC)/2 ; //half area .
           //the formula of the polygon area . 
           area += Math.sqrt(halfArea *(halfArea - sideA)*(halfArea - sideB)*(halfArea - sideC)) ; 
        }
        return area ; 
       }   
    
    }
   
   /**
    * method check if this polygon is bigger then the other .
    * @param other - other polygon .
    * return true if this bigger then the other , otherwise return false . 
    */
   public boolean isBigger(Polygon other) {
       if (this.calcArea() > other.calcArea() )
            return true ; 
       else 
            return false ; 
    }
   
   /**
    * method check if the vertex "p" is located on the polygon . 
    * @param p - the vertex we checked . 
    * @return the index of point "p" (if p on the polygon) and if p isnt - return -1. 
    */
   public int findVertex(Point p) { 
       for (int j = 0 ; j< _noOfVertices ; j++) 
           {
               if (_vertices[j].equals(p))   
                return j ;
           }
       return -1 ; 
    }
   
    /** method check if the point p located on the polygon , in this case return the
    * next vertex .
    * @param p - the vertex we checked . 
    * @return the next vertex , and if p isnt of the polygon return "null"
    */ 
   public Point getNextVertex(Point p) {
       for (int j = 0 ; j < _noOfVertices ; j++)
            {
                if (_vertices[j].equals(p) && j !=_noOfVertices )
                {
                    return new Point(_vertices[j+1]) ; 
                }
                else if (_vertices[j].equals(p) && j == _noOfVertices ) 
                {
                    return new Point(_vertices[0]) ; 
                }
            }
            return null ; 
    }
  
  
   /**
    * method return the rectangle that blocking the polygon . 
    * @return the rectangle as a polygon . 
   */ 
   public Polygon getBoundingBox() {
       if (_noOfVertices > 3 ) 
       {
           double maxX = 0 ; 
           double maxY = 0 ; 
           double minX = _vertices[0].getX() ; 
           double minY = _vertices[0].getY() ; 
           Polygon box = new Polygon() ; 
           
           for (int j = 1 ; j< _noOfVertices ; j++) 
           {
               if (_vertices[j].getY() < minY)
               {
                   minY = _vertices[j].getY() ; 
               }
               if (_vertices[j].getY() > maxY)
               {
                   maxY = _vertices[j].getY() ; 
               }
               if (_vertices[j].getX() > maxY)
               {
                   maxX = _vertices[j].getX() ; 
               }
               if (_vertices[j].getX() < minX) 
               {
                   minX = _vertices[j].getX() ; 
               }
            }
           
           box.addVertex (minX , minY) ; 
           box.addVertex (maxX , minY) ; 
           box.addVertex (maxX , maxY) ;
           box.addVertex (minX , maxY) ; 
           
           return box ; 
        }
        
        return null ; 
    }
    
    }// end of class polygon 
            
  
