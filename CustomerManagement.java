
import java.util.LinkedList;

public class CustomerManagement
{
   private LinkedList<Customer> LEQueue;
   private LinkedList<Customer> MEQueue;
   private LinkedList<Customer> SEQueue;

   private LinkedList<Customer> checkoutQueue;

   private LoanOfficer[] officerList;

   //**********************************************
   //Constructor
   public CustomerManagement(int numOfLoanOfficers)
   {
      LEQueue = new LinkedList<Customer>();
      MEQueue = new LinkedList<Customer>();
      SEQueue = new LinkedList<Customer>();
      checkoutQueue = new LinkedList<Customer>();

      //creating LoanOfficer objects
      officerList = new LoanOfficer[numOfLoanOfficers];
      for (int i=0; i< officerList.length; i++)
      {
         officerList[i] = new LoanOfficer(i);
      }
   }

   //*******************************************************************
   //add a customer to the corresponding queue based on its category
   //return true if added to a queue successfully; otherwise return false
   boolean addCustomer(int id, String category)
   {
      if(category.equals("LE") == true){
         LEQueue.add(new Customer(id, category));
         return true;
      }
      else
      if(category.equals("ME") == true){
         MEQueue.add(new Customer(id, category));
         return true;
      }
      else
      if(category.equals("SE") == true){
         SEQueue.add(new Customer(id, category));
         return true;
      }
      else{
         return false;
      }
   }

   //*******************************************************************
   //assign a customer to a loan officer with large enterprise (LE) queues
   //going first; return null if there are no customers in the queues or if
   //there are no loan officer are available
   Customer assignCustomerToLoanOfficer()
   {
      boolean ok = false;
      int ind = 0;
      for(int i = 0; i < officerList.length; i++){
         if(officerList[i].hasCustomer() == false){
            ok = true;
            ind = i;
            break;
         }
      }
      if((LEQueue.isEmpty() == true && MEQueue.isEmpty() == true && SEQueue.isEmpty() == true) || ok == false){
         return null;
      }
      else{
         Customer x = new Customer(0, "");
         if(LEQueue.isEmpty() == false){
            x = new Customer(LEQueue.getFirst().getCustID(),LEQueue.getFirst().getCategory());
            LEQueue.removeFirst();
         }
         else
         if(MEQueue.isEmpty() == false){
            x = new Customer(MEQueue.getFirst().getCustID(),MEQueue.getFirst().getCategory());
            MEQueue.removeFirst();
         }
         else
         if(SEQueue.isEmpty() == false){
            x = new Customer(SEQueue.getFirst().getCustID(),SEQueue.getFirst().getCategory());
            SEQueue.removeFirst();
         }
         officerList[ind].assignCustomer(x);
         return x;
      }
      
   }

   //***************************************************************
   //check if officer with the officerID has a customer, and release
   //that customer if they do. Then add that customer to checkout queue
   //and return the Customer object; otherwise return null
   Customer releaseCustomerFromOfficer(int officerID)
   {
      if((officerID < 0 && officerID >= officerList.length) || (officerList[officerID].hasCustomer() == false)){
         return null;
      }
      else{
         checkoutQueue.add(officerList[officerID].currentCustomer);
         officerList[officerID].handleCustomer();
         return checkoutQueue.getLast();
      }
   }

   //**************************************************************
   //remove the first Customer from the checkoutQueue and return it;
   //otherwise the method return null.
   public Customer checkoutCustomer()
   {
      if(checkoutQueue.isEmpty() == true){
         return null;
      }
      Customer x = new Customer(checkoutQueue.getFirst().getCustID(),checkoutQueue.getFirst().getCategory());
      checkoutQueue.removeFirst();
      return x;
   }

   //************************************************
   //The printQueue prints out the content
   //of the queues and the array of loan officers
   public void printQueues()
   {
      System.out.print("\nLarge Enterprise Queue:\n" + LEQueue.toString() + "\n");
      System.out.print("\nMedium Enterprise Queue:\n" + MEQueue.toString() + "\n");
      System.out.print("\nSmall Enterprise Queue:\n" + SEQueue.toString() + "\n\n");
      for (int i = 0; i < officerList.length; i++)
      {
         System.out.print(officerList[i].toString());
      }
      System.out.print("\nCheckout Customer Queue:\n" + checkoutQueue.toString() + "\n\n");
   }
}
