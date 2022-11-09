package br.ufal.aracomp.cosmos.emprestimoconectorrmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.ufal.aracomp.cosmos.emprestimo.impl.ComponentFactory;
import br.ufal.aracomp.cosmos.emprestimo.spec.dt.UsuarioDT;
import br.ufal.aracomp.cosmos.emprestimo.spec.prov.IEmprestimoOps;
import br.ufal.aracomp.cosmos.emprestimo.spec.prov.IManager;
import br.ufal.aracomp.cosmos.emprestimoconectorrmi.EmprestimoConector;
import br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance.EntityLoadBalance;
import br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance.ICalculaLimiteConectorEntityLoadBalance;
import br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance.ILoadBalanceStrategy;
import br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance.RoundRobinWeightedLoadBalance;
import br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop.ICalculaLimiteConector;

public class Main {

	private static final String HOST1 = "localhost";
	private static final String PORT1 = "1098";

	private static final String HOST2 = "localhost";
	private static final String PORT2 = "1099";

	private static final String SERVICE = "calculalimite";

	private static final int REQUEST_MAX = 10;
	private static final int MAX_VALUE_AMOUNT = 10000;

	public static void main(String[] args) {

		IManager managerEmprestimo = ComponentFactory.createInstance();

		try {
			ICalculaLimiteConector calculaLimiteConector = (ICalculaLimiteConector) Naming
					.lookup("rmi://" + HOST1 + ":" + PORT1 + "/" + SERVICE);

			ICalculaLimiteConector calculaLimiteConector2 = (ICalculaLimiteConector) Naming
					.lookup("rmi://" + HOST2 + ":" + PORT2 + "/" + SERVICE);

			EntityLoadBalance<ICalculaLimiteConector>[] entitiesLoadBalance = new ICalculaLimiteConectorEntityLoadBalance[] {
					new ICalculaLimiteConectorEntityLoadBalance(5, calculaLimiteConector),
					new ICalculaLimiteConectorEntityLoadBalance(2, calculaLimiteConector2) };

			ILoadBalanceStrategy<ICalculaLimiteConector> loadBalanceStrategy = new RoundRobinWeightedLoadBalance<>(
					entitiesLoadBalance);

			managerEmprestimo.setRequiredInterface("ILimiteReq", new EmprestimoConector(loadBalanceStrategy));

			for (int i = 0; i < REQUEST_MAX; ++i) {
				UsuarioDT usuarioDT = new UsuarioDT();
				usuarioDT.rendimentos = String.valueOf(Math.random() * MAX_VALUE_AMOUNT);

				IEmprestimoOps emprestimoOps = (IEmprestimoOps) managerEmprestimo
						.getProvidedInterface("IEmprestimoOps");
				System.out.println("Redimento: " + usuarioDT.rendimentos);
				System.out.println("Emprestimo: " + emprestimoOps.liberarEmprestimoAutomatico(usuarioDT));
				System.out.println();

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
