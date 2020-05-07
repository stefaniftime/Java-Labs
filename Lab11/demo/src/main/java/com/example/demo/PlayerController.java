package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private List<Player> playerList = new ArrayList<>();
    public PlayerController() {
        playerList.add(new Player(1, "Andrei"));
        playerList.add(new Player(2, "Robert"));
    }
    @GetMapping
    public List<Player> getPlayerList() {
        return playerList;
    }
    @GetMapping("/count")
    public int countPlayer() {
        return playerList.size();
    }
    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable("id") int id) {
        return playerList.stream()
                .filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    @PostMapping
    public int createPlayer(@RequestParam String name) {
        int id = 1 + playerList.size();
        playerList.add(new Player(id, name));
        return id;
    }

    @PostMapping(value = "/obj", consumes="application/json")
    public ResponseEntity<String>
    createPlayer(@RequestBody Player player) {
        playerList.add(player);
        return new ResponseEntity<>(
                "Player created successfully", HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(
            @PathVariable int id, @RequestParam String name) {
        Player player = getPlayer(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Player not found", HttpStatus.NOT_FOUND); //or GONE
        }
        player.setName(name);
        return new ResponseEntity<>(
                "Player updated successsfully", HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) {
        Player player = getPlayer(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Player not found", HttpStatus.GONE);
        }
        playerList.remove(player);
        return new ResponseEntity<>("Player removed", HttpStatus.OK);
    }

}