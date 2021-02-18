package fr.istic.si2.qtrees.main

import fr.istic.si2.qtrees.lib._
import fr.istic.si2.scribble._

import QtreeImage._
import QtreeTransfo._


/**
 * Définition du type algébrique récursif des quadtrees colorés
 */
sealed trait Quadtree
case class Area(c: Color) extends Quadtree
case class Mix(NO: Quadtree, NE: Quadtree, SE: Quadtree, SO: Quadtree) extends Quadtree

/**
 * Application principale V0
 */
object QtreeAppV0 extends App {

  
  val q1: Quadtree = Area(RED)
  
  val q5: Quadtree = Mix(Area(WHITE), Area(WHITE), Area(WHITE), Area(WHITE))
  
  val q2: Quadtree = Mix(Area(WHITE), Area(BLUE), Area(GREEN), Area(BLACK))
  
  val q3: Quadtree = Mix(q2,q5,q2,q5)
  
  val q4: Quadtree = Mix(q1, Area(Color(200,100,125,150)), Area(Color(200,100,125,150)), q2)
  
  val q6: Quadtree = Mix(q4,q3,q2,q2)
  
  
  //test grid:
  
   //draw(image(q3,256, true))
   //draw(image(q3,256, false))
  
  //test compression:
  
  //draw(image(q3,256, true))
  //draw(image(compress(q3),256, true))
  
  //test rotation90:
  
  //draw(image(compress(q6),256, true))
  //draw(image(rotate90(compress(q6)),256,true))
  
  //test mirroir:
  
  //draw(image(q3,256, true))
  //draw(image(mirror(q3),256, true))
  
  //test filtre couleur bleu:
  
  //draw(image(q6,256, true))
  //draw(image(filtreBleu(q6), 256, true))
  
  //test diminution opacité:
  
  draw(image(q6,256, true))
  draw(image(diminOpacite(q6), 256, true))
  
  //test filtre sans le bleu:
  
  //draw(image(q6,256, true))
  //draw(image(filtreZeroBleu(q6), 256, true))
  
  
  //draw(image(q1,16, true))
  //draw(image(q2,64, true))
  //draw(image(q2,64, false))
  //draw(image(q4,256, false))
  //draw(image(q6,256, true))
  
  //draw(image(rotate90(compress(q3)),256,true))
  //draw(image(mirror(compress(q3)),256,true))
  
  //draw(image(filtreBleu(q6), 256, true))
  //draw(image(diminOpacite(q4), 256, true))
  
  
  
}