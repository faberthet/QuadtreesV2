package fr.istic.si2.qtrees.main

import fr.istic.si2.qtrees.lib._
import fr.istic.si2.scribble._

/**
 * Application principale V2
 */



object QtreeAppV2 extends App {
  
  
  println("insérez un nom de fichier:");
    
  val UnivQ: Universe[Etat] = UnivQuadtrees
  
  bigbang[Etat](UnivQ)
  
  println("commande: afficher/retirer la grille: touche 'g', activer/désactiver l'affichage simultané de l'état initial avec l'état courant: touche 'c'")
  println("rotation de 90 degrés vers la droite: touche 'r', image mirroir: touche 'm'")
  println("filtre ne gardant que la composante bleu: touche 'b', filtre sans le bleu: touche 'z', diminution d'opacité: touche 'o'")
  println("réinitialiser l'état courant: touche 'i'")
  
  
}