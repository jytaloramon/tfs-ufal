package br.ufal.aracomp.cosmos.emprestimoconectorrmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.ufal.aracomp.cosmos.emprestimo.impl.ComponentFactory;
import br.ufal.aracomp.cosmos.emprestimo.spec.dt.UsuarioDT;
import br.ufal.aracomp.cosmos.emprestimo.spec.prov.IEmprestimoOps;
import br.ufal.aracomp.cosmos.emprestimo.spec.prov.IManager;	
import br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance.CalculaLimiteEntityLoadBalance;
import br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance.ManagerCalculaLimiteConector;
import br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop.ICalculaLimiteConector;

public class Main {

	private static final String HOST1 = "localhost";
	private static final String PORT1 = "1096";

	private static final String HOST2 = "localhost";
	private static final String PORT2 = "1097";

	private static final String SERVICE = "calculalimite";

	public static void main(String[] args) {

		IManager managerEmprestimo = ComponentFactory.createInstance();

		try {
			ICalculaLimiteConector calculaLimiteConector = (ICalculaLimiteConector) Naming
					.lookup("rmi://" + HOST1 + ":" + PORT1 + "/" + SERVICE);

			ICalculaLimiteConector calculaLimiteConecto2 = (ICalculaLimiteConector) Naming
					.lookup("rmi://" + HOST2 + ":" + PORT2 + "/" + SERVICE);

			CalculaLimiteEntityLoadBalance[] calculaLimiteEntityLoadBalances = new CalculaLimiteEntityLoadBalance[] {
					new CalculaLimiteEntityLoadBalance(5, calculaLimiteConector),
					new CalculaLimiteEntityLoadBalance(2, calculaLimiteConecto2) };

			managerEmprestimo.setRequiredInterface("ILimiteReq",
					new EmprestimoConector(new ManagerCalculaLimiteConector(calculaLimiteEntityLoadBalances)));

			int loopMax = 8;

			for (int i = 0; i < loopMax; ++i) {
				UsuarioDT usuarioDT = new UsuarioDT();
				usuarioDT.rendimentos = String.valueOf(Math.random() * 10000);

				IEmprestimoOps emprestimoOps = (IEmprestimoOps) managerEmprestimo
						.getProvidedInterface("IEmprestimoOps");
				System.out.println("Redimento: " + usuarioDT.rendimentos);
				System.out.println("Emprestimo: " + emprestimoOps.liberarEmprestimoAutomatico(usuarioDT));

				Thread.sleep(1000);
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
