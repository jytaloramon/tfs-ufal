package br.ufal.aracomp.cosmos.limiteconectorrmi.limiteop;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.ufal.aracomp.cosmos.limiteconectorrmi.spec.dt.ClientDTConector;

public interface ICalculaLimiteConector extends Remote {

	public double calculaLimite(ClientDTConector clientDTConector) throws RemoteException;

}
