package fr.istic.si2.qtrees.main

import fr.istic.si2.scribble._
import fr.istic.si2.qtrees.lib._
import QtreeTransfo._

object QtreeCreation {

  /**
   * Le service des matrices à utiliser.
   * Ce sont des matrices dont les éléments sont des couleurs.
   */
  val servM: ServMatrice[Color] = MeoServMatriceColor

  /**
   * @param m une matrice carrée de couleurs
   * @param ij une paire d'entiers
   * @param dim un entier qui est une puissance de 2, et
   *        inférieur ou égal à la dimension de m
   * @return un quadtree "maximal" correspondant à la sous-matrice de m
   *         de dimension (dim X dim), et dont le coin supérieur gauche
   *         est de coordonnées ij.
   */
  
  def fromMatrix(m: servM.Matrice, ij: (Int, Int), dim: Int): Quadtree = {
    
    dim match {
      
      case 1 => servM.get(m, ij) match {
        
                        case None => Area(TRANSPARENT)
                        case Some(c) => Area(c)
        
                }
      
      case _ =>
        
      Mix(fromMatrix(m,ij,dim/2),
          fromMatrix(m,(ij._1,ij._2 + dim/2),dim/2),
          fromMatrix(m,(ij._1 + dim/2,ij._2 + dim/2),dim/2),
          fromMatrix(m,(ij._1 + dim/2,ij._2),dim/2))
      
    }
    
  } 

  /**
   * @param filename un nom de fichier relatif au projet ScalaIDE
   * @return le quadtree compressé correspondant à l'image contenue dans le fichier filename
   */
  
  def build(filename: String): Quadtree = {
    
    val largeur = getDimensions(filename)._1
    val hauteur = getDimensions(filename)._2
    val dimmax = if(largeur>hauteur){largeur}else{hauteur}
    
    val matimg = servM.creer(hauteur, largeur, readColor(filename))
    
    compress(fromMatrix(matimg,(0,0),dimmax))
    
  }
  
  
  
}