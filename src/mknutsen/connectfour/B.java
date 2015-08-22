package mknutsen.connectfour;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Random;


@SuppressWarnings("serial")
public class B extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    final int difficulty = 3; //DIFFICULTY SETTING

    /*coordinates*/
    final int[] column = {11, 109, 208, 307, 405, 505, 604};

    final int[] row = {575, 476, 379, 281, 183, 84};

    Image board, red, blue, highlighter, welcomeScreen, dev, pieceHover, pieceHover2, helpScreen, homescreenCoverUp,
            creditsOverlay;

    Board connect;

    int undos;

    int[] selectedPieces;

    boolean showCredits = false;

    boolean animating = false;

    boolean undoing = false;

    boolean singlePlayer;

    boolean win = false;

    int endingY, endingX, xPos, yPos, endingY2, endingX2, xPos2, yPos2;

    boolean isHover = false;

    boolean turn = false;

    boolean revealDev = false;

    boolean devMode = false;

    int hPos = -1;

    boolean welcome = true;

    boolean randomize;

    boolean click = false;

    int goodIdeaCounter = 0;

    Button[] pieces = new Button[10];

    boolean choosePieces = false;

    Button done, regularButton, twoPlayer, mainMenu, winScreen, hover, mainMenu2, shayannButton, credits, halp,
            mainMenuHelp, signature;

    boolean showHelp = false;


    public B() {
        selectedPieces = new int[2];
        selectedPieces[0] = -1;
        selectedPieces[1] = -1;
        try {
            BufferedImage nn = ImageIO.read(getClass().getResourceAsStream("PieceHover2.png"));
            BufferedImage qq = ImageIO.read(getClass().getResourceAsStream("PieceHover.png"));
            BufferedImage b = ImageIO.read(getClass().getResourceAsStream("board.png"));
            BufferedImage z = ImageIO.read(getClass().getResourceAsStream("dev.png"));
            BufferedImage g = ImageIO.read(getClass().getResourceAsStream("button2.png"));
            BufferedImage A = ImageIO.read(getClass().getResourceAsStream("button3.png"));
            BufferedImage h = ImageIO.read(getClass().getResourceAsStream("highlighter.png"));
            BufferedImage w = ImageIO.read(getClass().getResourceAsStream("Darts_Banner AI Win.png"));
            BufferedImage x = ImageIO.read(getClass().getResourceAsStream("Darts_Banner AI Win Mouse Over.png"));
            BufferedImage help = ImageIO.read(getClass().getResourceAsStream("helpScreen.png"));
            BufferedImage sdaiofh = ImageIO.read(getClass().getResourceAsStream("menuWelcome.png"));
            BufferedImage wel = ImageIO.read(getClass().getResourceAsStream("welcome4.png"));
            BufferedImage zz = ImageIO.read(getClass().getResourceAsStream("mainMenu3.png"));
            setUpPieces();
            homescreenCoverUp = sdaiofh;
            dev = z;
            helpScreen = help;
            twoPlayer = new Button(430, 280, 200, 100, A);
            regularButton = new Button(70, 280, 200, 100, g);
            g = ImageIO.read(getClass().getResourceAsStream("TicTacToe_Button Credits.png"));
            credits = new Button(70, 450, 200, 100, g);
            g = ImageIO.read(getClass().getResourceAsStream("TicTacToe_Button Help.png"));
            halp = new Button(430, 450, 200, 100, g);
            undos = 5;
            hover = new Button(0, 0, 427, 100, x);
            connect = new Board();
            board = b;
            highlighter = h;
            winScreen = new Button(0, 0, 427, 100, w);
            welcomeScreen = wel;
            mainMenu = new Button(650, 0, 38, 35, zz);
            pieceHover = qq;
            pieceHover2 = nn;
            addMouseListener(this);
            addMouseMotionListener(this);
            addKeyListener(this);
            b = ImageIO.read(getClass().getResourceAsStream("done.png"));
            done = new Button(250, 220, 200, 100, b);
            b = ImageIO.read(getClass().getResourceAsStream("mainMenu2.png"));
            mainMenu2 = new Button(475, 0, 200, 100, b);
            mainMenuHelp = new Button(250, 550, 200, 100, b);
            b = ImageIO.read(getClass().getResourceAsStream("Shayann.png"));
            shayannButton = new Button(600, 0, 88, 88, b);
            b = ImageIO.read(getClass().getResourceAsStream("overlay.png"));
            creditsOverlay = b;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpPieces() {
        BufferedImage a;
        int k = 0;
        for (int i = 75; i <= 545; i = i + 117) {
            for (int j = 345; j <= 525; j += 115) {
                //System.out.println(j);
                try {
                    a = ImageIO.read(getClass().getResourceAsStream("Pieces/" + (k + 1) + ".png"));
                    pieces[k] = new Button(i, j, 88, 88, a);
                    k++;
                } catch (Exception e){
            e.printStackTrace();}
            }
        }

    }

    public void paint(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        /*START SCREEN*/
        if (welcome) {
            g.drawImage(welcomeScreen, 0, 0, null);
            if (!choosePieces) {
                if (showCredits) {
                    g.drawImage(homescreenCoverUp, 0, 0, this);
                    g.drawImage(creditsOverlay, 0, 0, this);
                    g.drawImage(mainMenuHelp.image, mainMenuHelp.startX, mainMenuHelp.startY, this);
                    /*Font f = new Font("TimesRoman",Font.BOLD,14);
                    g.setFont(f);
	                g.drawString("REMY BUSTANI MADE ALL OF THIS GAME LOOK PRETTY AND HE IS ALSO KIND OF PRETTY", 40,
	                300);
	                g.drawString("LOGAN CUNNINGHAM WAS THE VOICE IN THE GAME AND MADE IT SOUND SUPER BADASS", 44,
	                350);*/
                } else if (showHelp) {
                    g.drawImage(homescreenCoverUp, 0, 0, this);
                    g.drawImage(helpScreen, 0, 0, this);
                    g.drawImage(mainMenuHelp.image, mainMenuHelp.startX, mainMenuHelp.startY, this);
                } else {
                    g.drawImage(regularButton.image, regularButton.startX, regularButton.startY, null);
                    g.drawImage(twoPlayer.image, twoPlayer.startX, twoPlayer.startY, null);
                    g.drawImage(credits.image, credits.startX, credits.startY, this);
                    g.drawImage(halp.image, halp.startX, halp.startY, this);
                    /*dev mode*/
                    if (revealDev) {
                        g.drawImage(dev, 600, 600, null);
                    }
                }
            } else {
                Font f = new Font("TimesRoman", Font.BOLD, 25);
                g.setFont(f);
                if (selectedPieces[0] != -1) {
                    g.drawImage(pieceHover, pieces[selectedPieces[0]].startX - 4, pieces[selectedPieces[0]].startY - 4,
                            this);
                }
                if (selectedPieces[1] != -1) {
                    g.drawImage(pieceHover2, pieces[selectedPieces[1]].startX - 4, pieces[selectedPieces[1]].startY - 4,
                            this);
                }
                for (int i = 0; i < pieces.length; i++) {
                    //System.out.println(pieces[i].startY);
                    g.drawImage(pieces[i].image, pieces[i].startX, pieces[i].startY, this);
                }
                if (singlePlayer) {
                    int x = 185;
                    int y = 300;
                    if (selectedPieces[0] == -1) {
                        g.setColor(Color.BLUE);
                        g.drawString("SELECT YOUR PIECE COLOR", x, y);
                    } else if (selectedPieces[1] == -1) {
                        g.setColor(Color.GREEN);
                        g.drawString("SELECT THE COMPUTER'S PIECE COLOR", x - 85, y);
                    } else {
                        g.drawImage(done.image, done.startX, done.startY, this);
                    }
                } else {
                    int x = 130;
                    int y = 300;
                    if (selectedPieces[0] == -1) {
                        g.setColor(Color.BLUE);
                        g.drawString("PLAYER ONE: SELECT YOUR PIECE", x, y);
                    } else if (selectedPieces[1] == -1) {
                        g.setColor(Color.GREEN);
                        g.drawString("PLAYER TWO: SELECT YOUR PIECE", x, y);
                    } else {
                        g.drawImage(done.image, done.startX, done.startY, this);
                    }
                }
            }
        }

		/*GAME SCREEN*/
        else {
            g.drawImage(board, 0, 0, null);
            g.drawImage(mainMenu.image, mainMenu.startX, mainMenu.startY, null);
            if (!win && singlePlayer && !devMode) {
                /*Print Undos*/
                g.setColor(Color.BLACK);
                Font f = new Font("TimesRoman", Font.BOLD, 20);
                g.setFont(f);
                g.drawString(undos + " undos left", 5, 14);
            } else if (!win && devMode) {
                g.drawString("Developer Mode", 5, 14);
            }
            Piece temp, temp2;
            for (int i = 0; i < connect.moves.size() - 2; i++) {
                /*Print board except last 2*/
                temp = connect.moves.get(i);
                if (temp.getColor(true) == 1) {
                    g.drawImage(red, column[temp.getCol()], row[temp.getRow()], null);
                } else if (temp.getColor(true) == -1) {
                    g.drawImage(blue, column[temp.getCol()], row[temp.getRow()], null);
                }
            }
            /*NOT ANIMATING*/
            if (!animating && connect.moves.size() > 0) {
                if (connect.moves.size() > 1) {
                    temp = connect.moves.get(connect.moves.size() - 2);
                    if (temp.getColor(true) == 1) {
                        g.drawImage(red, column[temp.getCol()], row[temp.getRow()], null);
                    } else if (temp.getColor(true) == -1) {
                        g.drawImage(blue, column[temp.getCol()], row[temp.getRow()], null);
                    }
                }
                temp2 = connect.moves.get(connect.moves.size() - 1);
                if (temp2.getColor(true) == 1) {
                    g.drawImage(red, column[temp2.getCol()], row[temp2.getRow()], null);
                } else if (temp2.getColor(true) == -1) {
                    g.drawImage(blue, column[temp2.getCol()], row[temp2.getRow()], null);
                }
            }
            /*ANIMATING*/
            else if (connect.moves.size() > 0) {
                if (!singlePlayer && connect.moves.size() > 1) {
                    temp = connect.moves.get(connect.moves.size() - 2);
                    if (temp.getColor(true) == 1) {
                        g.drawImage(red, column[temp.getCol()], row[temp.getRow()], null);
                    } else {
                        g.drawImage(blue, column[temp.getCol()], row[temp.getRow()], null);
                    }
                }
                /*TWO PLAYER ANIMATING*/
                if (!singlePlayer || (win && connect.getWinner() == -1)) {
                    temp = connect.moves.get(connect.moves.size() - 1);

                    //System.out.println(temp.getColor());
                    if (temp.getColor(true) == 1) {
                        //System.out.println(yPos);
                        g.drawImage(red, xPos, yPos, null);
                    } else if (temp.getColor(true) == -1) {
                        // System.out.println(yPos);
                        g.drawImage(blue, xPos, yPos, null);
                    }
                    yPos += 12;
                    if (yPos > endingY) {
                        animating = false;
                        if (connect.catsCheck()) {
                            win = true;
                        }
                        new Thread(new Runnable() {

                            // The wrapper thread is unnecessary, unless it blocks on the
                            // Clip finishing; see comments.
                            public void run() {
                                try {
                                    //System.out.println("sound");
                                    InputStream in = null;
                                    try {
                                        in = getClass().getResourceAsStream("Connect4Sound" + ".wav");
                                    } catch (Exception e) {
                                    }

                                    Clip clip = AudioSystem.getClip();
                                    InputStream bufferedIn = new BufferedInputStream(in);
                                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                                    clip.open(inputStream);
                                    clip.start();
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                            }
                        }).start();

                        repaint();
                    } else {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        yPos += 12;
                        repaint();
                    }
                } else {
                    //temp = connect.moves.get(connect.moves.size()-2);
                    //temp2 = connect.moves.get(connect.moves.size()-1);
                    g.drawImage(blue, xPos, yPos, null);
                    if (yPos == endingY) {
                        g.drawImage(red, xPos2, yPos2, null);
                    }
                    if (yPos < endingY) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        yPos += 12;
                        repaint();
                    } else if (yPos >= endingY && click) {
                        click = false;
                        new Thread(new Runnable() {

                            // The wrapper thread is unnecessary, unless it blocks on the
                            // Clip finishing; see comments.
                            public void run() {
                                try {
                                    InputStream in = null;
                                    try {
                                        in = getClass().getResourceAsStream("Connect4Sound.wav");
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }

                                    Clip clip = AudioSystem.getClip();
                                    InputStream bufferedIn = new BufferedInputStream(in);
                                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                                    clip.open(inputStream);
                                    clip.start();
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                            }
                        }).start();
                    }
                    if (yPos2 < endingY2 && yPos >= endingY) {
                        yPos = endingY;
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        yPos2 += 8;
                        repaint();
                    } else if (yPos2 >= endingY2 && yPos >= endingY) {
                        animating = false;

                        new Thread(new Runnable() {

                            // The wrapper thread is unnecessary, unless it blocks on the
                            // Clip finishing; see comments.
                            public void run() {
                                try {
                                    InputStream in = null;
                                    try {
                                        in = getClass().getResourceAsStream("Connect4Sound.wav");
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    Clip clip = AudioSystem.getClip();
                                    InputStream bufferedIn = new BufferedInputStream(in);
                                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                                    clip.open(inputStream);
                                    clip.start();
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                            }
                        }).start();
                        repaint();
                    }
                }
            }
            /*UNDO*/
            if (undoing) {
                //temp = connect.moves.get(connect.moves.size()-2);
                //temp2 = connect.moves.get(connect.moves.size()-1);
                yPos -= 5;
                g.drawImage(blue, xPos2, yPos2, null);
                if (yPos > endingY) {
                    g.drawImage(red, xPos, yPos, null);
                    g.drawImage(blue, xPos2, yPos2, null);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    yPos -= 10;
                    repaint();
                } else if (yPos2 > endingY2) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    yPos2 -= 10;
                    repaint();
                } else {
                    undoing = false;
                    new Thread(new Runnable() {

                        // The wrapper thread is unnecessary, unless it blocks on the
                        // Clip finishing; see comments.
                        public void run() {
                            try {
                                InputStream in = null;
                                try {
                                    in = getClass().getResourceAsStream("undo.wav");
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                Clip clip = AudioSystem.getClip();
                                InputStream bufferedIn = new BufferedInputStream(in);
                                AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                                clip.open(inputStream);
                                clip.start();
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                    }).start();
                    repaint();
                }
            }
            if (hPos >= 0 && !win) {
                g.drawImage(highlighter, column[hPos], 0, null);
                if (!animating) {
                    if (singlePlayer) {
                        g.drawImage(blue, column[hPos], 0, null);
                    } else {
                        if (turn) {
                            g.drawImage(red, column[hPos], 0, null);
                        } else {
                            g.drawImage(blue, column[hPos], 0, null);
                        }
                    }
                }
            }
            if (win) {

                if (isHover) {
                    g.drawImage(hover.image, hover.startX, hover.startY, null);
                } else {
                    g.drawImage(winScreen.image, winScreen.startX, winScreen.startY, null);
                }
                g.drawImage(mainMenu2.image, mainMenu2.startX, mainMenu2.startY, this);
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        boolean game = false;
        ;
        //System.out.println(singlePlayer);
        /*GAME SCREEN*/
        if (!welcome && !animating && !undoing) {
            //System.out.println(e.getX());
            //randomizePieces();
            if (mainMenu.isInside(e)) {
                win = false;
                animating = false;
                welcome = true;
                undoing = false;
                undos = 5;
                devMode = false;
                choosePieces = false;
                selectedPieces[0] = -1;
                selectedPieces[1] = -1;
                connect = new Board();
                repaint();
            } else if (!win && e.getButton() == 1) {

                for (int i = 0; i < column.length - 1; i++) {
                    if (e.getX() > column[i] && e.getX() <= column[i + 1]) {
                        if (singlePlayer) {
                            game = playGame(i);
                        } else {
                            game = playGame(i, turn);
                            if (game) {
                                turn = !turn;
                            }
                        }
                        if (singlePlayer) {
                            xPos = column[i];
                            xPos2 = column[connect.moves.get(connect.moves.size() - 1).getCol()];
                            endingY = row[connect.moves.get(connect.moves.size() - 2).getRow()];
                            yPos = 0;
                            yPos2 = 0;
                            //System.out.println(i);
                            endingY2 = row[connect.moves.get(connect.moves.size() - 1).getRow()];
                        } else {
                            xPos = column[i];
                            yPos = 0;
                            endingY = row[connect.moves.get(connect.moves.size() - 1).getRow()];
                            //System.out.println(endingY+" "+xPos);

                        }
                        //connect.printBoard();
                        //connect.dropPiece(1,i);
                        //System.out.println(i+" "+connect.moves.size());
                    }
                }
                if (e.getX() > column[column.length - 1] && e.getX() <= 683) {
                    if (singlePlayer) {
                        game = playGame(column.length - 1);
                        xPos = column[column.length - 1];
                        xPos2 = column[connect.moves.get(connect.moves.size() - 1).getCol()];
                        yPos = 0;
                        yPos2 = 0;
                        //System.out.println(i);
                        endingY = row[connect.moves.get(connect.moves.size() - 2).getRow()];
                        endingY2 = row[connect.moves.get(connect.moves.size() - 1).getRow()];
                    } else {
                        game = playGame(column.length - 1, turn);
                        turn = !turn;
                        xPos = column[column.length - 1];
                        yPos = 0;
                        endingY = row[connect.moves.get(connect.moves.size() - 1).getRow()];
                    }
                }
                if (game) {
                    animating = true;
                    click = true;
                }
                //System.out.println(animating);
            } else if (!win && e.getButton() == 3) {
                //System.out.println("undo");


                if (singlePlayer && undos > 0 && connect.moves.size() >= 2) {
                    undoing = true;
                    xPos = column[connect.moves.get(connect.moves.size() - 1).getCol()];
                    yPos = row[connect.moves.get(connect.moves.size() - 1).getRow()];
                    xPos2 = column[connect.moves.get(connect.moves.size() - 2).getCol()];
                    yPos2 = row[connect.moves.get(connect.moves.size() - 2).getRow()];
                    endingY = 0;
                    endingY2 = 0;
                    connect.undo(connect.moves.get(connect.moves.size() - 1),
                            connect.moves.get(connect.moves.size() - 2));
                    if (!devMode) {
                        undos--;
                    }
                    new Thread(new Runnable() {

                        // The wrapper thread is unnecessary, unless it blocks on the
                        // Clip finishing; see comments.
                        public void run() {
                            try {
                                InputStream in = null;
                                try {
                                    in = getClass().getResourceAsStream("undo.wav");
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                Clip clip = AudioSystem.getClip();
                                InputStream bufferedIn = new BufferedInputStream(in);
                                AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                                clip.open(inputStream);
                                clip.start();
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                    }).start();
                } else if (devMode) {
                    connect.undo(connect.moves.get(connect.moves.size() - 1));
                    turn = !turn;
                }
            } else {
                if (undos > 1 && connect.moves.size() >= 2 && e.getButton() == 3) {

                    if (singlePlayer) {
                        undoing = true;
                        xPos = column[connect.moves.get(connect.moves.size() - 1).getCol()];
                        yPos = row[connect.moves.get(connect.moves.size() - 1).getRow()];
                        if (connect.getWinner() == 1) {
                            xPos2 = column[connect.moves.get(connect.moves.size() - 2).getCol()];
                            yPos2 = row[connect.moves.get(connect.moves.size() - 2).getRow()];
                            endingY = 0;
                            endingY2 = 0;
                            connect.undo(connect.moves.get(connect.moves.size() - 1),
                                    connect.moves.get(connect.moves.size() - 2));
                            undoing = true;
                        } else {
                            connect.undo(connect.moves.get(connect.moves.size() - 1));
                            undoing = true;
                        }
                        if (!devMode && connect.getWinner() == 1) {
                            undos -= 2;
                            win = false;
                            connect.setWinner(0);
                        }
                    }
                } else if (e.getX() >= 245 && e.getX() <= 424 && e.getY() <= 100 && e.getY() >= 0) {
                    win = false;
                    connect = new Board();
                    undos = 5;
                    if (randomize) {
                        randomizePieces();
                    }
                } else if (mainMenu2.isInside(e)) {
                    win = false;
                    animating = false;
                    welcome = true;
                    undoing = false;
                    undos = 5;
                    devMode = false;
                    goodIdeaCounter = 0;
                    choosePieces = false;
                    showHelp = false;
                    showCredits = false;
                    selectedPieces[0] = -1;
                    selectedPieces[1] = -1;
                    connect = new Board();
                    repaint();
                }
            }
            //connect.printBoard();
            //System.out.println(connect.scoreBoard());
        }

		/* START SCREEN */
        else {
            if (choosePieces) {
                if (shayannButton.isInside(e)) {
                    choosePieces = false;
                    welcome = false;
                    randomizePieces();
                }
                for (int i = 0; i < pieces.length; i++) {
                    if (pieces[i].isInside(e)) {

                        if (selectedPieces[1] == i) {
                            selectedPieces[1] = -1;
                        } else if (selectedPieces[0] == i) {
                            selectedPieces[0] = -1;
                        } else if (selectedPieces[0] == -1) {
                            selectedPieces[0] = i;
                        } else if (selectedPieces[1] == -1) {
                            selectedPieces[1] = i;
                        }
                    } else if (selectedPieces[0] != -1 && selectedPieces[1] != -1 && done.isInside(e)) {
                        choosePieces = false;
                        welcome = false;
                        normalizePieces(selectedPieces[0], selectedPieces[1]);
                    }
                }
            } else {
                if (e.getX() >= 600 && e.getX() <= 700 && e.getY() <= 650 && e.getY() >= 600) {
                    devMode = !devMode;
                } else if (regularButton.isInside(e)) {
                    singlePlayer = true;
                    choosePieces = true;
                    revealDev = false;
                }
                if (twoPlayer.isInside(e)) {
                    singlePlayer = false;
                    choosePieces = true;
                    revealDev = false;
                }
                if (credits.isInside(e)) {
                    showCredits = true;
                }
                if (halp.isInside(e)) {
                    showHelp = true;
                }
                if ((showHelp || showCredits) && mainMenuHelp.isInside(e)) {
                    showHelp = false;
                    showCredits = false;
                }
            }

        }
        repaint();
    }

    public void mouseExited(MouseEvent e) {
        hPos = -1;
        repaint();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        if (welcome && !choosePieces) {
            if (e.getX() >= 600 && e.getX() <= 700 && e.getY() <= 650 && e.getY() >= 600) {
                revealDev = true;
                repaint();
            } else if (revealDev && !devMode) {
                revealDev = false;
                repaint();
            }
        } else if (welcome && choosePieces) {
            if (shayannButton.isInside(e) && revealDev == false) {
                revealDev = true;
                repaint();
            } else if (revealDev && !shayannButton.isInside(e)) {
                revealDev = false;
                repaint();
            }
        }
        int temp = -1;
        for (int i = 0; i < column.length - 1; i++) {
            if (e.getX() > column[i] && e.getX() <= column[i + 1]) {
                temp = i;
            }
        }
        if (e.getX() > column[column.length - 1] && e.getX() <= 683) {
            temp = column.length - 1;
        }
        if (temp != hPos && temp != -1) {
            hPos = temp;
            repaint();
        }
        if (win && !welcome) {
            if (hover.isInside(e)) {
                isHover = true;
                repaint();
            } else if (isHover = true) {
                isHover = false;
                repaint();
            }
        }
        if (mainMenu.isInside(e) && !welcome && !win) {
            hPos = -1;
            repaint();
        }
    }

    public boolean playGame(int x) {
        //System.out.println(x+"  "+connect.board[5][x].getColor());
        ///connect.printBoard();
        if (connect.board[5][x].getColor(true) != 0) {
            return false;
        }
        if (!win && connect.board[5][x].getColor(true) == 0) {
            win = connect.playMove(x, difficulty);
            if (connect.catsCheck()) {
                win = true;
            }
            if (win && connect.total >= 30) {
                new Thread(new Runnable() {

                    // The wrapper thread is unnecessary, unless it blocks on the
                    // Clip finishing; see comments.
                    public void run() {
                        try {
                            InputStream in = null;
                            try {
                                in = getClass().getResourceAsStream("longGame.wav");
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } Clip clip = AudioSystem.getClip();
                            InputStream bufferedIn = new BufferedInputStream(in);
                            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                            clip.open(inputStream);
                            clip.start();
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }).start();
            } if (goodIdeaCounter > 0) {
                goodIdeaCounter--;
            }
            if (connect.scoreBoard() < -2 && !win) {
                if (goodIdeaCounter == 0) {
                    goodIdeaCounter = 5;
                    new Thread(new Runnable() {

                        // The wrapper thread is unnecessary, unless it blocks on the
                        // Clip finishing; see comments.
                        public void run() {
                            try {
                                InputStream in = null;
                                try {
                                    in = getClass().getResourceAsStream("playerGood.wav");
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } Clip clip = AudioSystem.getClip();
                                InputStream bufferedIn = new BufferedInputStream(in);
                                AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                                clip.open(inputStream);
                                clip.start();
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                    }).start();
                }
            } if (connect.getWinner() == -1) {
                try {
                    BufferedImage y =
                            ImageIO.read(getClass().getResourceAsStream("Darts_Banner AI Lose Mouse Over.png"));
                    BufferedImage z = ImageIO.read(getClass().getResourceAsStream("Darts_Banner AI Lose.png"));

                    hover.image = y;
                    winScreen.image = z;
                } catch (Exception e) {

                }
                if (connect.total < 30) {
                    new Thread(new Runnable() {

                        // The wrapper thread is unnecessary, unless it blocks on the
                        // Clip finishing; see comments.
                        public void run() {
                            try {
                                InputStream in = null;
                                try {
                                    in = getClass().getResourceAsStream("playerWin.wav");
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } Clip clip = AudioSystem.getClip();
                                InputStream bufferedIn = new BufferedInputStream(in);
                                AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                                clip.open(inputStream);
                                clip.start();
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                    }).start();
                }
            } else if (connect.getWinner() == 1) {
                try {
                    BufferedImage z = ImageIO.read(getClass().getResourceAsStream("Darts_Banner AI Win.png"));
                    BufferedImage y =
                            ImageIO.read(getClass().getResourceAsStream("Darts_Banner AI Win Mouse Over.png"));
                    hover.image = z;
                    winScreen.image = y;
                } catch (Exception e) {

                }
                if (connect.total < 30) {
                    new Thread(new Runnable() {

                        // The wrapper thread is unnecessary, unless it blocks on the
                        // Clip finishing; see comments.
                        public void run() {
                            try {
                                InputStream in = null;
                                try {
                                    in = getClass().getResourceAsStream("PlayerLose.wav");
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } Clip clip = AudioSystem.getClip();
                                InputStream bufferedIn = new BufferedInputStream(in);
                                AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
                                clip.open(inputStream);
                                clip.start();
                            } catch (Exception e) {
                                System.err.println(e.getMessage());
                            }
                        }
                    }).start();
                }
            }
            //connect.printBoard();
            //repaint();
        } return true;
    }

    public boolean playGame(int x, boolean color) {
        if (connect.board[5][x].getColor(true) != 0) {
            return false;
        }
        if (!win && connect.board[5][x].getColor(true) == 0) {
            win = connect.playMove(x, color);
            if (connect.catsCheck()) {
                win = true;
            }
            try {
                if (connect.getWinner() == 1) {

                    BufferedImage z = ImageIO.read(getClass().getResourceAsStream("Darts_Banner AI Win.png"));
                    BufferedImage y =
                            ImageIO.read(getClass().getResourceAsStream("Darts_Banner AI Win Mouse Over.png"));
                    hover.image = y;
                    winScreen.image = z;
                } else if (connect.getWinner() == -1) {
                    BufferedImage y =
                            ImageIO.read(getClass().getResourceAsStream("Darts_Banner AI Lose Mouse Over.png"));
                    BufferedImage z = ImageIO.read(getClass().getResourceAsStream("Darts_Banner AI Lose.png"));
                    hover.image = y;
                    winScreen.image = z;
                }
            } catch (Exception e) {

            }
            //connect.printBoard();
            //repaint();
        }
        return true;
    }

    public void randomizePieces() {
        Random rand = new Random();
        try {
            BufferedImage bl =
                    ImageIO.read(getClass().getResourceAsStream("/Shayann/shayann" + (rand.nextInt(10) + 1) + ".png"));
            BufferedImage r = ImageIO.read(getClass().getResourceAsStream("/Sean/" + (rand.nextInt(10) + 1) + ".png"));

            blue = bl;
            red = r;
        } catch (Exception e) {

        }
    }

    public void normalizePieces(int x, int y) {
        //System.out.println(x+" "+y);
        blue = pieces[x].image;
        red = pieces[y].image;
    }

    public void keyPressed(KeyEvent arg0) {
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }
}
