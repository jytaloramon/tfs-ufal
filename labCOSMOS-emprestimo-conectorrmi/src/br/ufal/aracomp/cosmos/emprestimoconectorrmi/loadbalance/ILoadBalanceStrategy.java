package br.ufal.aracomp.cosmos.emprestimoconectorrmi.loadbalance;

public interface ILoadBalanceStrategy<T> {

	public T getInstance();

}
