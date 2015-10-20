    //// X5:  collisions.
    //// (Assume ball diameter of 30.)
    
    //// GLOBALS:  pool table, 3 colored balls   50,20, 150,50
    
    String title=  "ELASTIC COLLISIONS";
    String news=   "Use 'r' key to reset.";
    String author=  "Luis Fuentes";
    
    
    float left, right, top, bottom;
    float middle;
    
    float cueX, cueY, cueDX, cueDY;
    float redX, redY, redDX, redDY;
    float yelX, yelY, yelDX, yelDY;
    float bluX, bluY, bluDX, bluDY;
    float rX, rY, rW, rH;
    
    //// SETUP:  size and table
    void setup()
    { 
      rX=50;
      rY=20;
      rW=150;
      rH=50;
      size( 600, 400 );
      left=   50;
      right=  width-50;
      top=    100;
      bottom= height-50;
      middle= left + (right-left) / 2;
      //
      reset();
    }
    void reset() {
    
      // Random positions.
      cueX=  top; 
      cueY=  bottom;
      redX=right;   
      redY= top;
      yelX=  middle;   
      yelY=  bottom;
      bluX=  random( middle, right );   
      bluY=  random( top, bottom );
      // Random speeds
      redDX=  random( 1, 3 );   
      redDY=  random( 1, 3 );
      yelDX=  random( 1, 3 );   
      redDY=  random( 1, 3 );
      bluDX=  random( 1, 3 );   
      bluDY=  random( 1, 3 );
      cueDX=  random( 1, 3 );   
      cueDY=  random( 1, 3 );
    }
    
    //// NEXT FRAME:  table, bounce off walls, collisions, show all
    void draw() {
      background( 250, 250, 200 );
      rectMode( CORNERS );
      table( left, top, right, bottom );
      bounce();
      collisions();
      show();
      messages();
      textSize(15);
      text("reset", 75, 40);
      noFill();
      rect(rX, rY, rW, rH);
    }
    
    //// SCENE:  draw the table with walls
    void table( float left, float top, float right, float bottom ) {
      fill( 100, 250, 100 );    // green pool table
      strokeWeight(20);
      stroke( 127, 0, 0 );      // Brown walls
      rect( left-20, top-20, right+20, bottom+20 );
      stroke(0);
      strokeWeight(1);
    }
    
    //// ACTION:  bounce off walls, collisions
    void bounce() {
      redX += redDX;  
      if ( redX<left || redX>right ) redDX *= -1;
      redY += redDY;  
      if ( redY<top || redY>bottom ) redDY *=  -1;
      cueX += cueDX;  
      if ( cueX<left || cueX>right ) cueDX *= -1;
      cueY += cueDY;  
      if ( cueY<top || cueY>bottom ) cueDY *=  -1;
    
      bluX += bluDX;  
      if ( bluX<left || bluX>right ) bluDX *= -1;
      bluY += bluDY;  
      if ( bluY<top || bluY>bottom ) bluDY *=  -1;
      yelX += yelDX;  
      if ( yelX<left || yelX>right ) yelDX *= -1;
      yelY += yelDY;  
      if ( yelY<top || yelY>bottom ) yelDY *=  -1;
    }
    void collisions() {
      float tmp;
      // Swap velocities!
      if ( dist( redX, redY, yelX, yelY ) < 30 ) {
        tmp=yelDX;  
        yelDX=redDX;  
        redDX=tmp;
        tmp=yelDY;  
        yelDY=redDY;  
        redDY=tmp;
      }
      if ( dist( redX, redY, bluX, bluY ) < 30 ) {
        tmp=bluDX;  
        bluDX=redDX;  
        redDX=tmp;
        tmp=bluDY;  
        bluDY=redDY;  
        redDY=tmp;
      }
      if ( dist( bluX, bluY, yelX, yelY ) < 30 ) {
        tmp=yelDX;  
        yelDX=bluDX;  
        bluDX=tmp;
        tmp=yelDY;  
        yelDY=bluDY;  
        bluDY=tmp;
      }
    
      if ( dist( cueX, cueY, yelX, yelY ) < 30 ) {
        tmp=yelDX;  
        yelDX=cueDX;  
        cueDX=tmp;
        tmp=yelDY;  
        yelDY=cueDY;
      } 
      if ( dist( redX, redY, cueX, cueY ) < 30 ) {
        tmp=cueDX;  
        cueDX=redDX;  
        redDX=tmp;
        tmp=cueDY;  
        cueDY=redDY;  
        redDY=tmp;
      }
    
      if ( dist( bluX, bluY, cueX, cueY ) < 30 ) {
        tmp=cueDX;  
        cueDX=bluDX;  
        bluDX=tmp;
        tmp=cueDY;  
        cueDY=bluDY;
      }
    }
    
    
    //// SHOW:  balls, messages
    void show() {
      fill( 255, 255, 255 );    
      ellipse( redX, redY, 30, 30 );
      fill( 255, 0, 0 );    
      ellipse( redX, redY, 30, 30 );
      fill( 255, 255, 0 );  
      ellipse( yelX, yelY, 30, 30 );
      fill( 0, 0, 255 );    
      ellipse( bluX, bluY, 30, 30 );
      fill( 255, 255, 255 );    
      ellipse( cueX, cueY, 30, 30 );
      if (( frameCount/120) %2 > 0) {
        fill(255,0,100);
        ellipse( redX, redY, 50, 50 );
        fill(55,0,250);
        ellipse( yelX, yelY, 70, 50 );
        fill(5,200,20);
        ellipse( bluX, bluY, 50, 60 );
        fill(55,100,100);
        ellipse( cueX, cueY, 60, 50 );
      }
    }
    void messages() {
      fill(0);
      text( title, width/3, 20 );
      text( news, width/3, 40 );
      text( author, 10, height-10 );
      text("1", redX-5, redY+5);
      text("2", cueX-5, cueY+5);
      text("3", yelX-5, yelY+5);
      text("4", bluX-5, bluY+5);
    }
    
    void mousePressed() {
      if (mouseX>rX && mouseX<rX+rW && mouseY>rY&&mouseY<rY+rH)
      {
        reset();
      }
    
    
      if (mouseX>cueX-15 && mouseX < cueX+15 && mouseY > cueY-15 && mouseY<cueY+15)
      {
        fill(255, 200, 100);
        ellipse(cueX+20, cueY-50, 25, 25);
      }
    }
    //// HANDLERS:  keys, click
    /*void mousePressed(){
     if(hit(mouseX,mouseY,rX, rY, rW, rH))
     {background(0);}}
     
     boolean hit(float  , mouseY, float X, float Y, float W, float H)
     { return mouseX>X && mouseX<X+W && mouseY>Y&&mouseY<Y+H;} 
     */
    
    
    



