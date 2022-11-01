package br.ufal.aracomp.cosmos.emprestimoconectorrmi;

import java.rmi.RemoteException;

import br.ufal.aracomp.cosmos.emprestimo.spec.dt.UsuarioDT;
import br.ufal.aracomp.cosmos.emprestimo.spec.req.ILimiteReq;
import br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop.ICalculaLimiteConector;
import br.ufal.aracomp.cosmos.limiteconectorrmi.spec.dt.ClientDTConector;

public class EmprestimoConector implements ILimiteReq {

	private final ICalculaLimiteConector calculaLimiteConector;

	public EmprestimoConector(ICalculaLimiteConector calculaLimiteConector) throws RemoteException {
		super();
		this.calculaLimiteConector = calculaLimiteConector;
	}

	@Override
	public double estimarLimite(UsuarioDT usuario) {
		ClientDTConector clientDTConector = new ClientDTConector();
		clientDTConector.salario = Double.parseDouble(usuario.rendimentos);

		try {
			return calculaLimiteConector.calculaLimite(clientDTConector);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

}
