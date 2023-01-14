package Model;

/**
 * This class is a wrapper class for the PlayerInfo class.
 *
 * It stores the playerInfo object in order to wrap the data stored in that
 * PlayerInfo object
 *
 * This makes obtaining data from playerInfo and dataModel more efficient
 */
public class InfoWrapper {
    private PlayerInfo p1;
    private PlayerInfo p2;

    /**
     * @Constructor Initializes both playerInfo objects
     * @param p1 playerInfo object for player one
     * @param p2 playerInfo object for player two
     */
    public InfoWrapper(PlayerInfo p1, PlayerInfo p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * this method is the accessor for the playerInfo object of player one
     *
     * @return p1 playerInfo object of Player 1
     */
    public PlayerInfo getP1() {
        return p1;
    }

    /**
     * this method is the accessor for the playerInfo object of player two
     *
     * @return p2 playerInfo object of Player 2
     */
    public PlayerInfo getP2() {
        return p2;
    }
}