package epsi.archi.intro.vote.object;

import java.util.*;

public class Vote {

    private List<String> choices;
    private Map<String, Integer> votes = new HashMap<>();

    public Vote(List<String> aChoices) {choices = aChoices;}

    public void vote(String id, Integer choice) {
        votes.put(id, choice);
    }

    public List<String> getChoices() {
        return Collections.unmodifiableList(choices);
    }

    public Map<String,Integer> getVotes() {
        return Collections.unmodifiableMap(votes);
    }

    public Map<String, String> getDisplayableVotes() {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, Integer> vote: getVotes().entrySet()) {
            result.put(vote.getKey(), choices.get(vote.getValue()));
        }
        return result;
    }

    public String close() throws VoteException {
        if (votes.size() < 1) {
            throw new VoteException("Can't close vote : not enough votes");
        }
        Map<Integer, Integer> counter = new HashMap<>();
        for (Map.Entry<String, Integer> vote : votes.entrySet()) {
            counter.put(vote.getValue(), counter.getOrDefault(vote.getValue(), 0) +1);
        }
        int max = 0;
        int result = -1;
        for (Map.Entry<Integer, Integer> count : counter.entrySet()) {
            if (count.getValue() > max) {
                max = count.getValue();
                result = count.getKey();
            }
        }
        return choices.get(result);
    }

}
