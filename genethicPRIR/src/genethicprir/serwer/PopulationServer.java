/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genethicprir.serwer;


import genethicprir.PopulationServerInterface;
import genethicprir.ReadableIndividual;

import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

/**
 *
 * @author adrian
 */
public class PopulationServer  extends java.rmi.server.UnicastRemoteObject
                         implements genethicprir.PopulationServerInterface
{
  Registry reg;  // rejestr nazw obiektów 
  Population population;
  
  public PopulationServer() throws RemoteException
  {
    try
    {
      // Utworzenie rejestru nazw
      reg = LocateRegistry.createRegistry(1099);
      // związanie z obiektem nazwy
      reg.rebind("PopulationServer", this);
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
      PopulationServer s = new PopulationServer();
    }
    catch (Exception e)
    {
       e.printStackTrace();
       System.exit(1);
    }
  }

    @Override
    public ReadableIndividual createFirstPopulation(int populationSize) throws RemoteException {
        this.population = new Population(populationSize);
        ReadableIndividual ri = population.getBestIndividual().castToReadable();
        return ri;
    }

    @Override
    public ReadableIndividual createNextPopulation(ReadableIndividual best) throws RemoteException{
        Controller controller = new Controller();
        population.getRandomIndividual();
        population.addIndividual(new Individual(best));
        population = controller.selection(population);
        population = controller.crossingOver(population);
        controller.mutation(population);
        return population.getBestIndividual().castToReadable();
    }

}
