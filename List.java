class List{

   private class Node{
      // Fields
      int data;
      Node next;
	  Node previous;
      
      // Constructor
      Node(int data) { this.data = data; next = null; previous = null; }
      
      // toString():  overrides Object's toString() method
      public String toString() { 
         return String.valueOf(data); 
      }
      
      // equals(): overrides Object's equals() method
      public boolean equals(Object x){
         boolean eq = false;
         Node that;
         if(x instanceof Node){
            that = (Node) x;
            eq = (this.data==that.data);
         }
         return eq;
      }
   }

   // Fields
   private Node front;
   private Node back;
   private Node cursor;
   private int length;
   private int index;

   // Constructor
   List() { 
      front = back = cursor = null; 
      length = 0; 
	  index = -1;
   }


   // Access Functions --------------------------------------------------------
   
   //length()
   // Returns the number of elements in this List.
   int length() {
	   return length;
   }
   
   //index()
   //If cursor is defined, returns the index of the cursor element,
   //otherwise returns -1.
   int index() {
	   if(cursor == null){
		   return -1;
	   }
	   return index;
   }
   
   // isEmpty()
   // Returns true if this List is empty, false otherwise.
   boolean isEmpty() { 
      return length == 0; 
   }

   // front() 
   // Returns front element.
   // Pre: !this.isEmpty()
   int front(){
      if( this.isEmpty() ){
         throw new RuntimeException(
            "List Error: front() called on empty List");
      }
      return front.data;
   }
   
   // back() 
   // Returns back element.
   // Pre: !this.isEmpty()
   int back(){
	   if( this.isEmpty() ){
         throw new RuntimeException(
            "List Error: back() called on empty List");
      }
      return back.data;
   }
   
   //get()
   //Returns element that cursor is currently on
   //Pre: !this.isEmpty(), cursor != null
   int get(){
	   if( this.isEmpty() ){
         throw new RuntimeException(
            "List Error: back() called on empty List");
      }
	  if( cursor == null ){
         throw new RuntimeException(
            "List Error: get() called on out of bounds cursor");
      }
      return cursor.data;
   }
   
   //equals(List L)
   //returns true if this List and L are the same integer sequence.
   //the cursor is ignored in both lists.
   /*boolean equals(List L){
	   if( this.getLength() != L.getLength() ){
		   return false;
	   }
	   if(this.getLength() == 0){
		   return true;
	   }
	   int temp1 = this.front();
	   int temp2 = L.front();
	   for( i = 1; i <= this.getLength(); ++i ){
		   if(temp1 != temp2){
			   return false;
		   }
		   temp1 = temp1.next.data;
		   temp2 = temp2.next.data;
	   }
	   return true;
   }*/

   // Manipulation Procedures -------------------------------------------------

   //clear()
   //resets this list to its original empty state.
   void clear(){
	   if(this.length() == 0){
         throw new RuntimeException(
            "List Error: clear() called on empty List");
       }
	   while(this.length() > 0){
		  this.deleteFront();
	   }
   }
   
   //moveFront()
   //If List is non-empty, places the cursor under the front element,
   //otherwise does nothing.
   void moveFront(){
	   if(isEmpty()){
		   throw new RuntimeException(
            "List Error: moveFront() called on empty List");
	   }
	   cursor = front;
	   index = 0;
   }
   
   //moveBack()
   //If List is non-empty, places the cursor under the front element,
   //otherwise does nothing.
   void moveBack(){
	   if(isEmpty()){
		   throw new RuntimeException(
            "List Error: moveBack() called on empty List");
	   }
	   cursor = back;
	   index = this.length - 1;
   }
   
   //movePrev()
   //If the cursor is defined and not at front, moves cursor one step toward
   //front of this List, if cursor is defined and at front, cursor becomes
   //undefined, if cursor is undefined, does nothing.
   void movePrev(){
	   if(cursor == null){
		   throw new RuntimeException(
            "List Error: movePrev() called on undefined cursor");
	   }
	   if(cursor == front){
		   cursor = null;
		   index = -1;
	   }
	   else{
		   cursor = cursor.previous;
		   index--;
	   }
   }
   
   //moveNext()
   //If the cursor is defined and not at back, moves cursor one step toward
   //back of this List, if cursor is defined and at back, cursor becomes
   //undefined, if cursor is undefined, does nothing.
   void moveNext(){
	   if(cursor == null){
		   throw new RuntimeException(
            "List Error: moveNext() called on undefined cursor");
	   }
	   if(cursor == back){
		   cursor = null;
		   index = -1;
	   }
	   else{
		   cursor = cursor.next;
		   index++;
	   }
   }
   
   //prepend()
   //Insert new element into this List. If list is non-empty,
   //insertion takes place before front element.
   void prepend(int data){
      Node N = new Node(data);
      if( this.isEmpty() ) { 
         front = back = N;
      }else{ 
         front.previous = N;
		 N.next = front;
         front = N;
      }
      length++;
	  if( this.index() != -1){
		  ++index;
	  }
   }
   
   //append()
   //Insert new element into this List. If list is non-empty,
   //insertion takes place after back element.
   void append(int data){
      Node N = new Node(data);
      if( this.isEmpty() ) { 
         front = back = N;
      }else{ 
         back.next = N;
	 N.previous = back;
         back = N; 
      }
      length++;
   }
   
   //insertBefore()
   //Insert new element before cursor.
   //pre: length() > 0, index >= 0
   void insertBefore(int data){
	   if(this.index() < 0){
		   throw new RuntimeException(
            "List Error: insertBefore() called on undefined cursor");
	   }
	   if(this.length() == 0){
		   throw new RuntimeException(
            "List Error: insertBefore() called on empty List");
	   }
           //if(this.index() == -1){
           //   this.append(data);
           //}
	   if(this.index() == 0){
              this.prepend(data);
	   }
	   else if(this.index() == this.length - 1){
              this.append(data);
	   }
	   else{
		   
	       Node N = new Node(data);
	       //Node temp = cursor.previous;
	       N.previous = cursor.previous;
	       N.next = cursor;
	       cursor.previous = N;
           N.previous.next = N;
	       //temp.next = N;
	       length++;
	       index++;
	   }
   }
   
   //insertAfter()
   //Insert new element before cursor.
   //pre: length() > 0, index >= 0
   void insertAfter(int data){
	   if(this.index() < 0){
		   throw new RuntimeException(
            "List Error: insertAfter() called on undefined cursor");
	   }
	   if(this.length() == 0){
		   throw new RuntimeException(
            "List Error: insertAfter() called on empty List");
	   }
	   if(this.index() == 0){
              this.prepend(data);
	   }
	   else if(this.index() == this.length - 1){
              this.append(data);
	   }
	   else{
       Node N = new Node(data);
	   Node temp = cursor.next;
	   cursor.next = N;
	   temp.previous = N;
	   N.next = temp;
	   N.previous = cursor;
	   length++;
	   }
   }

   //deleteFront()
   // Deletes the front element. Pre: length()>0
   void deleteFront(){
      if(this.length() == 0){
         throw new RuntimeException(
            "List Error: deleteFront() called on empty List");
      }
	  if(cursor == front){
		  cursor = null;
		  index = -1;
	  }
      if(this.length() > 1){
          front = front.next;
      }else{
         front = back = cursor = null;
		 index = -1;
      }
      length--;
	  if( this.index() > 0){
		  index--;
	  }
   }
   
   //deleteBack()
   // Deletes the back element. Pre: length()>0
   void deleteBack(){
	   if(this.length() == 0){
         throw new RuntimeException(
            "List Error: deleteBack() called on empty List");
      }
	  if(cursor == back){
		  cursor = null;
		  index = -1;
	  }
      if(this.length > 1){
         back = back.previous;
      }else{
         front = back = cursor = null;
		 index = -1;
      }
      length--;
   }
   
   //delete()
   //Deletes cursor element, making cursor undefined.
   //Pre: length() > 0, index >= 0
   void delete(){
	   if( this.length() <= 0){
		   throw new RuntimeException(
		      "List Error: delete() called on empty List.");
	   }
	   if( this.index() <= 0 ){
		   throw new RuntimeException(
		      "List Error: delete() called on undefined cursor.");
	   }
	   if(cursor == front){
		   deleteFront();
	   }
	   else if(cursor == back){
		   back = back.previous;
		   cursor = null;
	   }
	   else{
	   Node temp = cursor.previous;
	   cursor.next.previous = temp;
	   temp.next = cursor.next;
	   cursor = null;
	   index = -1;
	   length--;
	   }
   }


   // Other Functions ---------------------------------------------------------

   // toString()
   // Overides Object's toString() method.
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node N = front;
      while(N!=null){
         sb.append(N.toString());
		 sb.append(" ");
         N = N.next;
      }
      return new String(sb);
   }

   // equals()
   // Overrides Object's equals() method.  Returns true if x is a List storing
   // the same integer sequence as this List, false otherwise.
   public boolean equals(Object x){
      boolean eq  = false;
      List Q;
      Node N, M;

      if(x instanceof List){
         Q = (List)x;
         N = this.front;
         M = Q.front;
         eq = (this.length==Q.length);
         while( eq && N!=null ){
            eq = N.equals(M);
            N = N.next;
            M = M.next;
         }
      }
      return eq;
   }

   // copy()
   // Returns a new List identical to this List.
   List copy(){
      List Q = new List();
      Node N = this.front;

      while( N!=null ){
         Q.append(N.data);
         N = N.next;
      }
      return Q;
   }
   
   //concat()
   //Returns a new List which is the concatenation of this List
   //followed by L. The cursor in the new List is undefined,
   //regardless of the states of the cursors in this List and L.
   //The states of this List and L are unchanged
   List concat(List L){
	   if( this.isEmpty() ){
         return L.copy();
      }
	  if( L.isEmpty() ){
         return this.copy();
      }
	  //this.back.next = L.front;
	  //L.front.previous = this.back;
	  
	  List comb = this.copy();
	  Node N = L.front;
	  while( L!=null ){
         comb.append(N.data);
         N = N.next;
      }
	  return comb;
   }
   
}
