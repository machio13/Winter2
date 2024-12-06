package simwinter.master;

public enum Market {
    Prime("P"),
    Standard("S"),
    Growth("G"),
    Non("---");

    private String firstChar;

    Market(String firstChar) {
        this.firstChar = firstChar;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public static Market isRename(String market) {
        return switch (market) {
            case "P" -> Prime;
            case "S" -> Standard;
            case "G" -> Growth;
            default -> Non;
        };
    }
}
