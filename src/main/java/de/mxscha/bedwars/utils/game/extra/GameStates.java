package de.mxscha.bedwars.utils.game.extra;

public class GameStates {

    // Number of which GameState is the Game right now!
    private static int GameState = 0;

    public static int getGameState() {
        return GameState;
    }

    public static void setGameState(int gameState) {
        GameState = gameState;
    }
}
