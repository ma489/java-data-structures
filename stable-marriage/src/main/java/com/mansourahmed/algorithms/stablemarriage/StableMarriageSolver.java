package com.mansourahmed.algorithms.stablemarriage;

import java.util.*;

/**
 * Created by mansour on 05/12/15.
 */
public class StableMarriageSolver {

    /**
     * solve using Gale-Shapley algorithm (assumes equal number of proposers and acceptors)
     * aka Deferred-acceptance algorithm
     * https://en.wikipedia.org/wiki/Stable_marriage_problem
     */
    public Map<Proposer, Acceptor> solve(List<Proposer> proposers, List<Acceptor> acceptors) {
        Map<Proposer, Acceptor> matchings = new HashMap<>();
        List<Proposer> menWhoAreFreeAndHaveWomenTheyAreYetToProposeTo = new ArrayList<>(proposers);
        Map<Proposer, List<Acceptor>> womenToWhomTheseMenHaveNotAlreadyProposed = new HashMap<>();
        for (Proposer proposer : proposers) {
            womenToWhomTheseMenHaveNotAlreadyProposed.put(proposer, acceptors);
        }
        galeShapley(matchings,
                menWhoAreFreeAndHaveWomenTheyAreYetToProposeTo,
                womenToWhomTheseMenHaveNotAlreadyProposed);
        return matchings;
    }

    private static void galeShapley(Map<Proposer, Acceptor> matchings,
                                    List<Proposer> menWhoAreFreeAndHaveWomenTheyAreYetToProposeTo,
                                    Map<Proposer, List<Acceptor>> womenToWhomTheseMenHaveNotAlreadyProposed) {
        while (!menWhoAreFreeAndHaveWomenTheyAreYetToProposeTo.isEmpty()) {
            Proposer man = menWhoAreFreeAndHaveWomenTheyAreYetToProposeTo.remove(0);
            List<Acceptor> pendingProposal = womenToWhomTheseMenHaveNotAlreadyProposed.get(man);
            Acceptor woman = getHighestRankingPendingWoman(pendingProposal, man.getPreferences());
            if (!woman.isEngaged()) {
                matchings.put(man, woman);
                woman.setEngaged(true);
                man.setEngaged(true);
            } else { //some pair (otherMan, woman) already exists
                Proposer otherMan = getTentativeFiance(matchings, woman);
                if (prefers(woman, man, otherMan)) {//if woman prefers man to otherMan
                    matchings.put(man, woman);
                    man.setEngaged(true);
                    matchings.remove(otherMan);
                    otherMan.setEngaged(false);
                    menWhoAreFreeAndHaveWomenTheyAreYetToProposeTo.add(otherMan);
                    womenToWhomTheseMenHaveNotAlreadyProposed.get(otherMan).remove(woman);
                }
                //else (otherMan, woman) remain engaged
            }
            womenToWhomTheseMenHaveNotAlreadyProposed.get(man).remove(woman);
        }
    }

    private static Proposer getTentativeFiance(Map<Proposer, Acceptor> matchings, Acceptor woman) {
        for (Map.Entry<Proposer, Acceptor> matching : matchings.entrySet()) {
            if (woman.equals(matching.getValue())) {
                return matching.getKey();
            }
        }
        return null;
    }

    private static Acceptor getHighestRankingPendingWoman(List<Acceptor> pendingProposal, List<Integer> preferences) {
        List<Acceptor> listCopy = new ArrayList<>(pendingProposal);
        Comparator<Acceptor> comparator = new Comparator<Acceptor>() {
            @Override
            public int compare(Acceptor o1, Acceptor o2) {
                if (o1.equals(o2)) {
                    return 0;
                } else if (preferences.indexOf(o1.getId()) < preferences.indexOf(o2.getId())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        Collections.sort(listCopy, comparator);
        return listCopy.get(0);
    }

    private static boolean prefers(Acceptor woman, Proposer man, Proposer otherMan) {
        List<Integer> preferences = woman.getPreferences();
        return preferences.indexOf(man.getId()) < preferences.indexOf(otherMan.getId());
    }

}