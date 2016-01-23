/**
 * Samuel Williams
 * CSE 373 HW3
 * BinaryHeap
 */

/*
 * The Binary class creates a heap with "2" nodes per parent.
 * Heap properties are that the heap always returns the minimum value in the Heap.
 * Binary Heap can be initialized,
 * checked if it is empty, the minimum can be returned,
 * the minumum can be removed and retured, the size of the Heap can be found, new elements of
 * type double can be inserted, and the Heap can be cleared and made empty.
 */
public class BinaryHeap implements PriorityQueue{

   private int size;
   private double[] PQ;
   private EmptyPQException exception;

  /**
   * Constructs the BinaryHeap. Initializes size, PQ, and exception.
   */
   public BinaryHeap() {
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
      if(( this.size + 1 ) == this.PQ.length) {
         resize();
      }
      this.size = this.size + 1;
      this.PQ[this.size] = x;
      percolateUp();
   }
   
  /**
   * Helper method which rearanged the Heap as to maintain heap properties after an insertion.
   */    
   private void percolateUp() {
      boolean reordered = false;
      int start = size();
      while( !reordered && start > 1) {
         double temp = this.PQ[start / 2]; //parent
         if(temp > this.PQ[start]) { //if parent is a larger double than child
            this.PQ[start / 2] = this.PQ[start];
            this.PQ[start] = temp;
            start = start / 2;
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
      if(size() <= (this.PQ.length / 3)) { //if the PQ is 33% full (66% empty) resize to shrink
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
   //start * 2 only greater than size if we have passed the last leaf
      while( !reordered && start * 2 <= size ) { 
         int left = start * 2;
         int right = left + 1;
         double temp = this.PQ[start]; // the top node
      //if right exists, is smaller than or equal to left and right is smaller than temp
         if(right <= size && this.PQ[right] >= temp && this.PQ[left] >= temp) {
            reordered = true;
         } 
         else {
            int position = left; //left or right index
            if(right <= size && this.PQ[right] < this.PQ[left] && this.PQ[right] < temp) {
               position = right;
            } 
            this.PQ[position / 2] = this.PQ[position];
            this.PQ[position] = temp;
            start = position;
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
