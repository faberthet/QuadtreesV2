package fr.istic.si2.qtrees.main

import fr.istic.si2.qtrees.lib._
import fr.istic.si2.scribble._
import QtreeTransfo._
import QtreeCreation._
import QtreeImage._


sealed trait Etat
case class QuadNoGrid(q:Quadtree) extends Etat // état courant sans grille
case class QuadGrid(q:Quadtree) extends Etat //état courant avec grille
case class CompareInitNoGrid(q:Quadtree) extends Etat //état courant sans grille + état initial
case class CompareInitGrid(q:Quadtree) extends Etat //état courant avec grille + état initial


object UnivQuadtrees extends Universe[Etat]{
  
  val WIDTH: Int = 1024
  
  val HEIGHT: Int = 1024
  
  val name: String = "Application Quadtrees"
  
  val QuadtreeFile = io.StdIn.readLine()
  
  override def init: Etat = {
    
      QuadNoGrid(build(QuadtreeFile))
     
  }
  
  override def toImage(s: Etat): Image = {
    
    s match {
      
      case QuadNoGrid(q) => image(q,512,false) //image sans grille
      case QuadGrid(q) => image(q,512,true) // image avec grille
      case CompareInitNoGrid(q) => beside(image(build(QuadtreeFile), 512, false), image(q, 512, false)) // image initial + image courante sans grille
      case CompareInitGrid(q) => beside(image(build(QuadtreeFile), 512, false), image(q, 512, true)) //image initial + image courante avec grille
      
    }
    
  }
  
  override def stopWhen(s: Etat): Boolean = {
    
    s match {
      
      case QuadNoGrid(q) => false 
      case QuadGrid(q) => false
      case CompareInitNoGrid(q) => false
      case CompareInitGrid(q) => false
      
    }
    
  }
  
  
  /**
   * @param s un Etat
   * @param f une fonction de transformation de Quadtree
   * @return l'application de la fonction f au paramètre de l'Etat s
   */
  def mapEtat(s: Etat, f: Quadtree => Quadtree): Etat = {
    
    s match {
      
      case QuadNoGrid(q) => QuadNoGrid(f(q))
      case QuadGrid(q) => QuadGrid(f(q))
      case CompareInitNoGrid(q) => CompareInitNoGrid(f(q))
      case CompareInitGrid(q) => CompareInitGrid(f(q))
      
    }
    
  }
  
  override def react(s: Etat, e: Event): Etat = {
    
    
    e match {
      case KeyPressed(KeyAscii('g')) => s match { // active/désactive l'affichage de la grille
                                          case QuadNoGrid(q) => QuadGrid(q)
                                          case QuadGrid(q) =>  QuadNoGrid(q)
                                          case CompareInitNoGrid(q) => CompareInitGrid(q)
                                          case CompareInitGrid(q) => CompareInitNoGrid(q)
                                          }
      case KeyPressed(KeyAscii('c')) => s match { // active/désactive l'affichage simultané de l'état initial et l'état courant
                                          case QuadNoGrid(q) => CompareInitNoGrid(q)
                                          case QuadGrid(q) =>  CompareInitGrid(q)
                                          case CompareInitNoGrid(q) => QuadNoGrid(q)
                                          case CompareInitGrid(q) => QuadGrid(q)
                                          }
      
      case KeyPressed(KeyAscii('b')) => mapEtat(s, filtreBleu) // filtre ne gardant que la composante bleu
      case KeyPressed(KeyAscii('z')) => mapEtat(s, filtreZeroBleu) //filtre qui ôte la composante bleu
      case KeyPressed(KeyAscii('o')) => mapEtat(s, diminOpacite) //filtre qui diminue l'opacité
      case KeyPressed(KeyAscii('r')) => mapEtat(s,rotate90) //effectue une rotation de 90 degrés vers la droite de l'image
      case KeyPressed(KeyAscii('m')) => mapEtat(s, mirror) //affiche l'image mirroir selon l'axe vertical
      case KeyPressed(KeyAscii('i')) => s match { // réinitialise l'état courant
                                          case CompareInitNoGrid(q) => CompareInitNoGrid(build(QuadtreeFile))
                                          case CompareInitGrid(q) => CompareInitNoGrid(build(QuadtreeFile))
                                          case _ => QuadNoGrid(build(QuadtreeFile))
      }
      case _ => s
      
      
      }
           
    }
    
}

