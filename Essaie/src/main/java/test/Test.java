package test;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wemanity.bank.entities.Client;
import com.wemanity.bank.entities.CompteCourant;
import com.wemanity.bank.entities.CompteEpargne;
import com.wemanity.bank.entities.Employe;
import com.wemanity.bank.entities.Groupe;
import com.wemanity.bank.metier.IBanqueMetier;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		IBanqueMetier metier = (IBanqueMetier) context.getBean("metier");
		metier.addClient(new Client("C3", "AD1"));
		metier.addClient(new Client("C4", "AD2"));
		metier.addEmploye(new Employe("E1"), null);
		metier.addEmploye(new Employe("E2"), 1L);
		metier.addEmploye(new Employe("E3"), 1L);
		metier.addGroupe(new Groupe("G1"));
		metier.addGroupe(new Groupe("G2"));
		metier.addEmployeToGroupe(1L, 1L);
		metier.addEmployeToGroupe(2L, 2L);
		metier.addEmployeToGroupe(3L, 2L);
		
		metier.addCompte(new CompteCourant("CC1", new Date(), 9000, 8000), 1L, 2L);
		metier.addCompte(new CompteEpargne("CE1", new Date(), 40000, 5.5), 2L, 2L);
		
		metier.verser(5000, "CC1", 2L);
		metier.retirer(6000, "CC1", 2L);
		
		metier.virement(4000, "CC1", "CE1", 1L);
		
		
	}

}
