package com.example.votingapp.controller;

import com.example.votingapp.model.Vote;
import com.example.votingapp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping
    public ResponseEntity<Vote> vote(@RequestParam String option, HttpServletRequest request) {
        String voterIp = request.getRemoteAddr();
        Vote savedVote = voteService.saveVote(option, voterIp);
        return ResponseEntity.ok(savedVote);
    }

    @GetMapping("/results")
    public ResponseEntity<Map<String, Long>> getResults() {
        return ResponseEntity.ok(voteService.getResults());
    }
}
