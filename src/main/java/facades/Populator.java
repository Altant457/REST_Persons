/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getInstance(emf);
        pf.addPerson("Lazaro", "Pacheco", "(201) xxx-xxxx");
        pf.addPerson("Ismael", "Simmons", "(414) xxx-xxxx");
        pf.addPerson("Avery", "Gibson", "(315) xxx-xxxx");
        pf.addPerson("Michael", "Mcfadden", "(509) xxx-xxxx");
    }
    
    public static void main(String[] args) {
        populate();
    }
}
