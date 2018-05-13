package epsi.archi.intro.vote.object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleDisplay {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private String readLine() {
        try {
            return in.readLine().trim();
        } catch (IOException e) {
            return "";
        }
    }

    private Integer readInt() {
        try {
            return Integer.valueOf(in.readLine().trim());
        } catch (IOException|NumberFormatException e) {
            return -1;
        }
    }

    public List<String> createChoices() {
        List<String> result = new ArrayList<>();
        System.out.println("Define the choices");
        String input = "";
        do {
            System.out.print("New choice (N to stop) : ");
            input = readLine();
            if (!input.equals("N")) {
                result.add(input);
            }
        } while (!input.equals("N"));
        return result;
    }

    public Action askAction() {
        System.out.println("Choose your action :");
        System.out.println("1 - Vote");
        System.out.println("2 - Display");
        System.out.println("3 - Close");
        System.out.print("Choose your action : ");
        Action result;
        switch (readInt()) {
            case 1: {
                result = Action.VOTE;
                break;
            }
            case 2: {
                result = Action.DISPLAY;
                break;
            }
            case 3: {
                result = Action.CLOSE;
                break;
            }
            default: {
                result = Action.UNKNOWN;
            }
        }
        return result;
    }

    public String askId() {
        System.out.println();
        System.out.print("Identifiant : ");
        String result = readLine();
        return result;
    }

    public Integer askChoice(List<String> choices) {
        System.out.println("Choices :");
        for(int i = 0; i < choices.size(); i++) {
            System.out.println(i + " - " + choices.get(i));
        }
        System.out.print("Vote for : ");
        return readInt();
    }

    public void displayVote(Map<String,String> votes) {
        for (Map.Entry<String, String> vote : votes.entrySet()) {
            System.out.println(vote.getKey()+" voted "+vote.getValue());
        }
    }

    public void displayChoiceInvalid() {
        System.out.println("Unknown choice ...");
    }

    public void displayResult(String result) {
        System.out.println("Vote result : "+result);
    }

    public void displayError(VoteException exception) {
        System.err.println("Error : "+exception.getMessage());
    }

    public void displaySeparator() {
        System.out.println();
        System.out.println();
    }


}
