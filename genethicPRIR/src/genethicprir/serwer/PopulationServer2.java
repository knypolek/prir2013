/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir.serwer;


import genethicprir.PopulationServerInterface;

import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

/**
 *
 * @author adrian
 */
public class PopulationServer2  extends java.rmi.server.UnicastRemoteObject
                         implements PopulationServerInterface
{
  Registry reg;  // rejestr nazw obiektów 
  
  public PopulationServer2() throws RemoteException
  {
    try
    {
      // Utworzenie rejestru nazw
      reg = LocateRegistry.getRegistry(1099);
      // związanie z obiektem nazwy
      reg.rebind("PopulationServer2", this);
    }
    catch(RemoteException e)
    {
     e.printStackTrace();
      throw e;
    }
  }
  public void count(int x){
      System.out.println(x);
      
  }
 
  public Population getNextPopulation( Population oldPopulation ) throws RemoteException{
        
                Controller controller = new Controller();
                oldPopulation = controller.selection(oldPopulation);
                oldPopulation = controller.crossingOver(oldPopulation);
                controller.mutation(oldPopulation);
                return oldPopulation;
  }
 
  public static void main(String args[])
  {
    // utworzenie obiektu dostępnego zdalnie
    try
    {
      PopulationServer2 s = new PopulationServer2();
    }
    catch (Exception e)
    {
       e.printStackTrace();
       System.exit(1);
    }
  }

    @Override
    public ReadableIndividual createFirstPopulation(int populationSize) throws RemoteException {
        //TODO createFirstPopulation method
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReadableIndividual createNextPopulation(ReadableIndividual best) throws RemoteException{
        //TODO createNextPopulation method
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
