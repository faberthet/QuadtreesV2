package fr.istic.si2.qtrees.main

import fr.istic.si2.qtrees.lib._
import fr.istic.si2.scribble._

import QtreeCreation._
import QtreeTransfo._
import QtreeImage._

/**
 * Application principale V1
 */
object QtreeAppV1 extends App {

  
  
  val matrice1 = servM.creer(4, 4, (i,j) => RED)
  val matrice2 = servM.creer(8, 8, (i,j) => RED)
  
 val matrice3 = servM.set(matrice1, (2,2), BLUE)
 val matrice4 = servM.set(matrice3, (1,1), GREEN)
  
 
 // TEST DE FROM MATRIX:
 
  //draw(image(fromMatrix(matrice1,(0,0),4), 256, true))
  //draw(image(fromMatrix(matrice2,(0,0),8), 256, true))
  //draw(image(fromMatrix(matrice4,(0,0),4), 256, true))
  
  

  // TEST DE BUILD:
 
  val quadFile: Quadtree = build("fichiers/rainbow.png")
  
  //avec grille
  draw(image(quadFile, 512, true))
  
  //sans grille
  draw(image(quadFile, 512, false))
  
  
}