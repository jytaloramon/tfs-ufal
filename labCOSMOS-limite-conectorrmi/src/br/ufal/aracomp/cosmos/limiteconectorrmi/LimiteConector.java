package br.ufal.aracomp.cosmos.limiteconectorrmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.ufal.aracomp.cosmos.limite.spec.dt.ClienteDT;
import br.ufal.aracomp.cosmos.limite.spec.prov.ILimiteOps;
import br.ufal.aracomp.cosmos.limite.spec.prov.IManager;
import br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop.ICalculaLimiteConector;
import br.ufal.aracomp.cosmos.limiteconectorrmi.spec.dt.ClientDTConector;

public class LimiteConector extends UnicastRemoteObject implements ICalculaLimiteConector {

	private static final long serialVersionUID = -7776944915479717551L;

	private final IManager manager;

	private int countRequests = 1;

	public LimiteConector(IManager manager) throws RemoteException {
		super();
		this.manager = manager;
	}

	@Override
	public double calculaLimite(ClientDTConector clientDTConector) {
		ILimiteOps limiteOps = (ILimiteOps) manager.getProvidedInterface("ILimiteOps");

		ClienteDT clienteDT = new ClienteDT();
		clienteDT.salario = clientDTConector.salario;

		System.out.println("-> (" + countRequests + ") Requisição (Calcula limite): " + clienteDT.salario);
		countRequests++;

		return limiteOps.calcularLimite(clienteDT);
	}

}
