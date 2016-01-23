
public class HW3_testing {

   public static void main(String[] args) {
      /****************General Testing of heaps */
      DHeap heap = new DHeap(4);
      BinaryHeap Twoheap = new BinaryHeap(); 
      ThreeHeap heapThree = new ThreeHeap();
      MyPQ Myheap = new MyPQ();
       
      //heap.findMin(); //testing error case
      //heap.deleteMin(); //testing error case
   	/*System.out.println(6%5); //test relationship between parent and child node for D heaps 
      Twoheap.makeEmpty();
      Twoheap.size();     
      Twoheap.isEmpty();           
   	System.out.println(5%5);
   	System.out.println(4%5);
   	System.out.println(3%5);
   	System.out.println(2%5); */
      
      /*****************Testing timing of heaps */

      long current = System.nanoTime();     
       
      /**Binary Heap time tests********/
      
      for(int i = 20; i > 0; i--) {//adds number in descending order
    	  Twoheap.insert((double)i);        
      }
      
      for(int i = 1; i <= 20; i++) { //adds duplicate numbers in ascending order
    	  Twoheap.insert((double)i);
      }
      Twoheap.insert(20); //adds an additional max
      
      System.out.println("Time to insert Binary Tree: " + (System.nanoTime() - current));   
      current = System.nanoTime();     
      while(!Twoheap.isEmpty()) { //clears PQ
         Twoheap.deleteMin();
      }
      System.out.println("Time to DELETE: " + (System.nanoTime() - current));   
    
      /**Three Heap time tests********/  
      
      for(int i = 20; i > 0; i--) {//adds number in descending order
    	  heapThree.insert((double)i);        
      }
      
      for(int i = 1; i <= 20; i++) { //adds duplicate numbers in ascending order
    	  heapThree.insert((double)i);
      }
      heapThree.insert(20); //adds an additional max
      
      System.out.println("Time to insert Three Heap: " + (System.nanoTime() - current));   
      current = System.nanoTime();     
      while(!heapThree.isEmpty()) { //clears PQ
         heapThree.deleteMin();
      }
      System.out.println("Time to DELETE: " + (System.nanoTime() - current));   
      
      /**My Heap time tests********/  
      
      for(int i = 20; i > 0; i--) {//adds number in descending order
    	  Myheap.insert((double)i);        
      }
      
      for(int i = 1; i <= 20; i++) { //adds duplicate numbers in ascending order
    	  Myheap.insert((double)i);
      }
      Myheap.insert(20); //adds an additional max
      
      System.out.println("Time to insert MyPQ: " + (System.nanoTime() - current));   
      current = current = System.nanoTime();    
      while(!Myheap.isEmpty()) { //clears PQ
         Myheap.deleteMin();
      }
      System.out.println("Time to DELETE: " + (System.nanoTime() - current));   
      
      /**D Heap time tests********/  
      
      for(int i = 20; i > 0; i--) {//adds number in descending order
    	  heap.insert((double)i);        
      }
      
      for(int i = 1; i <= 20; i++) { //adds duplicate numbers in ascending order
    	  heap.insert((double)i);
      }
      heap.insert(20); //adds an additional max
      
      System.out.println("Time to insert DHeap: " + (System.nanoTime() - current));   
      current = System.nanoTime();     
      while(!heap.isEmpty()) { //clears PQ
         heap.deleteMin();
      }
      System.out.println("Time to DELETE: " + (System.nanoTime() - current));   
      

   
   
   
   }
}
