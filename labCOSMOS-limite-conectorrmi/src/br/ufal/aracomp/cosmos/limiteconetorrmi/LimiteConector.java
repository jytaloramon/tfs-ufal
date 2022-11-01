package br.ufal.aracomp.cosmos.limiteconetorrmi;

import java.rmi.server.RemoteObject;

import br.ufal.aracomp.cosmos.limiteconetorrmi.limiteop.ICalculaLimiteConector;
import br.ufal.aracomp.cosmos.limiteconetorrmi.spec.dt.ClientDTConector;
import br.ufal.aracomp.cosmos.limite.spec.dt.ClienteDT;
import br.ufal.aracomp.cosmos.limite.spec.prov.ILimiteOps;
import br.ufal.aracomp.cosmos.limite.spec.prov.IManager;

public class LimiteConector extends RemoteObject implements ICalculaLimiteConector {

	private static final long serialVersionUID = -7776944915479717551L;

	private final IManager manager;

	public LimiteConector(IManager manager) {
		this.manager = manager;
	}

	@Override
	public double calculaLimite(ClientDTConector clientDTConector) {
		ILimiteOps limiteOps = (ILimiteOps) manager.getProvidedInterface("ILimiteOps");
		
		ClienteDT clienteDT = new ClienteDT();
		clienteDT.salario = clientDTConector.salario;
		
		return limiteOps.calcularLimite(clienteDT);
	}

}
