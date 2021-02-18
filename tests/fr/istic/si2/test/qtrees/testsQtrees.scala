package fr.istic.si2.test.qtrees

import org.junit.Test

import org.junit.Assert._
import util.Random
import fr.istic.si2.testerApp._
import fr.istic.si2.moreAssertions._
import fr.istic.si2.math._

import fr.istic.si2.qtrees.lib._
import fr.istic.si2.qtrees.main._

import fr.istic.si2.scribble._

import QtreeTransfo._

class QtreesTest {

  /**
   * un test
   */
  @Test
  def testCompress() {
    
    assertEquals(Area(BLUE), compress(Area(BLUE)))
    assertEquals(Area(BLUE), compress(Mix(Area(BLUE),Area(BLUE),Area(BLUE),Area(BLUE))))
    assertEquals(Mix(Area(BLUE),Area(GREEN),Area(BLUE),Area(BLUE)), compress(Mix(Area(BLUE),Area(GREEN),Area(BLUE),Area(BLUE))))
    
  }
  
  /**
   * un test
   */
  @Test
  def testRotate90() {
    
    val q1: Quadtree = Mix(Area(RED),Area(BLUE),Area(GREEN),Area(BLACK))
    val q2: Quadtree = Mix(Area(BLACK),Area(RED),Area(BLUE),Area(GREEN)) //valeur attendu lorsque q1 est passé en paramètre de la fonction testé
    assertEquals(Area(BLUE), rotate90(Area(BLUE)))
    assertEquals(Mix(Area(RED),Area(BLUE),Area(GREEN),Area(BLACK)), rotate90(Mix(Area(BLUE),Area(GREEN),Area(BLACK),Area(RED))))
    assertEquals(Mix(Area(BLUE),q2,Area(GREEN),q2), rotate90(Mix(q1,Area(GREEN),q1,Area(BLUE))))
    
  }
   
   /**
   * un test
   */
  @Test
  def testMirror() {
    
    val q1: Quadtree = Mix(Area(RED),Area(BLUE),Area(GREEN),Area(BLACK))
    val q2: Quadtree = Mix(Area(BLUE),Area(RED),Area(BLACK),Area(GREEN))
    
    assertEquals(Area(BLUE), mirror(Area(BLUE)))
    assertEquals(Mix(Area(BLUE),Area(RED),Area(BLACK),Area(GREEN)), mirror(Mix(Area(RED),Area(BLUE),Area(GREEN),Area(BLACK))))
    assertEquals(Mix(Area(GREEN),q2,Area(BLUE),q2), mirror(Mix(q1,Area(GREEN),q1,Area(BLUE))))
    
  }
  
  
   /**
   * un test
   */
  @Test
  def testMapQuadtree() {
    
    val q1: Quadtree = Mix(Area(RED),Area(BLUE),Area(WHITE),Area(BLACK))
    val q2: Quadtree = Mix(Area(BLACK),Area(BLUE),Area(BLUE),Area(BLACK))
    
    assertEquals(Area(BLUE), mapQuadtree(Area(BLUE), (c: Color) => c match { case Color(a,b,c,d) => Color(0,0,c,d) } ))
    assertEquals(Area(Color(0,0,100,100)), mapQuadtree(Area(Color(100,100,100,100)), (c: Color) => c match { case Color(a,b,c,d) => Color(0,0,c,d) } ))
    assertEquals(q2, mapQuadtree(q1, (c: Color) => c match { case Color(a,b,c,d) => Color(0,0,c,d) }))
     
  }
  
  
  /**
   * un test
   */
  @Test
  def testFiltreBleu() {
    
    val q1: Quadtree = Mix(Area(RED),Area(BLUE),Area(WHITE),Area(BLACK))
    val q2: Quadtree = Mix(Area(BLACK),Area(BLUE),Area(BLUE),Area(BLACK))
    
    assertEquals(Area(BLUE), filtreBleu(Area(BLUE)))
    assertEquals(Area(Color(0,0,100,100)), filtreBleu(Area(Color(100,100,100,100))))
    assertEquals(q2, filtreBleu(q1))
     
  }
  
  
   /**
   * un test
   */
  @Test
  def testDiminOpacite() {
    
    val q1: Quadtree = Mix(Area(RED),Area(BLUE),Area(WHITE),Area(BLACK))
    val q2: Quadtree = Mix(Area(Color(255,0,0,155)),Area(Color(0,0,255,155)),Area(Color(255,255,255,155)),Area(Color(0,0,0,155)))
    
    assertEquals(Area(Color(0,0,255,155)), diminOpacite(Area(BLUE)))
    assertEquals(Area(Color(100,100,100,0)), diminOpacite(Area(Color(100,100,100,100))))
    assertEquals(q2, diminOpacite(q1))
     
  }
  
  
  /**
   * un test
   */
  @Test
  def testFiltreZeroBleu() {
    
    val q1: Quadtree = Mix(Area(RED),Area(BLUE),Area(WHITE),Area(BLACK))
    val q2: Quadtree = Mix(Area(RED),Area(BLACK),Area(Color(255,255,0,255)),Area(BLACK))
    
    assertEquals(Area(BLACK), filtreZeroBleu(Area(BLUE)))
    assertEquals(Area(Color(100,100,0,100)), filtreZeroBleu(Area(Color(100,100,100,100))))
    assertEquals(q2, filtreZeroBleu(q1))
     
  }
    
}