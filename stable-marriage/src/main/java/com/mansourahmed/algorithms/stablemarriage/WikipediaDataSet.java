package com.mansourahmed.algorithms.stablemarriage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mansour on 05/12/15.
 */
public class WikipediaDataSet implements DataSet {

    /**
     3,4 (C,X)
     2,6 (B,Z)
     1,5 (A,Y)
     proposers (suitors) get first choice
     acceptors (reviewers) get 3rd choice
     men-optimal matching
     */

    private final List<Proposer> proposers;
    private final List<Acceptor> acceptors;

    public WikipediaDataSet() {
        proposers = initialiseProposers(); // 1 = A, 2 = B, 3 = C
        acceptors = initialiseAcceptors(); // 4 = X, 5 = Y, 6 = Z
        setPreferences();
    }

    private void setPreferences() {
        //A: YXZ
        List<Integer> preferencesOfA = proposers.get(0).getPreferences();
        preferencesOfA.add(5);
        preferencesOfA.add(4);
        preferencesOfA.add(6);
        //B: ZYX
        List<Integer> preferencesOfB = proposers.get(1).getPreferences();
        preferencesOfB.add(6);
        preferencesOfB.add(5);
        preferencesOfB.add(4);
        //C: XZY
        List<Integer> preferencesOfC = proposers.get(2).getPreferences();
        preferencesOfC.add(4);
        preferencesOfC.add(6);
        preferencesOfC.add(5);
        //X: BAC
        List<Integer> preferencesOfX = acceptors.get(0).getPreferences();
        preferencesOfX.add(2);
        preferencesOfX.add(1);
        preferencesOfX.add(3);
        //Y: CBA
        List<Integer> preferencesOfY = acceptors.get(1).getPreferences();
        preferencesOfY.add(3);
        preferencesOfY.add(2);
        preferencesOfY.add(1);
        //Z: ACB
        List<Integer> preferencesOfZ = acceptors.get(2).getPreferences();
        preferencesOfZ.add(1);
        preferencesOfZ.add(3);
        preferencesOfZ.add(2);
    }

    private static List<Proposer> initialiseProposers() {
        ArrayList<Proposer> proposers = new ArrayList<>();
        proposers.add(new Proposer(1)); //A
        proposers.add(new Proposer(2)); //B
        proposers.add(new Proposer(3)); //C
        return proposers;
    }

    private static List<Acceptor> initialiseAcceptors() {
        ArrayList<Acceptor> acceptors = new ArrayList<>();
        acceptors.add(new Acceptor(4)); //X
        acceptors.add(new Acceptor(5)); //Y
        acceptors.add(new Acceptor(6)); //Z
        return acceptors;
    }

    @Override
    public List<Proposer> getProposers() {
        return proposers;
    }

    @Override
    public List<Acceptor> getAcceptors() {
        return acceptors;
    }

}