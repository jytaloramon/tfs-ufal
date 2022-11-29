COMPILER=javac
FLAGS=-Xlint

PATHPROJSERVER=labCOSMOS-limite-conectorrmi-server
PATHPROJSERVER2=labCOSMOS-limite-conectorrmi-server2
PATHPROJLIMITE=labCOSMOS-limite
PATHPROJLIMITECONECTOR=labCOSMOS-limite-conectorrmi

PATHPROJCLIENT=labCOSMOS-emprestimo-conectorrmi
PATHPROJEMPRESTIMO=labCOSMOS-emprestimo

FOLDERBIN=bin
FOLDERBINTEMP=tempbin

clean:
	rm -rf $(PATHPROJSERVER)/$(FOLDERBIN) \
		$(PATHPROJSERVER2)/$(FOLDERBIN) \
		$(PATHPROJCLIENT)/$(FOLDERBIN)

clean-content:
	rm -rf $(PATHPROJSERVER)/$(FOLDERBIN)/* \
		$(PATHPROJSERVER2)/$(FOLDERBIN)/* \
		$(PATHPROJCLIENT)/$(FOLDERBIN)/*

clean-tempbin:
	rm -rf $(FOLDERBINTEMP)

create-tempbin:
	mkdir -p $(FOLDERBINTEMP)/{src,$(FOLDERBIN)}

compile-server-limite: $(PATHPROJSERVER) create-tempbin
	cp -R $(PATHPROJLIMITE)/src $(PATHPROJLIMITECONECTOR)/src \
		$(PATHPROJSERVER)/src -d $(FOLDERBINTEMP) 
	
	$(COMPILER) $(FLAGS) \
		-sourcepath tempbin/src -d tempbin/bin \
		tempbin/src/br/ufal/aracomp/cosmos/limiteconectorrmiserver/Main.java

	cp -Rf $(FOLDERBINTEMP)/$(FOLDERBIN) $(PATHPROJSERVER)/
	rm -rf $(FOLDERBINTEMP)
	
compile-server2-limite: $(PATHPROJSERVER2) create-tempbin
	cp -R $(PATHPROJLIMITE)/src $(PATHPROJLIMITECONECTOR)/src \
		$(PATHPROJSERVER2)/src -d $(FOLDERBINTEMP) 
	
	$(COMPILER) $(FLAGS) \
		-sourcepath tempbin/src -d tempbin/bin \
		tempbin/src/br/ufal/aracomp/cosmos/limiteconectorrmiserver2/Main.java

	cp -Rf $(FOLDERBINTEMP)/$(FOLDERBIN) $(PATHPROJSERVER2)/
	rm -rf $(FOLDERBINTEMP)
	
compile-client-emprestimo: $(PATHPROJSERVER2) create-tempbin
	cp -R $(PATHPROJEMPRESTIMO)/src $(PATHPROJLIMITECONECTOR)/src \
		$(PATHPROJCLIENT)/src -d $(FOLDERBINTEMP) 
	
	$(COMPILER) $(FLAGS) \
		-sourcepath tempbin/src -d tempbin/bin \
		tempbin/src/br/ufal/aracomp/cosmos/emprestimoconectorrmi/client/Main.java

	cp -Rf $(FOLDERBINTEMP)/$(FOLDERBIN) $(PATHPROJCLIENT)/
	rm -rf $(FOLDERBINTEMP)
