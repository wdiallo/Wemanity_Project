package com.wemanity.bank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.wemanity.bank.entities.Client;
import com.wemanity.bank.entities.Compte;
import com.wemanity.bank.entities.Employe;
import com.wemanity.bank.entities.Groupe;
import com.wemanity.bank.entities.Operation;

@Transactional
public class BanqueDao implements IBanqueDao{

	@PersistenceContext
	private EntityManager em;
	@Override
	public Client addClient(Client c) {
		em.persist(c);
		return c;
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		if(codeSup != null) {
			Employe sup = em.find(Employe.class, codeSup);
			e.setEmployeSup(sup);
		}
		em.persist(e);
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		em.persist(g);
		return g;
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
		Employe e = em.find(Employe.class, codeEmp);
		Groupe g = em.find(Groupe.class, codeGr);
		e.getGroupe().add(g);
		g.getEmployes().add(e);
	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		Client cli = em.find(Client.class, codeCli);
		Employe emp = em.find(Employe.class, codeEmp);
		cp.setClient(cli);
		cp.setEmploye(emp);
		em.persist(cp);
		return cp;
	}

	@Override
	public Operation addOperation(Operation op, String codeCpte, Long codeEmp) {
		Compte cp = consulterCompte(codeCpte);
		Employe emp = em.find(Employe.class, codeEmp);
		op.setCompte(cp);
		op.setEmployeCreate(emp);
		em.persist(op);
		return op;
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp = em.find(Compte.class, codeCpte);
		if(cp == null) throw new RuntimeException("Compte introuvable!");
		return cp;
	}
	
	@Override
	public List<Operation> consulterOperations(String codeCpte) {
		String rq = "SELECT o FROM Operation o where o.compte.codeCompte = :x";
		Query req = em.createQuery(rq);
		req.setParameter("x", codeCpte);
		return req.getResultList();
	}
	
	@Override
	public Client consulterClient(Long codeCli) {
		Client cli = em.find(Client.class, codeCli);
		if(cli == null) throw new RuntimeException("Client introuvable");
		return cli;
	}

	@Override
	public List<Client> consulterClients(String mc) {
		String rq = "SELECT c FROM Client c where c.nomClient like :x";
		Query req = em.createQuery(rq);
		req.setParameter("x", "%"+mc+"%");
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		String rq = "SELECT c FROM Compte c where c.client.codeClient = :x";
		Query req = em.createQuery(rq);
		req.setParameter("x", codeCli);
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesByEmploye(Long codeEmp) {
		String rq = "SELECT c FROM Compte c where c.employe.codeEmploye = :x";
		Query req = em.createQuery(rq);
		req.setParameter("x", codeEmp);
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployes() {
		String rq = "SELECT e FROM Employe ";
		Query req = em.createQuery(rq);
		return req.getResultList();
	}

	@Override
	public List<Groupe> getGroupes() {
		String rq = "SELECT g FROM Groupe ";
		Query req = em.createQuery(rq);
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployesByGroupe(Long codeGr) {
		String rq = "SELECT e FROM Employe e where e.groupe.codeGroupe = :x";
		Query req = em.createQuery(rq);
		req.setParameter("x", codeGr);
		return req.getResultList();
	}
	
	
}
