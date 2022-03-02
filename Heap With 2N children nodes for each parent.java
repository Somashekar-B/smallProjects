
import java.util.*;

//this class is a base for every node in the heap
//here value is the data in that node and parentNode stores the address of the parent of that node
class Node{
    int value;
    Node parentNode;
    
    //constructor to intialize a node
    Node(int value, Node parentNode){
        this.value = value;
        this.parentNode = parentNode;
    }
    
}

class customHeap{
    
    //Hashmap to store the children list of each respective node 
    //key: address of the Node
    //value: list of address of all children nodes
    private HashMap<Node, ArrayList<Node>> heap = new HashMap<>();
    
    //to store the maximum number of child for a node
    private long noOfChild;
    
    //it always denotes the address of the main root node
    private Node root;

    //constructor that converts given value to power of 2 and stores is as max number of children for each Node
    customHeap(int margin){
        noOfChild = (long)Math.pow(2,margin);
    }
    
    //function to insert a new node into the heap
    //parameters: int value
    //return type: void
    public void insert(int value){
        ArrayList<Node> arr = new ArrayList<>();

        //as we are storing all the node details into a HashMap called heap 
        //so when the HashMap does not have any elements in it we consider the element to be inserted as root node
        if(heap.size()==0){
            
            //creation of node
            Node newNode = new Node(value, null);
            
            //adding newNode to HashMap            
            heap.put(newNode, arr);
            
            //assign node as root node
            root = newNode; 
        }
        
        //so when heap i.e., HashMap is not empty we have check where to insert the newNode 
        //such that it maintains the structure of complete tree even after adding it. 
        else{
            
        //we are using bfs technique to find the right position for the new node    
         Queue<Node> queue = new LinkedList<>();
         
         //initially add root to queue
         queue.add(root);
    
         //until queue is empty i.e., until all the nodes are visited the loop will Run
         //in realtime it stops when it finds the right place for the new node
         while(queue.size()!=0){
             
             //pop front element of queue to make it as parent and check whether it has max number of children 
             //if true then add all those children to the queue
             Node front = queue.remove();
             if(heap.get(front).size()==noOfChild){
                 for(int i=0; i<noOfChild; i++){
                     queue.add(heap.get(front).get(i));
                 }
             }
             
             //if false it means we can add the newNode to that parent at last of its children list 
             //and then add that newNode to the HashMap i.e, heap and heapify the whole heap 
             //heapify means that all the nodes are rearranged such that they hold the property of heap
             else{
                 Node newNode = new Node(value, front);
                 heap.get(front).add(newNode);
                 heap.put(newNode, arr);
                 heapify(newNode);
                 return;
             }
             
         }
        }
    }
    
    //function that prints all the nodes with its children by comma seperated by following BFS fashion 
    //parameters: none
    //return type: void
    public void print(){
        Queue<Node> queue = new LinkedList<>();
         queue.add(root);
         while(queue.size()!=0){
             Node front = queue.remove();
             
             System.out.print(front.value+"->");
             
                 for(int i=0; i<heap.get(front).size(); i++){
                     queue.add(heap.get(front).get(i));
                     System.out.print(heap.get(front).get(i).value+",");
                 }
                 if(heap.get(front).size()==0){
                     System.out.printf("Null");
                 }
                 System.out.println();
         }
    }
    
    
    //function that heapifies the heap contents following making heap into MAX HEAP
    //parameters: Node newNode
    //return type: void
    public void heapify(Node newNode){
        while(newNode.parentNode != null){
            if(newNode.value <= newNode.parentNode.value){
                return;
            }
            int temp = newNode.value;
            newNode.value = newNode.parentNode.value;
            newNode.parentNode.value = temp;
            newNode = newNode.parentNode;
        }
    }
    
    //function to return max value of the heap 
    //parameters: none
    //return type: int
    //since we are implementing the MAXHEAP functionality root value will be the max element of heap
    int popMax(){
        return root.value;
    }
}

//driver code
public class Main
{
	public static void main(String[] args) {
		customHeap heap = new customHeap(5);
		
		for(int i=0; i<600; i++){
		    heap.insert(i);
		}
		
		heap.print();
		System.out.println(heap);
	}
}
