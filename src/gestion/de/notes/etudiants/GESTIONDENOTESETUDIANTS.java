/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.de.notes.etudiants;
import gestion.de.notes.etudiants.metier.*;
import gestion.de.notes.etudiants.dao.*;

/**
 *
 * @author M@nU_LP
 */
public class GESTIONDENOTESETUDIANTS {
    static int choix;
    static String st;
    static java.util.Scanner sc = new java.util.Scanner(System.in);
    static EleveDAOImpl edi = new EleveDAOImpl();
    static ClasseDAOImpl cdi = new ClasseDAOImpl();
    static MatiereDAOImpl mdi = new MatiereDAOImpl();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        while(true){
        System.out.println("BIENVENUE DANS LE LOGICIEL DE GESTION DE NOTES DES ETUDIANTS");
        System.out.println("QUE VOULEZ-VOUS FAIRE ?");
        menu();
        
        
        System.out.println("Entrer votre choix");
        choix = sc.nextInt(); sc.nextLine();
        
        switch(choix){
            case 1 :
                Eleve el = new Eleve();
                System.out.print("Entrer le nom de l'élève : ");
                st = sc.nextLine();
                el.setNomEl(st);
                System.out.print("Son prénom : ");
                st = sc.nextLine();
                el.setPrenomEl(st);
                
                System.out.print("Sa date de naissance (AAAA-MM-JJ) : ");
                st = sc.nextLine();
                
                while(!st.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")){
                    System.out.print("Erreur format. Respectez le format(AAAA-MM-JJ) : ");
                    st = sc.nextLine();
                }
                el.setDateNaissEl(st);
                
                System.out.println((edi.ajouterEleve(el))?"Elève ajouté":
                                    "Echec, veuillez réessayer");
                
                break;
                
            case 2 :
                System.out.print("Entrer le nom de la classe : ");
                st = sc.nextLine(); // pour pouvoir une amélioration future du code( demande de confirmation ...)
                Classe cl = new Classe(st);
                
                System.out.println((cdi.ajouterClasse(cl))?"Classe ajoutée":
                                    "Echec, veuillez réessayer");
                
                break;
            case 3 : 
                Matiere mt = new Matiere();
                
                System.out.print("Entrer le libelé de la matiere : ");
                mt.setNomMat(sc.nextLine());
                /* Questionner monsieur sur le sujet
                System.out.print("Entrer le coefficient de la matiere : ");
                mt.setCoefMat(sc.nextInt()); sc.nextLine();
                */
                System.out.println((mdi.ajouterMatiere(mt))?"Matière ajoutée":
                                    "Echec, veuillez réessayer");
                
                break;
            
            case 4 :
                // On essaye d'ajouter l'élève sélectionné à la classe et on affiche un message
                System.out.println((cdi.ajouterElevedansClasse(selectionnerEleve(), selectionnerClasse()))?"Ajout éffectué" 
                                    : "Erreur, veuillez réessayer");
                
                break;
            case 5 : 
                // Choix du type de la composition(devoir ou examen)
                System.out.println("Sélectionnez le type de l'examen" + "\n" +
                        "\t1- Examen" + "\n\t" + "2- Devoir");
                System.out.print("Faites votre choix : ");
                choix = sc.nextInt(); 
                
                // Travail à refaire(paresse)
                st = (choix == 1)?"devoir":"examen";
                
                System.out.print("Entrez la note : ");
                float note = sc.nextFloat(); //sc.next();
                
                Matiere mat = selectionnerMatiere();
                el = selectionnerEleve();
                System.out.println((edi.saisirNote(el, mat, note, (choix == 1)?TypeComposition.examen:TypeComposition.devoir)?
                                    "Note ajoutée":"Echec, veuillez réessayer"));
                
                break;
                
            case 6 :
                System.out.println(edi.calculerMoy(selectionnerEleve(), selectionnerMatiere()));
                break;
                
            default:
                System.out.println("Option non valide");
                break;                
        }
        }
        
    }
    
    public static void menu(){
        String[] listeChoix = {"ajouter élève","ajouter classe", "ajouter matière",
                            "integrer un élève dans une classe","saisir note la note d'un étudiant",
                            "afficher la moyenned'un étudiant","afficher le rang d'un étudiant",
                            "faire l'état des moyennes dans une classe "};
        
        for (int i = 0; i < listeChoix.length; i++) {
            System.out.println((i+1)+"- "+listeChoix[i]);        
        }
    }
    
    public static void afficherEleves(){
        System.out.println("Numéro de l'élève" + "\t\t" + "Nom" + "\t\t" + "Prenoms");
        edi.listeEleve().forEach(student->{
            System.out.println(student.getIdEl() + "\t\t\t\t" + student.getNomEl() + "\t\t" + student.getPrenomEl());
        });
    }
    
    public static void afficherClasses(){
        System.out.println("Numéro de la classe" + "\t\t" + "libelé");
        cdi.listeClasse().forEach(cla->{
            System.out.println(cla.getIdCla()+ "\t\t\t\t" + cla.getNomCla());
        });
    }
    
    public static void afficherMatieres(){
        System.out.println("Numéro de la matière" + "\t\t" + "libelé");
        mdi.listeMatiere().forEach(mat->{
            System.out.println(mat.getIdMat()+ "\t\t\t\t" + mat.getNomMat());
        });
    }
    
    public static Eleve selectionnerEleve(){
        afficherEleves();
        System.out.print("Sélectionnez le numéro de l'élève : ");
        choix = sc.nextInt(); //sc.next();
        
        return edi.avoirAvecId(choix);        
    }

    public static Classe selectionnerClasse(){
        afficherClasses();
        System.out.print("Sélectionnez le numéro de la classe : ");
        choix = sc.nextInt(); //sc.next();
        
        return cdi.avoirParId(choix);        
    }  

    public static Matiere selectionnerMatiere(){
        afficherMatieres();
        System.out.print("Sélectionnez le numéro de la matière : ");
        choix = sc.nextInt(); //sc.next();
        
        return mdi.avoirAvecId(choix);        
    }    
}
