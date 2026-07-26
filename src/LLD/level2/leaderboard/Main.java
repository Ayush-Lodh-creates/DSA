package LLD.level2.leaderboard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class User {
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}

class Leaderboard {

    private final Map<User, Integer> userScores;
    private final TreeMap<Integer, List<User>> scoresToUsers;

    public Leaderboard() {
        userScores = new HashMap<>();
        scoresToUsers = new TreeMap<>(Collections.reverseOrder());
    }

    public List<User> getTopKUsersByScore(int k) {
        List<User> userList = new ArrayList<>();
        for (Map.Entry<Integer, List<User>> entry : scoresToUsers.entrySet()) {
            List<User> users = entry.getValue();
            if (users.size() < k) {
                userList.addAll(users);
                k -= users.size();
            } else {
                userList.addAll(users.subList(0, k));
                break;
            }
        }
        return userList;
    }

    public void updateScore(int update, User user) {
        if (userScores.containsKey(user)) {
            int oldScore = userScores.get(user);
            List<User> userList = scoresToUsers.get(oldScore);
            if (userList.size() == 1) {
                scoresToUsers.remove(oldScore);
            } else {
                userList.remove(user);
            }
            int newScore = oldScore + update;
            userScores.put(user, newScore);
            scoresToUsers.computeIfAbsent(newScore, k -> new ArrayList<>()).add(user);
        } else {
            userScores.put(user, update);
            scoresToUsers.computeIfAbsent(update, k -> new ArrayList<>()).add(user);
        }
    }

    public int getRankForUser(User user) {
        if (!userScores.containsKey(user)) {
            return -1;
        }
        int rank = 1;
        for (Map.Entry<Integer, List<User>> entry : scoresToUsers.entrySet()) {
            if (entry.getValue().contains(user)) {
                return rank;
            }
            rank += entry.getValue().size();
        }
        return -1;
    }

    public Map<User, Integer> getUserScores() {
        return userScores;
    }
}

class DaysLeaderBoard {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final TreeMap<LocalDate, Leaderboard> dailyLeaderboards;

    public DaysLeaderBoard() {
        dailyLeaderboards = new TreeMap<>();
    }

    public void updateScoreForDay(int update, User user, String day) {
        LocalDate localDate = LocalDate.parse(day, FORMATTER);
        dailyLeaderboards.computeIfAbsent(localDate, k -> new Leaderboard()).updateScore(update, user);
    }

    public List<User> getTopKUsersForDay(String day, int k) {
        LocalDate localDate = LocalDate.parse(day, FORMATTER);
        Leaderboard board = dailyLeaderboards.get(localDate);
        if (board == null) return new ArrayList<>();
        return board.getTopKUsersByScore(k);
    }

    public int getRankForUserOnDay(String day, User user) {
        LocalDate localDate = LocalDate.parse(day, FORMATTER);
        Leaderboard board = dailyLeaderboards.get(localDate);
        if (board == null) return -1;
        return board.getRankForUser(user);
    }

    public List<User> getTopKScoresForWeek(String endDay, int k) {
        LocalDate endDate = LocalDate.parse(endDay, FORMATTER);
        LocalDate startDate = endDate.minusDays(7);
        Map<LocalDate, Leaderboard> range = dailyLeaderboards.subMap(startDate, true, endDate, true);

        Leaderboard aggregated = new Leaderboard();
        for (Leaderboard board : range.values()) {
            for (Map.Entry<User, Integer> entry : board.getUserScores().entrySet()) {
                aggregated.updateScore(entry.getValue(), entry.getKey());
            }
        }
        return aggregated.getTopKUsersByScore(k);
    }
}

public class Main {

    public static void main(String[] args) {
        User alice = new User(1, "Alice");
        User bob = new User(2, "Bob");
        User charlie = new User(3, "Charlie");
        User devin = new User(4, "Devin");

        System.out.println("=== Basic Leaderboard ===");
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.updateScore(20, alice);
        leaderboard.updateScore(89, bob);
        leaderboard.updateScore(1, charlie);
        leaderboard.updateScore(56, alice);
        leaderboard.updateScore(34, devin);
        leaderboard.updateScore(25, devin);
        leaderboard.updateScore(100, bob);
        leaderboard.updateScore(47, alice);
        leaderboard.updateScore(63, charlie);

        System.out.print("Top 3: ");
        for (User user : leaderboard.getTopKUsersByScore(3)) {
            System.out.print(user.name + "(" + leaderboard.getUserScores().get(user) + ") ");
        }
        System.out.println();
        System.out.println("Rank for Bob: " + leaderboard.getRankForUser(bob));

        System.out.println("\n=== Daily Leaderboard ===");
        DaysLeaderBoard daily = new DaysLeaderBoard();

        daily.updateScoreForDay(50, alice, "10-07-2026");
        daily.updateScoreForDay(80, bob, "10-07-2026");
        daily.updateScoreForDay(30, charlie, "10-07-2026");

        daily.updateScoreForDay(70, alice, "12-07-2026");
        daily.updateScoreForDay(20, bob, "12-07-2026");
        daily.updateScoreForDay(90, devin, "12-07-2026");

        daily.updateScoreForDay(60, charlie, "15-07-2026");
        daily.updateScoreForDay(40, alice, "15-07-2026");

        daily.updateScoreForDay(100, bob, "17-07-2026");
        daily.updateScoreForDay(55, devin, "17-07-2026");

        System.out.print("Top 2 on 10-07-2026: ");
        for (User user : daily.getTopKUsersForDay("10-07-2026", 2)) {
            System.out.print(user.name + " ");
        }
        System.out.println();

        System.out.println("Alice rank on 12-07-2026: " + daily.getRankForUserOnDay("12-07-2026", alice));

        System.out.print("Top 3 for week ending 17-07-2026: ");
        for (User user : daily.getTopKScoresForWeek("17-07-2026", 3)) {
            System.out.print(user.name + " ");
        }
        System.out.println();
    }
}
