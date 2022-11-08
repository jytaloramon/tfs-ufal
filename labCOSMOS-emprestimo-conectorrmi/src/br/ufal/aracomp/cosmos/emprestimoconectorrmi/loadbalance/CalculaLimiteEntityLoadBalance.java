package br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance;

import br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop.ICalculaLimiteConector;

public class CalculaLimiteEntityLoadBalance extends EntityLoadBalance<ICalculaLimiteConector> {

	public CalculaLimiteEntityLoadBalance(int weight, ICalculaLimiteConector entity) {
		super(weight, entity);
		// TODO Auto-generated constructor stub
	}

}
