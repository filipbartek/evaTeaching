package evolution.prisoner.strategies;

import evolution.prisoner.Result;
import evolution.prisoner.Strategy;
import java.util.HashMap;
import java.util.Map;

public class Bartekfi extends Strategy {

    private Map<Move, Integer> sums;
    private Map<Move, Integer> nums;

    public Bartekfi() {
        reset();
    }

    @Override
    public String authorName() {
        return "Filip Bartek";
    }

    @Override
    public String getName() {
        return "bartekfi";
    }

    @Override
    public Move nextMove() {
        /*if (nums.get(Move.COOPERATE) == 0) {
            return Move.COOPERATE;
        }
        if (nums.get(Move.DECEIVE) == 0) {
            return Move.DECEIVE;
        }*/
        double avgCooperate = ((double)sums.get(Move.COOPERATE) / nums.get(Move.COOPERATE));
        double avgDeceive = ((double)sums.get(Move.DECEIVE) / nums.get(Move.DECEIVE));
        if (avgCooperate >= avgDeceive) {
            return Move.COOPERATE;
        } else {
            return Move.DECEIVE;
        }
        // TODO: Chytra heuristika - jako double jednoruky bandita.
    }

    @Override
    public void reward(Result res) {
        nums.put(res.getMyMove(), nums.get(res.getMyMove()) + 1); // increment

        int diff = res.getMyScore();
        int prevSum = sums.get(res.getMyMove());
        int newSum = prevSum + diff;
        sums.put(res.getMyMove(), newSum);
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        sums = new HashMap<Move, Integer>();
        sums.put(Move.COOPERATE, 1);
        sums.put(Move.DECEIVE, 1);

        nums = new HashMap<Move, Integer>();
        nums.put(Move.COOPERATE, 1);
        nums.put(Move.DECEIVE, 1);
    }

}
