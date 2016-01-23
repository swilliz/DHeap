/**
 * Samuel Williams
 * CSE 373 HW3
 * DHeap
 */

/*
 * The DHeap class creates a heap with "D" nodes per parent with D being user specified.
 * Heap properties are that the heap always returns the minimum value in the Heap.
 * DHeap can be initialized with a user specified branching factor,
 * checked if it is empty, the minimum can be returned,
 * the minumum can be removed and retured, the size of the Heap can be found, new elements of
 * type double can be inserted, and the Heap can be cleared and made empty.
 */
public class DHeap implements PriorityQueue {

   private int size; //number of elements in the heap
   private double[] PQ; //array which holds elements in the heap
   private EmptyPQException exception; 
   private int BRANCHING_FACTOR; //user specified branching factor

  /**
   * Constructs the DHeap. Initializes size, PQ, branching factor, and exception.
   */
   public DHeap(int branchingFactor) {
      size = 0;
      PQ = new double[10];
      exception = new EmptyPQException();
      BRANCHING_FACTOR = branchingFactor;
   }

  /**
   * Constructs the DHeap as a binary heap. Initializes size, PQ, branching factor, and exception.
   */      
   public DHeap() {
      this(2); //if unspecified makes a BinaryHeap
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
      return this.PQ[1];
   }
 
  /**
   * Inserts a new element into the priority queue. Duplicate values ARE
   * allowed.
   * 
   * @param x
   *            element to be inserted into the priority queue.
   */
   public void insert(double x) {
      if( (this.size + 1) == this.PQ.length) {
         resize();
      }
      this.size = this.size + 1;
      this.PQ[this.size] = x;
      percolateUp();
   }
  
  /**
   * Helper method which rearanged the Heap maintains heap properties after an insertion.
   */
   private void percolateUp() {
      boolean reordered = false;
      int start = size();
      while( !reordered && start > 1) {
         int parent = start / BRANCHING_FACTOR;
         if(start % BRANCHING_FACTOR > 1) { //adjusts to find the correct parent index
            parent++;
         }
         double temp = this.PQ[parent]; //parent node value
         if(temp > this.PQ[start]) { //if parent is a larger double than child rearange 
            this.PQ[parent] = this.PQ[start];
            this.PQ[start] = temp;
            start = parent;
         } 
         else {
            reordered = true;
         }
      }
   }
 
  /**
   * Helper method to resize the heap based on how full it is.
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
      if(size() <= (this.PQ.length / 3) ) { //if the PQ is 33% full (66% empty) resize to shrink
         resize();
      }
      double result =  this.PQ[1];
      this.PQ[1] = this.PQ[size()]; //set the first node's value as the last node
      size--; //eliminate last node
      percolateDown();
      return result;
   }
 
  /**
   * Private Helper method to rearange the Heap to maintain heap properties after a deletion.
   */
   private void percolateDown() {
      boolean reordered = false;
      int start = 1;
   //start only greater than or equal size if we have reached a leaf or passed the size.
      while( !reordered && start <= size ) { 
         double min = this.PQ[start];
         int indexOfSmallest = start;
         int farthestChild = (start * BRANCHING_FACTOR) + 1; //farthest possible child's index
      //stores as index the index in the array of the smallest child if the
      //child is smaller than the parent        
         for(int i = 0; i < BRANCHING_FACTOR; i++) {
         //if farthest child is in the range of size and value here is less than min
            if( (farthestChild - i <= this.size) && this.PQ[farthestChild - i] < min) { 
               indexOfSmallest = farthestChild - i;
               min = this.PQ[indexOfSmallest];
            }
         }  
      //if the smallest index is the parent
         if(indexOfSmallest == start) {
            reordered = true;
         } 
         //else the smallest value is not the parent
         else {
            this.PQ[indexOfSmallest] = this.PQ[start];
            this.PQ[start] = min;
            start = indexOfSmallest;
         }              
      } 
   }

  /**
   * Resets the priority queue to appear as not containing any elements.
   */
   public void makeEmpty() {
      this.PQ = new double[10];
      this.size = 0;
   }
}
