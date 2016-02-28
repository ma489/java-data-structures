package com.mansourahmed.algorithms.stablemarriage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mansour on 05/12/15.
 */
public class RandomDataSet implements DataSet {

    public static final int NUMBER_OF_PAIRS = 3;

    private final List<Proposer> proposers;
    private final List<Acceptor> acceptors;

    public RandomDataSet() {
        proposers = initialiseProposers();
        acceptors = initialiseAcceptors();
        setRandomPreferences(acceptors, proposers);
    }

    private static List<Acceptor> initialiseAcceptors() {
        ArrayList<Acceptor> acceptors = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_PAIRS; i++) {
            acceptors.add(new Acceptor(i));
        }
        return acceptors;
    }

    private static List<Proposer> initialiseProposers() {
        ArrayList<Proposer> proposers = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_PAIRS; i++) {
            proposers.add(new Proposer(i));
        }
        return proposers;
    }

    private static void setRandomPreferences(List<Acceptor> acceptors, List<Proposer> proposers) {
        for (Acceptor acceptor : acceptors) {
            acceptor.setPreferences(getRandomlyOrderedIds());
        }
        for (Proposer proposer : proposers) {
            proposer.setPreferences(getRandomlyOrderedIds());
        }
    }

    private static List<Integer> getRandomlyOrderedIds() {
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_PAIRS; i++) {
            ids.add(i);
        }
        Collections.shuffle(ids);
        return ids;
    }

    public List<Proposer> getProposers() {
        return proposers;
    }

    public List<Acceptor> getAcceptors() {
        return acceptors;
    }

}