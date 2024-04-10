package com.microservice.candidate.service;

import com.microservice.candidate.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {
     Candidate createCandidate(Candidate candidate);
     Candidate updateCandidate(Candidate candidate,Integer id);
     Candidate createCandidatePersonalDetails(PersonalDetails personalDetails,Integer id);
     Candidate createCandidateEducation(Education education, Integer id);
     Candidate createCandidateExperience(Experience experience, Integer id);
     Candidate createCandidateCertification(Certification certification, Integer id);
     Candidate updateCandidateEducation(Education education);
     Candidate updateCandidateExperience(Experience experience);
     Candidate updateCandidateCertification(Certification certification);
     Candidate deleteCandidateEducation(Integer id);
     Candidate deleteCandidateExperience(Integer id);
     Candidate deleteCandidateCertification(Integer id);
     Candidate addLanguages(String language,Integer id);
     Candidate addSkills(String skill,Integer id);
     Candidate deleteLanguages(String language,Integer id);
     Candidate deleteSkills(String skill,Integer id);
     Candidate getCandidateById(Integer id);
    List<Candidate> getAllCandidates();
    String applyForJob(Integer id, Integer jobId);
}
