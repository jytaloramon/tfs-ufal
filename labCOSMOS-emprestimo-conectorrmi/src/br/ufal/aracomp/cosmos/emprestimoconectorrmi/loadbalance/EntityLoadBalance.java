package br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance;

public abstract class EntityLoadBalance<T> {
	
	private final int weight;
	
	private final T entity;

	public EntityLoadBalance(int weight, T entity) {
		this.weight = weight;
		this.entity = entity;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public T getEntity() {
		return entity;
	}
}
