package com.wemanity.bank.metier;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wemanity.bank.dao.IBanqueDao;
import com.wemanity.bank.entities.Client;
import com.wemanity.bank.entities.Compte;
import com.wemanity.bank.entities.Employe;
import com.wemanity.bank.entities.Groupe;
import com.wemanity.bank.entities.Operation;
import com.wemanity.bank.entities.Retrait;
import com.wemanity.bank.entities.Versement;

@Transactional
public class BanqueMetier implements IBanqueMetier{
	private IBanqueDao dao;
	
	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Client addClient(Client c) {
		return dao.addClient(c);
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		return dao.addEmploye(e, codeSup);
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		return dao.addGroupe(g);
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
		dao.addEmployeToGroupe(codeEmp, codeGr);
	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		return dao.addCompte(cp, codeCli, codeEmp);
	}

	@Override
	public void verser(double mt, String codeCpte, Long codeEmp) {
		dao.addOperation(new Versement(new Date(), mt), codeCpte, codeEmp);
		Compte cp = dao.consulterCompte(codeCpte);
		cp.setSolde(cp.getSolde() + mt);
		
	}

	@Override
	public void retirer(double mt, String codeCpte, Long codeEmp) {
		dao.addOperation(new Retrait(new Date(), mt), codeCpte, codeEmp);
		Compte cp = dao.consulterCompte(codeCpte);
		cp.setSolde(cp.getSolde() - mt);
		
	}

	@Override
	public void virement(double mt, String codeCpteSub, String codeCpteAdd, Long codeEmp) {
		this.retirer(mt, codeCpteSub, codeEmp);
		this.verser(mt, codeCpteAdd, codeEmp);
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		return dao.consulterCompte(codeCpte);
	}

	@Override
	public List<Operation> consulterOperations(String codeCpte) {
		return dao.consulterOperations(codeCpte);
	}

	@Override
	public Client consulterClient(Long codeCli) {
		return dao.consulterClient(codeCli);
	}

	@Override
	public List<Client> consulterClients(String mc) {
		return dao.consulterClients(mc);
	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		return dao.getComptesByClient(codeCli);
	}

	@Override
	public List<Compte> getComptesByEmploye(Long codeEmp) {
		return dao.getComptesByEmploye(codeEmp);
	}

	@Override
	public List<Employe> getEmployes() {
		return dao.getEmployes();
	}

	@Override
	public List<Groupe> getGroupes() {
		return dao.getGroupes();
	}

	@Override
	public List<Employe> getEmployesByGroupe(Long codeGr) {
		return dao.getEmployesByGroupe(codeGr);
	}

}
