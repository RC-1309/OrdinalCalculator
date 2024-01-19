package operations;

import java.util.List;

public class Priority {
    private static final int constAndVariablePriority = 0;
    private static final int powPriority = 1;
    private static final int multiplyPriority = 2;
    private static final int addPriority = 3;
    private static final int MAX_PRIORITY = 3;

    public static final List<String[]> PRIORITIES = List.of(
            new String[]{},
            new String[]{"^"},
            new String[]{"*"},
            new String[]{"+"}
    );

    public static int getPowPriority() {
        return powPriority;
    }

    public static int getConstAndVariablePriority() {
        return constAndVariablePriority;
    }

    public static int getMultiplyPriority() {
        return multiplyPriority;
    }

    public static int getAddPriority() {
        return addPriority;
    }

    public static int getMaxPriority() {
        return MAX_PRIORITY;
    }
}
