package com.example.votingapp.service;

import com.example.votingapp.model.Vote;
import com.example.votingapp.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;

    public Vote saveVote(String option, String voterIp) {
        Vote vote = new Vote();
        vote.setOption(option);
        vote.setVoterIp(voterIp);
        return voteRepository.save(vote);
    }

    public Map<String, Long> getResults() {
        List<Vote> votes = voteRepository.findAll();
        return votes.stream()
                .collect(Collectors.groupingBy(Vote::getOption, Collectors.counting()));
    }
}
