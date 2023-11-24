

public class LoanOfficer
{
   private int officerID;
   public Customer currentCustomer;

   //**************************************************
   //Constructor to initialize member variables
   //Initially no patient is assigned
   public LoanOfficer(int id)
   {
      this.officerID = id;
      currentCustomer = null;
   }

   //******************************************
   //an accessor method for the officer's ID
   public int getID()
   {
      return officerID;
   }

   //****************************************************************
   //method to determine if a loanOfficer currently has a customer by
   //checking the currentCustomer variable
   public boolean hasCustomer()
   {
      if(currentCustomer == null){
         return false;
      }
      else{
         return true;
      }
   }

   //************************************************************************
   //assign customer1 to currentCustomer if the officer does not have customer
   public boolean assignCustomer(Customer customer1)
   {
      if(currentCustomer == null){
         currentCustomer = customer1;
         return true;
      }
      else{
         return false;
      }
   }

   //*********************************************
   public Customer handleCustomer()
   {
      if(currentCustomer == null){
         return null;
      }
      else{
         Customer x = new Customer(currentCustomer.getCustID(), currentCustomer.getCategory());
         currentCustomer = null;
         return x;
      }
   }

   //********************************************
   //toString method returns a string containing
   //the information of a loanOfficer
   public String toString()
   {
      String result = "\nOfficer ID: " + officerID;

      if (currentCustomer == null)
         result += " does not have any cutomers\n";
      else
         result += " is serving customer with id " + currentCustomer.getCustID() + "\n";

      return result;
   }
}
