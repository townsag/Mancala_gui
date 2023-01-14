package Model;

import java.util.ArrayList;

/**
 * This class stores all of the data for the players playing Mancala
 */
public class PlayerInfo {

    private ArrayList<Integer> row;
    private Integer mancala;
    private boolean isActive;
    private int playerNumber;

    /**
     * initializes the instance variables for each player
     *
     * @param pNum the currentPlayer.
     */
    public PlayerInfo(int pNum) {
        playerNumber = pNum;
        row = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            row.add(i);
        }
        mancala = 0;
        isActive = false;
    }

    /**
     * accessor for the number of stones in a players mancala
     *
     * @return mancala the number of stones in players mancala
     */
    public Integer getMancala() {
        return mancala;
    }

    /**
     * This method returns the number of holes at index in the row ArrayList
     *
     * @param index the location of the hole
     * @return int the number of stones stored in the hole at index in the row
     *         ArrayList
     */
    public Integer getHoleValue(int index) {
        return row.get(index);
    }

    /**
     * Accessor method for isActive
     *
     * @return is Active flag for if this player is currently active
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * accessor for playerNumber
     *
     * @return playerNumber the player id.
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * checks to see if a hole is empty
     *
     * @return tempFlag flag for if hole isEmoty or not
     */
    public boolean isEmpty() {
        boolean tempFlag = true;
        for (Integer holeValue : row) {
            if (holeValue != 0) {
                tempFlag = false;
            }
        }
        return tempFlag;
    }

    /**
     * get the remaining stones from players hole and put them in players mancala
     *
     */
    public void captureRemainingStones() {
        for (int i = 0; i < 6; i++) {
            mancala += row.get(i);
            row.set(i, 0);
        }
    }

    /**
     * mutator for the number of stones at specific index
     *
     * @param index location of the hole
     * @param value the number of stones in the hole
     */
    public void setHoleValue(int index, Integer value) {
        row.set(index, value);
    }

    /**
     * mutator for number of stones in mancala
     *
     * @param value the number of stones
     */
    public void setMancalaValue(Integer value) {
        mancala = value;
    }

    /**
     * mutator for isActive
     *
     * @param flag flag for isActive
     */
    public void setActive(boolean flag) {
        isActive = flag;
    }

    /**
     * sets the number of stones in all holes to startingValue
     *
     * @param startingValue number of stones
     */
    public void setAllValues(Integer startingValue) {
        for (int i = 0; i < 6; i++) {
            row.set(i, startingValue);
        }
    }

    /**
     * assigns the data from one PlayerInfo object to another PlayerInfo object
     *
     * @param pOther another PlayerInfo object
     */
    public void shallowAssignment(PlayerInfo pOther) {
        for (int i = 0; i < 6; i++) {
            row.set(i, pOther.getHoleValue(i));
        }
        this.mancala = pOther.getMancala();
        this.isActive = pOther.getIsActive();
        this.playerNumber = pOther.getPlayerNumber();
    }

}
