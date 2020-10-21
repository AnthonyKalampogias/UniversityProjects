package BasicClasses;

import java.util.ArrayList;

public class CreateUsers {

    public static void main(String[] args)
    {

        /*

        Bugs

        //TODO: Probolh dia8eshmwn paketwn DOES not display properly
        //TODO: Remove debug souts                                                                                  ~DONE~
        //TODO: Create clinet does not work                                                                         ~DONE~
        //TODO: IssueAccountBill method does not work                                                               ~DONE~
        //TODO: fix standard jsp problem                                                                            ~DONE~
        //TODO: when you log in with wrong credentials an exception occurs                                          ~DONE~
        //TODO: plhrwmh logariasmou does not work                                                                   ~DONE~
        //TODO: confirm message that you created a seller                                                           ~DONE~
        //TODO: create new plan from admin                                                                          ~DONE~
        //TODO: sanitize create client from seller                                                                  ~DONE~
        //TODO: sanitize when seller does not chose a program name                                                  ~DONE~
        //TODO: convert "insert" methods to return true if the data is inserted correctly for handling errors       ~DONE~
        //TODO: when you go to pay the bill, if there is another bill paid, you cant pay the "new" bill             ~DONE~
        



        Features

        //TODO: delete seller from admin  (sort of yes) need to delete his plans, sold plans and everything other related to him   NOPE NOPE NOPE NOPE!

        */
        /**
         * Program flow: user (be it admin, seller or customer) logs in.
         * The input is passed to the Users.logIn method which checks if the user is in the DB, if he is it returns an arraylist where
         * the first element is the type of the user, and the second is the UserID.
         * Then, if the user is a seller and he creates a new user, the data is passed to the Customer.usrRegister method.
         * The method first creates the user, (password and usrName) so it can then retrieve the UserID which is needed
         * in order to continue.
         * After the method has the newly created UserID, it then inserts into the DB the customer.
         * After that, the method calls the Seller.CreateClientBillProgram method which takes the customer's first name, last name, the program name they
         * required and last but not least the SellerID (which is provided by the ConnectToDB.getSellerIDByUsrID).
         * After all that the method (Seller.CreateClientBillProgram) inserts into the DB and binds the customer to the program of his choice.
         * If the user is a seller and he chooses to update the program of an existing customer, the data is passed to the
         * Seller.UpdateClienBillProgram where the planID and CustomerID are retrieved and then the method updates the DB of the changes.
         * */



    }

}
