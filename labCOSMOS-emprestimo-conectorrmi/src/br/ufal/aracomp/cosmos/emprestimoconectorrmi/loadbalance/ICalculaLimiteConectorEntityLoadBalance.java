package br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance;

import br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop.ICalculaLimiteConector;

public class ICalculaLimiteConectorEntityLoadBalance extends EntityLoadBalance<ICalculaLimiteConector> {

	public ICalculaLimiteConectorEntityLoadBalance(int weight, ICalculaLimiteConector entity) {
		super(weight, entity);
	}

}
