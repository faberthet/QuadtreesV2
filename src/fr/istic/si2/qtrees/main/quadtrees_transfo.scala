package fr.istic.si2.qtrees.main

import fr.istic.si2.scribble._
import fr.istic.si2.qtrees.lib._

object QtreeTransfo {

  /**
   * @param q un quadtree
   * @return le quadtree correspondant à la compression de q
   */
  def compress(q: Quadtree): Quadtree = {
    
    q match{
      
      case Area(a) => Area(a)
      case Mix(no,ne,se,so) => val no_c:Quadtree = compress(no)
                               val ne_c: Quadtree =compress(ne)
                               val se_c: Quadtree =compress(se)
                               val so_c: Quadtree =compress(so)
                             
                             (no_c,ne_c,se_c,so_c) match {
        
                                                 case (Area(c1), Area(c2), Area(c3), Area(c4)) => if (c1==c2 && c2==c3 && c3==c4){Area(c1)}
                                                                                                  else{ Mix(no_c,ne_c,se_c,so_c)}
                                                 case _ => Mix(no_c,ne_c,se_c,so_c)
                             }
                                                      
    }
    
  }

  /**
   * @param q un quadtree
   * @return le quadtree correspondant à une rotation de q de 90º vers la droite
   */
  def rotate90(q: Quadtree): Quadtree = {
    
    q match{
      
      case Area(a) => Area(a)
      case Mix(a,b,c,d) => Mix(rotate90(d),rotate90(a),rotate90(b),rotate90(c))
        
    }
    
  }

  /**
   * @param q un quadtree
   * @return le quadtree correspondant à q ayant subi un retournement horizontal
   *
   * @note Attention, ce retournement NE PEUT PAS être programmé avec la rotation.
   */
  def mirror(q: Quadtree): Quadtree = {
    
     q match{
      
      case Area(a) => Area(a)
      case Mix(a,b,c,d) => Mix(mirror(b),mirror(a),mirror(d),mirror(c))
        
    }
    
  }
    
  /**
   * @param q un quadtree
   * @param f une fonction de transformation de couleur
   * @return le quadtree correspondant à la même image que q, mais où chaque couleur a subi
   *         la transformation f
   */

  def mapQuadtree(q: Quadtree, f: Color => Color): Quadtree = {
    
    q match {
      
      case Area(c) => Area(f(c))
      case Mix(a,b,c,d) => Mix(mapQuadtree(a, f), mapQuadtree(b, f), mapQuadtree(c, f), mapQuadtree(d, f))
      
    }
    
  }
   
  
  /**
   * @param q un Quadtree
   * @return un quadtree dont les couleurs des feuilles ne gardent que la composante bleu si elle existent, noir sinon
   */
  def filtreBleu(q: Quadtree): Quadtree = {
    
    mapQuadtree(q, (c: Color) => c match { case Color(a,b,c,d) => Color(0,0,c,d) })  
  }
  
  
  /**
   * @param q un Quadtree
   * @return un quadtree dont les couleurs des feuilles sont otées de leur composante bleu
   */
  def filtreZeroBleu(q: Quadtree): Quadtree = {
    
    mapQuadtree(q, (c: Color) => c match { case Color(a,b,c,d) => Color(a,b,0,d) })  
  }
  
  
  /**
   * @param q un Quadtree 
   * @return un quadtree dont l'opacité des feuille est diminuée de 100
   */
  def diminOpacite(q: Quadtree): Quadtree = {
    
    mapQuadtree(q, (c: Color) => c match { case Color(a,b,c,d) => if(d<100){Color(a,b,c,0)}else{Color(a,b,c,d-100)} })
    
  }
  
}