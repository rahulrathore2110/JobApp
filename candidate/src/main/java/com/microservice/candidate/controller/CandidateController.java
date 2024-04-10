package com.microservice.candidate.controller;

import com.microservice.candidate.model.*;
import com.microservice.candidate.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/")
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate){
        Candidate candidate1 = this.candidateService.createCandidate(candidate);
        return new ResponseEntity<>(candidate1, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Candidate>> getAllCandidate(){
        List<Candidate> candidates = this.candidateService.getAllCandidates();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidate(@PathVariable Integer id){
        Candidate candidate = this.candidateService.getCandidateById(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@RequestBody Candidate candidate,@PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.updateCandidate(candidate,id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @PutMapping("/personalDetails/{id}")
    public ResponseEntity<Candidate> addCandidatePersonalDetails(@RequestBody PersonalDetails personalDetails, @PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.createCandidatePersonalDetails(personalDetails,id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @PutMapping("/education/{id}")
    public ResponseEntity<Candidate> addCandidateEducation(@RequestBody Education education, @PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.createCandidateEducation(education,id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @PutMapping("/experience/{id}")
    public ResponseEntity<Candidate> addCandidateExperience(@RequestBody Experience experience, @PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.createCandidateExperience(experience,id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @PutMapping("/certification/{id}")
    public ResponseEntity<Candidate> addCandidateCertification(@RequestBody Certification certification, @PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.createCandidateCertification(certification,id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateEducation/")
    public ResponseEntity<Candidate> updateCandidateEducation(@RequestBody Education education){
        Candidate updateCandidate = this.candidateService.updateCandidateEducation(education);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateExperience/")
    public ResponseEntity<Candidate> updateCandidateExperience(@RequestBody Experience experience){
        Candidate updateCandidate = this.candidateService.updateCandidateExperience(experience);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateCertification/")
    public ResponseEntity<Candidate> updateCandidateCertification(@RequestBody Certification certification){
        Candidate updateCandidate = this.candidateService.updateCandidateCertification(certification);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteEducation/{id}")
    public ResponseEntity<Candidate> deleteCandidateEducation(@PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.deleteCandidateEducation(id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteExperience/{id}")
    public ResponseEntity<Candidate> deleteCandidateExperience(@PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.deleteCandidateExperience(id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteCertification/{id}")
    public ResponseEntity<Candidate> deleteCandidateCertification(@PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.deleteCandidateCertification(id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @PostMapping("/language/{language}/{id}")
    public ResponseEntity<Candidate> addLanguage(@PathVariable String language, @PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.addLanguages(language,id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @PostMapping("/skills/{skill}/{id}")
    public ResponseEntity<Candidate> addSkills(@PathVariable String skill, @PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.addSkills(skill,id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/language/{language}/{id}")
    public ResponseEntity<Candidate> deleteLanguage(@PathVariable String language, @PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.deleteLanguages(language,id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/skills/{skill}/{id}")
    public ResponseEntity<Candidate> deleteSkills(@PathVariable String skill, @PathVariable Integer id){
        Candidate updateCandidate = this.candidateService.deleteSkills(skill,id);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }

    @PutMapping("apply/{id}/{jobId}")
    public ResponseEntity<String> applyJob(@PathVariable Integer id,@PathVariable Integer jobId){
        String updateCandidate = this.candidateService.applyForJob(id, jobId);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }








}
