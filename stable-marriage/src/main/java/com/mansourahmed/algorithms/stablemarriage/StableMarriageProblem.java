package com.mansourahmed.algorithms.stablemarriage;

import java.util.*;

/**
 * Created by mansour on 05/12/15.
 */
public class StableMarriageProblem {

    private static void printPreferences(List<Proposer> proposers, List<Acceptor> acceptors) {
        System.out.println("Proposer preferences: ");
        for (Proposer proposer : proposers) {
            System.out.println(proposer.getId() + " : " + proposer.getPreferences());
        }
        System.out.println("Acceptor preferences: ");
        for (Acceptor acceptor : acceptors) {
            System.out.println(acceptor.getId() + " : " + acceptor.getPreferences());
        }
    }

    public static void main(String[] args) {
        //DataSet dataset = new RandomDataSet();
        DataSet dataset = new WikipediaDataSet();
        List<Proposer> proposers = dataset.getProposers();
        List<Acceptor> acceptors = dataset.getAcceptors();
        System.out.println("Solving: ");
        printPreferences(proposers, acceptors);
        StableMarriageSolver stableMarriageSolver = new StableMarriageSolver();
        Map<Proposer, Acceptor> matchings = stableMarriageSolver.solve(proposers, acceptors);
        printSolution(matchings);
    }

    private static void printSolution(Map<Proposer, Acceptor> matchings) {
        System.out.println("Done. Solution: ");
        for (Map.Entry<Proposer, Acceptor> matching : matchings.entrySet()) {
            Proposer proposer = matching.getKey();
            Acceptor acceptor = matching.getValue();
            String match = "Proposer: " + proposer.getId() + " - Acceptor: " + acceptor.getId();
            System.out.println(match);
            int propPref = proposer.getPreferences().indexOf(acceptor.getId()) + 1;
            int accPref = acceptor.getPreferences().indexOf(proposer.getId()) + 1;
            String pref = "This was proposer's " + propPref + "th choice, and acceptor's " + accPref + "th choice";
            System.out.println(pref);
        }
    }

}