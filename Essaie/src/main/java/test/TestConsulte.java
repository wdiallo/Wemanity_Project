package test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wemanity.bank.entities.Compte;
import com.wemanity.bank.entities.Operation;
import com.wemanity.bank.metier.IBanqueMetier;

public class TestConsulte {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		IBanqueMetier metier = (IBanqueMetier) context.getBean("metier");
		Compte cp = metier.consulterCompte("CC1");
		
		System.out.println("Solde compte : "+cp.getSolde()+"Euros");
		System.out.println("Date création : "+cp.getDateCreation());
		System.out.println("Client : "+cp.getClient().getNomClient());
		System.out.println("Employé : "+cp.getEmploye().getNomEmploye());
		
		List<Operation> ops = metier.consulterOperations("CC1");
		for(Operation op : ops) {
			System.out.println("**********************");
			System.out.println(op.getNumeroOperation());
			System.out.println(op.getDateOperation());
			System.out.println(op.getMontant());
			System.out.println(op.getClass().getSimpleName());
		}

	}

}
