package main;

import java.util.Arrays;

public enum Message {
    GAME_LOADED("WGEAPI.notifyGameplayID"),
    GAME_READY("WGEAPI.status.ready"),
    SPIN_START("WGEAPI.spinStarted"),
    SPIN_END("WGEAPI.spinEnded"),
    ;

    private final String method;

    Message(String method) {
        this.method = method;
    }

    public static Message of(String method) {
    	
        return Arrays.stream(values())
                .filter(m -> m.is(method))
                .findFirst()
                .orElse(null);
    }

    public String getMessage() {
		return method;
	}
    
    private boolean is(String input) {
        return method.equals(input);
    }
}
