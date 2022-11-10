package com.crio.codingame.services;

// import java.util.ArrayList;
// import java.util.Collections;
import java.util.List;
// import java.util.stream.Collectors;

import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.ContestStatus;
import com.crio.codingame.entities.RegisterationStatus;
import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.IContestRepository;
import com.crio.codingame.repositories.IUserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IContestRepository contestRepository;

    public UserService(IUserRepository userRepository, IContestRepository contestRepository) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
    }
    // TODO: CRIO_TASK_MODULE_SERVICES
    // Create and store User into the repository.
    @Override
    public User create(String name) {
     //return null;
     
    //  User nUser = userRepository.save(name);
     User user = new User(name, 1500);
     User newUser = userRepository.save(user);
     return newUser;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Get All Users in Ascending Order w.r.t scores if ScoreOrder ASC.
    // Or
    // Get All Users in Descending Order w.r.t scores if ScoreOrder DESC.

    @Override
    public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder){
    //  return Collections.emptyList();
     List<User> allUsers=userRepository.findAll();
     if(scoreOrder==ScoreOrder.ASC){
        allUsers.sort((User a, User b)->{
            if(a.getScore()>b.getScore()){
                return 1;
            }else if(a.getScore()<b.getScore()){
                return -1;
            }else{
                return 0;
            }
        });
     }else{
        allUsers.sort((User a, User b)->{
            if(a.getScore()<b.getScore()){
                return 1;
            }else if(a.getScore()>b.getScore()){
                return -1;
            }else{
                return 0;
            }
        });
     }
     return allUsers;
    }

    @Override
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is in progress!");
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(user.checkIfContestExists(contest)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
        }
        user.addContest(contest);
        userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.REGISTERED);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Withdraw the user from the contest
    // Hint :- Refer Unit Testcases withdrawContest method

    @Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
    //   return null;
    Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException());
    User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException());
    if(user.checkIfContestExists(contest)){
        // throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
         user.deleteContest(contest);
         userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.NOT_REGISTERED);
    } 
    if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
        throw new InvalidOperationException();
    }
    if(contest.getContestStatus().equals(ContestStatus.ENDED)){
        throw new InvalidOperationException();
    }
    if(user.getName()!=userName){
        throw new InvalidOperationException();
    }
    return null;
    // UserRegistrationDto user = userRepository.get;
    }
    
}
