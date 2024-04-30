import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 500;
    int boardheight = 550;
    
    JFrame frame = new JFrame("Tic-Tac-Toe"); //titel for the window
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3]; 
    String palyerX ="X";
    String playerO ="O";
    String currentPlayer= palyerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.orange);
        textLabel.setFont(new Font("Serif",Font.ITALIC,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textLabel,BorderLayout.NORTH); //to put the text to up

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.white);
        frame.add(boardPanel);

        for(int r=0 ; r<3;r++) {
            for(int c=0; c<3;c++) {
                JButton tile = new JButton();
                board[r][c] =tile;
                boardPanel.add(tile);

                
                tile.setBackground(Color.white);
                tile.setForeground(Color.DARK_GRAY);
                tile.setFont (new Font("Arial",Font.BOLD, 120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent h) {
                        if(gameOver) return;
                        JButton tile=(JButton) h.getSource();
                        //if statement is used for stop over writting x&o
                        if (tile.getText()==""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == palyerX ? playerO :palyerX;
                                textLabel.setText(currentPlayer+" 's turn.");

                            }  
                        }
                    }
                });
            }
        }
    }

    void checkWinner() {
        //horizontally
        for(int r=0; r<3;r++) {
            if (board[r][0].getText() == "")continue;

            if (board[r][0].getText()==board[r][1].getText()&&
            board[r][1].getText()==board[r][2].getText()){
                for(int i =0; i<3; i++){
                    setWinner(
                        board[r][i]);
                }
                gameOver=true;
                return;
            }
        }

        //vertically
        for(int c = 0; c < 3; c++) {
            if (board[0][c].getText()== "")continue;

            if (board [0][c].getText()==board[1][c].getText()&&
            board[1][c].getText()==board[2][c].getText()) {
                for(int i = 0; i<3;i++) {
                    setWinner(board[i][c]);
                }
                gameOver=true;
                return;
            }
        }

        //diaganolly
        if (board[0][0].getText()==board[1][1].getText()&&
        board[1][1].getText()==board[2][2].getText()&&
        board[0][0].getText() != "") {
            for(int i=0; i<3; i++) {
                setWinner(board[i][i]);
            }
            gameOver=true;
            return;
        }

        //anti-diaganolly
        if (board[0][2].getText() == board [1][1].getText()&&
        board [1][1].getText() == board [2][0].getText()&&
        board [2][0].getText() != "") {
            setWinner(board [0][2]);
            setWinner(board [1][1]);
            setWinner(board [2][0]);
            gameOver = true;
            return;  
        }

        if (turns == 9) {
            for(int r=0; r<3; r++) {
                for(int c=0; c<3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }

    }
    void setWinner(JButton tile) {
        tile.setForeground(Color.black);
        tile.setBackground(Color.green);
        textLabel.setText(currentPlayer + " Is the Winner!");
    }

    void setTie(JButton tile){
        tile.setBackground(Color.YELLOW);
        tile.setForeground(Color.MAGENTA);
        textLabel.setText("TEI:)");
    }
}