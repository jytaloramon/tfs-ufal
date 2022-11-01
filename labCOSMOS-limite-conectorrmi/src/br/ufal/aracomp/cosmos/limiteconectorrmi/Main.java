package br.ufal.aracomp.cosmos.limiteconectorrmi;

import br.ufal.aracomp.cosmos.limite.spec.prov.IManager;
import br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop.ICalculaLimiteConector;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ufal.aracomp.cosmos.limite.impl.ComponentFactory;

public class Main {

	private static final String HOST = "localhost";
	private static final int PORT = 1099;

	public static void main(String[] args) {

		IManager manageLimite = ComponentFactory.createInstance();

		try {
			ICalculaLimiteConector calculaLimiteConector = new LimiteConector(manageLimite);
			
			Registry registry = LocateRegistry.createRegistry(PORT);
			registry.bind("calculalimite", calculaLimiteConector);
			
			System.out.println("Server UP");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
