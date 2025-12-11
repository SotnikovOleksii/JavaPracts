package ua.university.fsm;

public class TestAutomatonFixed {

    public State process(String input) {
        State currentState = State.S;

        for (char ch : input.toCharArray()) {
            currentState = nextState(currentState, ch);
        }
        return currentState;
    }

    private State nextState(State current, char ch) {
        return switch (current) {

            case S -> (ch == 'T') ? State.ONE : State.S;

            case ONE -> {
                if (ch == 'E') yield State.TWO;
                if (ch == 'T') yield State.ONE;
                yield State.S;
            }

            case TWO -> {
                if (ch == 'S') yield State.THREE;
                if (ch == 'T') yield State.ONE;
                yield State.S;
            }

            case THREE -> {
                if (ch == 'T') yield State.F;
                yield State.S;
            }

            case F -> State.F;
        };
    }
}