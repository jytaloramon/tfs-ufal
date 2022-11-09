package br.ufal.aracomp.cosmos.limiteconectorrmiserver2;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ufal.aracomp.cosmos.limite.impl.ComponentFactory;
import br.ufal.aracomp.cosmos.limite.spec.prov.IManager;
import br.ufal.aracomp.cosmos.limiteconectorrmi.LimiteConector;
import br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop.ICalculaLimiteConector;

public class Main {
	
	private static final String NAME = "SERVER 2";
	private static final String HOST = "localhost";
	private static final int PORT = 1099;

	public static void main(String[] args) {

		IManager manageLimite = ComponentFactory.createInstance();

		try {
			ICalculaLimiteConector calculaLimiteConector = new LimiteConector(manageLimite);

			Registry registry = LocateRegistry.createRegistry(PORT);
			registry.bind("calculalimite", calculaLimiteConector);

			System.out.println("Server -> " + NAME + " <- UP: (" + HOST + ":" + PORT + ")");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
