package com.wemanity.bank.metier;

import java.util.List;

import com.wemanity.bank.entities.Client;
import com.wemanity.bank.entities.Compte;
import com.wemanity.bank.entities.Employe;
import com.wemanity.bank.entities.Groupe;
import com.wemanity.bank.entities.Operation;

public interface IBanqueMetier {
	public Client addClient(Client c);
	public Employe addEmploye(Employe e, Long codeSup);
	public Groupe addGroupe(Groupe g);
	public void addEmployeToGroupe(Long codeEmp, Long codeGr);
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp);
	public void verser(double mt, String codeCpte, Long codeEmp);
	public void retirer(double mt, String codeCpte, Long codeEmp);
	public void virement(double mt, String codeCpteSub, String codeCpteAdd, Long codeEmp);
	 
	public Compte consulterCompte(String codeCpte);
	public List<Operation> consulterOperations(String codeCpte);
	public Client consulterClient(Long codeCli);
	public List<Client> consulterClients(String mc);
	public List<Compte> getComptesByClient(Long codeCli);
	public List<Compte> getComptesByEmploye(Long codeEmp);
	public List<Employe> getEmployes();
	public List<Groupe> getGroupes();
	public List<Employe> getEmployesByGroupe(Long codeGr);
}
