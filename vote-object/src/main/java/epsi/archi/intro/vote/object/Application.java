package epsi.archi.intro.vote.object;

public class Application {

    public static void main(String[] args) {
        ConsoleDisplay display = new ConsoleDisplay();
        Vote vote = new Vote(display.createChoices());
        Action action = Action.UNKNOWN;
        do {
            action = display.askAction();
            switch (action) {
                case VOTE: {
                    String id = display.askId();
                    Integer choice = display.askChoice(vote.getChoices());
                    if (choice >= 0 && choice < vote.getChoices().size()) {
                        vote.vote(id, choice);
                    } else {
                        display.displayChoiceInvalid();
                    }
                    break;
                }
                case DISPLAY: {
                    display.displayVote(vote.getDisplayableVotes());
                    break;
                }
                case CLOSE: {
                    try {
                        String result = vote.close();
                        display.displayResult(result);
                    } catch (VoteException e) {
                        display.displayError(e);
                    }
                    break;
                }
                default: {
                    display.displayChoiceInvalid();
                }
            }
            display.displaySeparator();
        } while(action != Action.CLOSE);
    }
}
