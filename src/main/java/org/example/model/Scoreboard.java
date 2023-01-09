package org.example.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Scoreboard {
    private List<UserData> scores;

    public void addScore(UserData data) {
        scores.add(data);
        scores = scores.stream()
                .sorted(Comparator.comparing(x -> -x.getScore()))
                .limit(11)
                .collect(Collectors.toList());
    }

    public void sortScores() {
        scores = scores.stream()
                .sorted(Comparator.comparing(x -> -x.getScore()))
                .limit(11)
                .collect(Collectors.toList());
    }
}
