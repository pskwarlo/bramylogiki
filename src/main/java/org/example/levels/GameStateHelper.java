package org.example.levels;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.example.model.GameSave;
import org.example.model.Scoreboard;
import org.example.model.UserData;

@UtilityClass
public class GameStateHelper {
    public static final String SAVE_PATH = "save.json";
    public static final String LEADERBOARD_PATH = "leaderboard.json";
    private static final Gson gson = new Gson();

    @SneakyThrows
    public static void addToLeaderboard(UserData data) {
        Scoreboard scoreboard;
        try (Reader reader = new FileReader(LEADERBOARD_PATH)) {
            scoreboard = gson.fromJson(reader, Scoreboard.class);
            scoreboard.addScore(data);
        } catch (IOException ex) {
            scoreboard = new Scoreboard(List.of(data));
        }

        File file = new File(LEADERBOARD_PATH);
        Files.deleteIfExists(file.toPath());

        try (FileWriter writer = new FileWriter(LEADERBOARD_PATH)) {
            gson.toJson(scoreboard, writer);
        }
    }

    public static GameSave loadGame() throws FileNotFoundException {
        try (Reader reader = new FileReader(SAVE_PATH)) {
            return gson.fromJson(reader, GameSave.class);
        } catch (IOException ex) {
            throw new FileNotFoundException(ex.getMessage());
        }
    }

    @SneakyThrows
    public static void saveGame(int level, UserData data) {
        File file = new File(SAVE_PATH);
        Files.deleteIfExists(file.toPath());
        GameSave save = new GameSave(data.getScore(), data.getUsername(), level);
        try (FileWriter writer = new FileWriter(SAVE_PATH)) {
            gson.toJson(save, writer);
        }
    }

    public static Scoreboard takeLeaderboard() {
        try (Reader reader = new FileReader(LEADERBOARD_PATH)) {
            return gson.fromJson(reader, Scoreboard.class);
        } catch (IOException ex) {
            return new Scoreboard(Collections.emptyList());
        }
    }
}
