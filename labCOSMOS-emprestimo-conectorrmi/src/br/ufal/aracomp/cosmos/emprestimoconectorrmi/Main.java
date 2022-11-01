package br.ufal.aracomp.cosmos.emprestimoconectorrmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.ufal.aracomp.cosmos.emprestimo.impl.ComponentFactory;
import br.ufal.aracomp.cosmos.emprestimo.spec.dt.UsuarioDT;
import br.ufal.aracomp.cosmos.emprestimo.spec.prov.IEmprestimoOps;
import br.ufal.aracomp.cosmos.emprestimo.spec.prov.IManager;
import br.ufal.aracomp.cosmos.emprestimo.spec.req.ILimiteReq;
import br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop.ICalculaLimiteConector;

public class Main {

	private static final String HOST = "localhost";
	private static final String PORT = "1099";
	private static final String SERVICE = "calculalimite";

	public static void main(String[] args) {

		IManager managerEmprestimo = ComponentFactory.createInstance();

		try {
			ICalculaLimiteConector calculaLimiteConector = (ICalculaLimiteConector) Naming.lookup("rmi://" + HOST + ":" + PORT + "/" + SERVICE);

			managerEmprestimo.setRequiredInterface("ILimiteReq", new EmprestimoConector(calculaLimiteConector));

			IEmprestimoOps emprestimoOps = (IEmprestimoOps) managerEmprestimo.getProvidedInterface("IEmprestimoOps");
			UsuarioDT usuarioDT = new UsuarioDT();
			usuarioDT.rendimentos = "1001";

			System.out.println("Redimento: " + usuarioDT.rendimentos);
			System.out.println("Emprestimo: " + emprestimoOps.liberarEmprestimoAutomatico(usuarioDT));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
