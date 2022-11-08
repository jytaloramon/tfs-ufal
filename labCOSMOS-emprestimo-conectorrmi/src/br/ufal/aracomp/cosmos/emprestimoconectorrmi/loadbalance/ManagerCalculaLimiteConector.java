package br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance;

import br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop.ICalculaLimiteConector;

public class ManagerCalculaLimiteConector implements ILoadBalance<ICalculaLimiteConector> {

	private final EntityLoadBalance<ICalculaLimiteConector>[] entities;

	private int actualIndex;
	private int actualCount;

	public ManagerCalculaLimiteConector(CalculaLimiteEntityLoadBalance entityLoadBalance[]) {
		this.actualIndex = this.actualCount = 0;
		this.entities = entityLoadBalance;
	}

	@Override
	public ICalculaLimiteConector getInstance() {
		if (entities[actualIndex].getWeight() <= actualCount) {
			nextIndex();
			resetCount();
		}

		increaseCount();

		return entities[actualIndex].getEntity();
	}

	private void nextIndex() {
		actualIndex = (actualIndex + 1) % entities.length;
	}

	private void increaseCount() {
		actualCount += 1;
	}

	private void resetCount() {
		actualCount = 0;
	}
}
