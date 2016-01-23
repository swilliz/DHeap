/**
 * Samuel Williams
 * CSE 373 HW3
 * MyPQ
 */

/*
 * The MyPQ class creates a heap.
 * Heap properties are that the heap always returns the minimum value in the Heap.
 * DHeap can be initialized checked if it is empty, the minimum can be returned,
 * the minumum can be removed and retured, the size of the Heap can be found, new elements of
 * type double can be inserted, and the Heap can be cleared and made empty.
 */
public class MyPQ implements PriorityQueue{

   private int size;
   private double[] PQ;
   private EmptyPQException exception;
	
   /**
    * Constructs the BinaryHeap. Initializes size, PQ, and exception.
    */
   public MyPQ() {
      size = 0;
      PQ = new double[10];
      exception = new EmptyPQException();
   }
	
    /**
     * Returns true if priority queue has no elements
     * 
     * @return true if the priority queue has no elements
     */
   public boolean isEmpty() {
      return (size() == 0);
   }
    
    /**
     * Returns the number of elements in this priority queue.
     * 
     * @return the number of elements in this priority queue.
     */
   public int size() {
      return this.size;
   }
    
    /**
     * Returns the minimum element in the priority queue
     * 
     * @return the minimum element
     * @throws EmptyPQException
     *             if priority queue contains no elements
     */
   public double findMin() {
      if(isEmpty()) {
         throw new EmptyPQException("The Priority Queue is empty / findMin");
      }
      double result = PQ[1];
      for(int i = 1; i <= size; i++) {
         if(PQ[i] < result) {
            result = PQ[i];
         }
      }
      return result;
   }
    
    /**
     * Inserts a new element into the priority queue. Duplicate values ARE
     * allowed.
     * 
     * @param x
     *            element to be inserted into the priority queue.
     */
   public void insert(double x) {
      if( ( this.size + 1 ) == this.PQ.length) {
         resize();
      }
      this.size = this.size + 1;
      this.PQ[this.size] = x;
   }
    

  /**
   * Helper method to resize the array based on how full it is.
   */
   private void resize() {
   //if growing the array
      double[] temp;
      if(size() + 1 == this.PQ.length) {
         temp = new double[this.PQ.length * 2];
      } 
      //if shrinking the array
      else {          
         temp = new double[this.PQ.length / 2];
      }
      for(int i = 1; i <= this.size; i++) {
         temp[i] = this.PQ[i];
      }
      this.PQ = temp;         
   }
    /**
     * Removes and returns the minimum element from the priority queue.
     * 
     * @return the minimum element
     * @throws EmptyPQException
     *             if priority queue contains no elements
     */
   public double deleteMin() {
      if(isEmpty()) {
         throw new EmptyPQException("The Priority Queue is empty / deleteMin ");
      }
      if(size() <= (this.PQ.length / 3) ) { //if the PQ is 33% full (66% empty) resize to shrink to 50%
         resize();
      }
      double result = this.PQ[1];
      int position = 1;
      for(int i = 1; i <= size; i++) {
         if(PQ[i] < result) {
            result = PQ[i];
            position = i;
         }
      }
      for(int i = position; i < this.size; i++) {
         this.PQ[i] = this.PQ[i + 1];
      }    
      size--;
      return result;
   }

    /**
     * Resets the priority queue to appear as not containing any elements.
     */
   public void makeEmpty() {
      this.PQ = new double[10];
      this.size = 0;
   }
}
