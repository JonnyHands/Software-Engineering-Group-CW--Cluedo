
public class BoardLayout {

    public Tile[][] board; //Array of tiles on the board

    /**
     * 0 - no room 
     * 1 - Kitchen 
     * 2 - Ballroom 
     * 3 - Conservatory 
     * 4 - Dining room 
     * 5 - Billiard 
     * 6 - Library 
     * 7 - Lounge 
     * 8 - Hall 
     * 9 - Study
     */
    int OO = 0; //no room
    int K_ = 1; //kitchen
    int B_ = 2; //Ballroom
    int C_ = 3; //Conservatory
    int D_ = 4; //Dining room
    int Bi = 5; //Billiard
    int L_ = 6; //Library
    int Lo = 7; //Lounge
    int Ha = 8; //Hall
    int St = 9; //Study
    int A_ = 10; //Assumption
    
    private int[][] room = new int[][]{ //24 x 25 grid
        //0  1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23
        {St, St, St, St, St, St, OO, OO, OO, Ha, Ha, Ha, Ha, Ha, Ha, OO, OO, OO, Lo, Lo, Lo, Lo, Lo, Lo}, //0
        {St, St, St, St, St, St, St, OO, OO, Ha, Ha, Ha, Ha, Ha, Ha, OO, OO, Lo, Lo, Lo, Lo, Lo, Lo, Lo}, //1
        {St, St, St, St, St, St, St, OO, OO, Ha, Ha, Ha, Ha, Ha, Ha, OO, OO, Lo, Lo, Lo, Lo, Lo, Lo, Lo}, //2
        {St, St, St, St, St, St, St, OO, OO, Ha, Ha, Ha, Ha, Ha, Ha, OO, OO, Lo, Lo, Lo, Lo, Lo, Lo, Lo}, //3
        {OO, OO, OO, OO, OO, OO, OO, OO, OO, Ha, Ha, Ha, Ha, Ha, Ha, OO, OO, Lo, Lo, Lo, Lo, Lo, Lo, Lo}, //4
        {OO, OO, OO, OO, OO, OO, OO, OO, OO, Ha, Ha, Ha, Ha, Ha, Ha, OO, OO, Lo, Lo, Lo, Lo, Lo, Lo, Lo}, //5
        {OO, L_, L_, L_, L_, L_, OO, OO, OO, Ha, Ha, Ha, Ha, Ha, Ha, OO, OO, OO, OO, OO, OO, OO, OO, OO}, //6
        {L_, L_, L_, L_, L_, L_, L_, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO}, //7
        {L_, L_, L_, L_, L_, L_, L_, OO, OO, A_, A_, A_, A_, A_, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO}, //8
        {L_, L_, L_, L_, L_, L_, L_, OO, OO, A_, A_, A_, A_, A_, OO, OO, D_, D_, D_, D_, D_, D_, D_, D_}, //9
        {OO, L_, L_, L_, L_, L_, OO, OO, OO, A_, A_, A_, A_, A_, OO, OO, D_, D_, D_, D_, D_, D_, D_, D_}, //10
        {OO, OO, OO, OO, OO, OO, OO, OO, OO, A_, A_, A_, A_, A_, OO, OO, D_, D_, D_, D_, D_, D_, D_, D_}, //11
        {Bi, Bi, Bi, Bi, Bi, Bi, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, D_, D_, D_, D_, D_, D_, D_, D_}, //12
        {Bi, Bi, Bi, Bi, Bi, Bi, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, D_, D_, D_, D_, D_, D_, D_, D_}, //13
        {Bi, Bi, Bi, Bi, Bi, Bi, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, D_, D_, D_, D_, D_, D_, D_, D_}, //14
        {Bi, Bi, Bi, Bi, Bi, Bi, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, D_, D_, D_, D_, D_}, //15
        {Bi, Bi, Bi, Bi, Bi, Bi, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO}, //16
        {OO, OO, OO, OO, OO, OO, OO, OO, B_, B_, B_, B_, B_, B_, B_, B_, OO, OO, OO, OO, OO, OO, OO, OO}, //17
        {OO, OO, OO, OO, OO, OO, OO, OO, B_, B_, B_, B_, B_, B_, B_, B_, OO, OO, K_, K_, K_, K_, K_, OO}, //18
        {OO, C_, C_, C_, C_, OO, OO, OO, B_, B_, B_, B_, B_, B_, B_, B_, OO, OO, K_, K_, K_, K_, K_, K_}, //19
        {C_, C_, C_, C_, C_, C_, OO, OO, B_, B_, B_, B_, B_, B_, B_, B_, OO, OO, K_, K_, K_, K_, K_, K_}, //20
        {C_, C_, C_, C_, C_, C_, OO, OO, B_, B_, B_, B_, B_, B_, B_, B_, OO, OO, K_, K_, K_, K_, K_, K_}, //21
        {C_, C_, C_, C_, C_, C_, OO, OO, B_, B_, B_, B_, B_, B_, B_, B_, OO, OO, K_, K_, K_, K_, K_, K_}, //22
        {C_, C_, C_, C_, C_, C_, OO, OO, OO, OO, B_, B_, B_, B_, OO, OO, OO, OO, K_, K_, K_, K_, K_, K_}, //23
        {OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO}}; //24

    /**
     * 0 - Void 
     * 1 - Hall 
     * 2 - Room/Wall 
     * 3 - Entrance 
     * 4 - Secret 
     * 5 - Player positions 
     * 6 - Weapon Positions
     * 8 - Roll again
     * 9 - Extra accusation
     */
    int V_ = 0; //Void (out of use tile)
    int __ = 1; //the path players walk on
    int W_ = 2; //walls and rooms (tiles that cant be walked on or through)
    int E_ = 3; // entrances to rooms
    int S_ = 4; //secret passages from one room to another
    int PP = 5; //player positions in rooms
    int WP = 6; //weapon positions in rooms
    int R_ = 8; //roll again tile
    int EA = 9; // extra accusation tile
    private int[][] tiletype = new int[][]{ //24 x 25 grid
        //0  1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23
        {W_, WP, WP, WP, WP, W_, V_, __, V_, W_, W_, W_, W_, W_, W_, V_, __, V_, W_, W_, WP, WP, WP, W_},//0
        {W_, WP, PP, PP, PP, W_, W_, __, __, W_, W_, WP, WP, W_, W_, __, __, W_, W_, W_, WP, WP, WP, W_},//1
        {W_, WP, PP, PP, PP, W_, W_, __, __, W_, WP, PP, PP, WP, W_, __, __, W_, W_, PP, PP, PP, W_, W_},//2
        {S_, W_, W_, W_, W_, W_, E_, __, __, W_, W_, PP, PP, W_, W_, __, __, W_, W_, PP, PP, PP, W_, W_},//3
        {V_, __, __, __, __, __, __, __, __, E_, WP, PP, PP, WP, W_, __, __, W_, W_, W_, W_, W_, W_, W_},//4 
        {__, __, __, __, __, __, __, __, __, W_, W_, W_, W_, W_, W_, __, __, E_, W_, W_, W_, W_, W_, S_},//5
        {V_, W_, W_, W_, W_, W_, EA, __, __, W_, W_, E_, E_, W_, W_, __, __, __, __, __, __, __, __, V_},//6
        {W_, PP, PP, W_, WP, WP, W_, __, __, __, __, __, __, __, __, EA, __, __, __, __, __, __, __, __},//7
        {W_, PP, PP, W_, WP, WP, E_, __, __, V_, V_, E_, V_, V_, __, __, __, __, R_, __, __, __, __, V_},//8
        {W_, PP, PP, W_, WP, WP, W_, __, __, V_, V_, V_, V_, V_, __, __, W_, E_, W_, W_, W_, W_, W_, W_},//9
        {V_, W_, W_, E_, W_, W_, __, __, __, V_, V_, V_, V_, V_, __, __, W_, W_, W_, W_, W_, W_, W_, W_},//10
        {V_, __, __, __, __, __, __, __, __, V_, V_, PP, V_, V_, __, __, W_, W_, W_, W_, PP, PP, WP, WP},//11
        {W_, E_, W_, W_, W_, W_, __, __, __, V_, V_, V_, V_, V_, __, __, E_, W_, W_, W_, PP, PP, WP, WP},//12
        {PP, PP, WP, WP, W_, W_, __, __, EA, V_, V_, V_, V_, V_, __, __, W_, W_, W_, W_, PP, PP, WP, WP},//13
        {PP, PP, WP, WP, W_, W_, __, __, __, V_, V_, V_, V_, V_, __, __, W_, W_, W_, W_, W_, W_, W_, W_},//14
        {PP, PP, WP, WP, W_, E_, __, __, __, __, __, __, __, __, __, __, __, __, EA, W_, W_, W_, W_, W_},//15
        {W_, W_, W_, W_, W_, W_, __, __, __, __, __, R_, __, __, __, __, __, __, __, __, __, __, __, V_},//16
        {V_, __, __, __, __, __, R_, __, W_, E_, W_, W_, W_, W_, E_, W_, __, __, __, __, __, __, __, __},//17
        {__, __, __, __, __, __, __, __, W_, W_, W_, W_, W_, W_, W_, W_, __, __, W_, E_, W_, W_, W_, V_},//18
        {V_, S_, W_, W_, W_, __, __, __, E_, W_, W_, PP, PP, PP, W_, E_, __, __, W_, W_, WP, W_, WP, W_},//19
        {W_, WP, W_, WP, W_, E_, __, __, W_, W_, W_, PP, PP, PP, W_, W_, __, R_, W_, WP, PP, PP, PP, W_},//20
        {W_, PP, PP, PP, WP, W_, __, __, W_, W_, W_, WP, WP, WP, W_, W_, __, __, W_, WP, PP, PP, PP, W_},//21
        {W_, PP, PP, PP, WP, W_, __, __, W_, W_, W_, WP, WP, WP, W_, W_, __, __, W_, W_, WP, W_, WP, W_},//22
        {W_, WP, W_, WP, W_, W_, V_, __, __, __, W_, W_, W_, W_, __, __, __, V_, S_, W_, W_, W_, W_, W_},//23
        {V_, V_, V_, V_, V_, V_, V_, V_, V_, __, V_, V_, V_, V_, __, V_, V_, V_, V_, V_, V_, V_, V_, V_},};//24


    private static final int row = 25; // sets number or rows
    private static final int column = 24; //sets number of columns

    //adjusts tile offset to centre character pieces.
    private float x = 29; // x offset for grid
    private float y = -2 ; // y offset for grid

    public BoardLayout() { //constructor to create board and implement the tiles features.
        
        board = new Tile[row][column];  // the boards dimensions
        
        for (int n = 0; n < board.length; n++) {
            y += 24; //adds tile space
            x = 30; //sets x offset           
            
            for (int m = 0; m < board[n].length; m++) {
                board[n][m] = new Tile(x, y, n, m, tiletype[n][m], room[n][m]);
                x += 24; //adds tile space
            }
        }
    }

}
