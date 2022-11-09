package br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance;

public class RoundRobinWeightedLoadBalance<T> implements ILoadBalanceStrategy<T> {

	private final EntityLoadBalance<T>[] entities;

	private int actualIndex;

	private int actualCount;

	public RoundRobinWeightedLoadBalance(EntityLoadBalance<T>[] entities) {
		this.entities = entities;
		this.actualIndex = this.actualCount = 0;
	}

	@Override
	public T getInstance() {
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
